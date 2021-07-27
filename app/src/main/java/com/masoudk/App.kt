package com.masoudk

import android.app.Application
import com.masoudk.di.appModule
import com.masoudk.di.localPersistenceModule
import com.masoudk.di.networkModule
import com.masoudk.ui.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(
                listOf(
                    appModule,
                    networkModule,
                    localPersistenceModule
                )
            )
            koin.createRootScope()
        }

    }

}