package com.madprateek.gosensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensor : Sensor? = null
    var sensorManager : SensorManager? = null
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event!!.values[0] > 30 && isRunning == false ){
            isRunning = true
            try{
                //var filePath =  Environment.getExternalStorageDirectory()/Music/Mere Bina.mp3"
                var mp = MediaPlayer()
                mp.setDataSource("https://s1.vocaroo.com/media/download_temp/Vocaroo_s1IgjP1TWkrt.mp3")
                mp.prepare()
                mp.start()
            }catch (ex : Exception){
                Toast.makeText(this,"exception",Toast.LENGTH_LONG).show()
                Log.d("HERE OCUUR",ex.message)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
