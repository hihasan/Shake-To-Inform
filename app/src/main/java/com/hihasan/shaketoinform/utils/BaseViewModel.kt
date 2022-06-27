package com.hihasan.shaketoinform.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.hihasan.shaketoinform.constants.DatabaseConstants

abstract class BaseViewModel(context : Context) : ViewModel(){


    var database: BaseDatabase? = let {
        Room.databaseBuilder(context, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

}