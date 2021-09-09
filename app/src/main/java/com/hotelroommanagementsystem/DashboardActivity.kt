package com.hotelroommanagementsystem

import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.hotelroommanagementsystem.db.UserDB
import com.hotelroommanagementsystem.fragments.RoomFragment
import com.hotelroommanagementsystem.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DashboardActivity : AppCompatActivity() {
    lateinit var btmNav: BottomNavigationView
    lateinit var frameLayout: FrameLayout

    private lateinit var sensorManager: SensorManager
    private var sensorAccelerometer: Sensor? = null
    private var sensorProximity: Sensor? = null
    private var sensorGyroscope: Sensor? = null
    private var acclValue = 0f
    private var lastAcclValue: Float = 0f
    private var shake: Float = 0f
    private var powerManager: PowerManager? = null
    private var wakeLock: PowerManager.WakeLock? = null
    private var field = 0x00000020

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if (!checkSensor()) {
            Toast.makeText(this, "Sensor Not Available", Toast.LENGTH_SHORT).show()
        } else {
            sensorAccelerometer =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        }
        try {
            field = PowerManager::class.java.javaClass.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK")
                    .getInt(null)
        } catch (ignored: Throwable) {
            powerManager = getSystemService(POWER_SERVICE) as PowerManager;
            wakeLock = powerManager!!.newWakeLock(field, getLocalClassName());
        }

        val homeFragment= RoomFragment()
        val profFragment= ProfileFragment()
        val addCarFragment= CartFragment()

        frameLayout=findViewById(R.id.frameLayout)

        makeCurrentFragment(homeFragment)

        btmNav=findViewById(R.id.btmNav)
        btmNav.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.nav_home ->{makeCurrentFragment(homeFragment)
                    true
                }
                R.id.profile ->{makeCurrentFragment(profFragment)
                    true
                }
        R.id.map ->{
            supportFragmentManager.beginTransaction()
            R.id.frameLayout
            startActivity(Intent(this@DashboardActivity, MapsActivity::class.java))


               true
               }
                R.id.addCart ->{makeCurrentFragment(addCarFragment)
                    true
                }

                R.id.logout -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Logout")
                    builder.setMessage("DoYou Want to Exit ???")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    builder.setPositiveButton("Yes",
                            DialogInterface.OnClickListener { _, _ ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    UserDB.getInstance(this@DashboardActivity).getUserDAO().logout()
                                    withContext(Main){
                                        startActivity(Intent(this@DashboardActivity,LoginActivity::class.java))
                                    }
                                }
                            })
                    builder.setNegativeButton("No",
                            DialogInterface.OnClickListener { dialog, _ -> //if user select "No", just cancel this dialog and continue with app
                                dialog.cancel()
                            }
                    )
                    val alert: AlertDialog = builder.create()
                    alert.setCancelable(false)
                    alert.show()
                    true
                }

                else -> false
            }
        }
    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            // setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            flag = false        }
        else if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false        }
        return flag    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                accelerometerEventListener,
                sensorAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
                proximityEventListener,
                sensorProximity,
                SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
                gyroEventListener,
                sensorGyroscope,
                SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(accelerometerEventListener)
        sensorManager.unregisterListener(proximityEventListener)
        sensorManager.unregisterListener(gyroEventListener)
    }

    private val accelerometerEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcclValue = acclValue
            acclValue = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta = acclValue - lastAcclValue
            shake = shake * 0.9f + delta
            if (shake > 12) {
                logout()
                Toast.makeText(
                        this@DashboardActivity,
                        "Successfully Logged Out",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            UserDB.getInstance(this@DashboardActivity).getUserDAO().logout()
            withContext(Main){
                startActivity(Intent(this@DashboardActivity,LoginActivity::class.java))
            }
        }
    }

    private val proximityEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values[0]
            if (values <= 4) {
                if (!wakeLock?.isHeld!!) {
                    wakeLock?.acquire();
                } else {
                    if (wakeLock!!.isHeld) {
                        wakeLock!!.release();
                    }
                }
            } else {
                if (wakeLock!!.isHeld) {
                    wakeLock!!.release()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private val gyroEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val values = event!!.values[1]
            if (values < 0){
                startActivity(Intent(this@DashboardActivity,DashboardActivity::class.java))
            }
            else if (values > 0) {
//                startActivity(Intent(this@DashboardActivity,DashboardActivity::class.java))
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

}