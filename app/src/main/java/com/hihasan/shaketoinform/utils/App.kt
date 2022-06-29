package com.hihasan.shaketoinform.utils

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application(){


    companion object{
        lateinit var ctx: Context

        fun getAppContext(): Context {
            return ctx
        }
    }



    override fun onCreate() {
        ctx = applicationContext


        super.onCreate()
    }




    override fun onLowMemory() {

        super.onLowMemory()
    }


}