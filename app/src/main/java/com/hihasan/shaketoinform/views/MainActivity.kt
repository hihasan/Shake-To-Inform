package com.hihasan.shaketoinform.views

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hihasan.shaketoinform.R
import com.hihasan.shaketoinform.constants.DatabaseConstants
import com.hihasan.shaketoinform.data.entity.BugEntity
import com.hihasan.shaketoinform.databinding.ActivityMainBinding
import com.hihasan.shaketoinform.utils.BaseActivity
import com.hihasan.shaketoinform.utils.BaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject lateinit var mainRepository: MainRepository

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        sensorListeners()

        initListeners()

    }

    private fun sensorListeners() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        Objects.requireNonNull(sensorManager)!!
            .registerListener(sensorListener, sensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)

        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH


    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration

            currentAcceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta

            if (acceleration > 25) {
                materialDialog()
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun materialDialog() {
        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(resources.getString(R.string.app_name))
            .setMessage(resources.getString(R.string.long_message))
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                dialog.dismiss()
                val intent = Intent(this@MainActivity, BugActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            .show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListeners() {
        binding.content.calculationBtn.setOnClickListener {

            try {
                val calcuate = binding.content.number1.text.toString().toInt() / binding.content.number2.text.toString().toInt()
                Toast.makeText(applicationContext, calcuate.toString() , Toast.LENGTH_SHORT).show()
            } catch (e :java.lang.Exception){
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                val localTime = LocalTime.now()
                val localDate = LocalDate.now()
                val bugEntity = BugEntity(
                    0,
                    "e.printStackTrace().toString()",
                    localDate.toString(),
                    localTime.toString()
                )

                viewModel.insertBug(bugEntity)
            }
        }
    }

    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )

        super.onResume()
    }

    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }
}