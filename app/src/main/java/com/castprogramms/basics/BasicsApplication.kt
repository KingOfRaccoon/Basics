package com.castprogramms.basics

import android.app.Application
import com.castprogramms.basics.registration.emailandpassword.LoginViewModel
import com.castprogramms.basics.workwithnetwork.Repository
import com.castprogramms.basics.workwithnetwork.ManageUserDataFireStore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

class BasicsApplication: Application() {
    val appModule = module{
        single { ManageUserDataFireStore() }
        single { Repository(get()) }
        viewModel { LoginViewModel(get()) }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BasicsApplication)
            modules(appModule)
        }
    }
}