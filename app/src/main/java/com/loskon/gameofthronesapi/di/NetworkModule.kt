package com.loskon.gameofthronesapi.di

import android.content.Context
import com.loskon.gameofthronesapi.BuildConfig
import com.loskon.gameofthronesapi.data.networkdatasource.api.CharacterApi
import com.loskon.gameofthronesapi.data.networkdatasource.interceptor.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CACHE_SIZE = 2 * 1024 * 1024L
    private const val TIMEOUT = 30L
    const val CACHE_DIR_NAME = "characters"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(File(context.cacheDir.path, CACHE_DIR_NAME), CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(@ApplicationContext context: Context): CacheInterceptor {
        return CacheInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttp(cacheInterceptor: CacheInterceptor, logging: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(cacheInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(logging)
            cache(cache)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttp)
            baseUrl(BuildConfig.API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }
}