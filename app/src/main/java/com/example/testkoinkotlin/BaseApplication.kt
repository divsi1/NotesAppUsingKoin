package com.example.testkoinkotlin

import android.app.Application
import com.example.testkoinkotlin.di.appModule
import com.example.testkoinkotlin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
//divya
    override fun onCreate() {
    super.onCreate()
    startKoin {androidContext(this@BaseApplication)
        modules(listOf(appModule, viewModelModule))
    }
    }

}
