package com.loskon.gameofthronesapi.data.networkdatasource.interceptor

import android.content.Context
import com.loskon.gameofthronesapi.domain.exception.EmptyCacheException
import com.loskon.gameofthronesapi.utils.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.util.concurrent.TimeUnit

class CacheInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return if (NetworkUtil.hasConnected(context)) {
            val onlineRequest = onlineCacheControl(request)
            chain.proceed(onlineRequest).addCacheHeader(fromCache = false)
        } else {
            if (hasCacheDir()) {
                val offlineRequest = offlineCacheControl(request)
                chain.proceed(offlineRequest).addCacheHeader(fromCache = true)
            } else {
                throw EmptyCacheException()
            }
        }
    }

    private fun hasCacheDir(): Boolean = File(context.cacheDir.path).exists()

    private fun onlineCacheControl(request: Request): Request {
        val cacheControl = CacheControl.Builder().maxAge(MAX_AGE_CACHE, TimeUnit.SECONDS).build()
        return request.newBuilder().header(CACHE_CONTROL, cacheControl.toString()).build()
    }

    private fun offlineCacheControl(request: Request): Request {
        val cacheControl = CacheControl.Builder().maxStale(MAX_STALE_CACHE, TimeUnit.DAYS).build()
        return request.newBuilder().cacheControl(cacheControl).build()
    }

    private fun Response.addCacheHeader(fromCache: Boolean): Response {
        return newBuilder().addHeader(CACHE_HEADER, fromCache.toString()).build()
    }

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val MAX_AGE_CACHE = 10
        private const val MAX_STALE_CACHE = 11
        const val CACHE_HEADER = "Cache-Header"
    }
}