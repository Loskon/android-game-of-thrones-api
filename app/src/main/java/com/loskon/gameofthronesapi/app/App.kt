package com.loskon.gameofthronesapi.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loskon.gameofthronesapi.BuildConfig
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class App : DaggerApplication() {

    //val appComponent = DaggerAppComponent.create()
    //val appComponent = DaggerAppComponent.builder().applicationModule()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this@App).build()
    }
}

/*
@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(application: App)
}

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}*/

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
}

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Application = app
}

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

/*
@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {
    //fun inject(activity: MainActivity)
}*/