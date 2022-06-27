package com.hihasan.shaketoinform.utils

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hihasan.shaketoinform.R
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
class App : Application(){


    companion object{
        lateinit var ctx: Context

        fun getAppContext(): Context {
            return ctx
        }
    }

    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onCreate() {
        ctx = applicationContext
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        Objects.requireNonNull(sensorManager)!!
            .registerListener(sensorListener, sensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)

        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )

        super.onCreate()
    }




    override fun onLowMemory() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onLowMemory()
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

            if (acceleration > 12) {

                val builder = AlertDialog.Builder(ctx)
                builder.setCancelable(true)
                builder.setMessage(resources.getString(R.string.long_message))
                builder.setInverseBackgroundForced(true)

                builder.setNeutralButton(
                    "Ok"
                ) { dialog, whichButton -> dialog.dismiss() }

                builder.show()

//                MaterialAlertDialogBuilder(
//                    App.getAppContext(),
//                    R.style.MaterialComponents)
//                    .setMessage(resources.getString(R.string.long_message))
//                    .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
//                        // Respond to negative button press
//                        dialog.dismiss()
//                    }
//                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
//                        // Respond to positive button press
//                    }
//                    .show()
//                val alert: AlertDialog = alrtDialog.create()
//                alert.show()
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
}