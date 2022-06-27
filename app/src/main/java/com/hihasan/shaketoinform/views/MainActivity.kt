package com.hihasan.shaketoinform.views

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.hihasan.shaketoinform.constants.DatabaseConstants
import com.hihasan.shaketoinform.data.entity.BugEntity
import com.hihasan.shaketoinform.databinding.ActivityMainBinding
import com.hihasan.shaketoinform.utils.BaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainRepository: MainRepository

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var database: BaseDatabase



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        database = let {
            Room.databaseBuilder(it, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }

        initListeners()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListeners() {
        binding.content.calculationBtn.setOnClickListener {
            val calcuate = binding.content.number1.toString().toInt() / binding.content.number2.toString().toInt()

            try {

                Toast.makeText(applicationContext, calcuate.toString() , Toast.LENGTH_SHORT).show()
            } catch (e : Exception){
                val localTime = LocalTime.now()
                val localDate = LocalDate.now()
                val bugEntity = BugEntity(
                    0,
                    e.printStackTrace().toString(),
                    localDate.toString(),
                    localTime.toString()
                )

                viewModel.insertBug(bugEntity)
            }
        }
    }
}