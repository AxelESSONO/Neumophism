package com.axel.neumophism

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vimalcvs.switchdn.DayNightSwitch
import com.vimalcvs.switchdn.DayNightSwitchAnimListener
import soup.neumorphism.NeumorphButton
import soup.neumorphism.ShapeType


class MainActivity : AppCompatActivity() {

    private lateinit var seeAllButton: NeumorphButton
    private lateinit var dayNightSwitch: DayNightSwitch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seeAllButton = findViewById(R.id.seeAllButton)
        dayNightSwitch = findViewById(R.id.switchItem)
        seeAllButton.setShapeType(ShapeType.FLAT)


        dayNightSwitch.setDuration(50)
        dayNightSwitch.setIsNight(ThemeSettings.getInstance(this)!!.nightMode)
        dayNightSwitch.setListener { isNight ->
            ThemeSettings.getInstance(this@MainActivity)!!.nightMode = isNight
            ThemeSettings.getInstance(this@MainActivity)!!.refreshTheme()
        }

        dayNightSwitch.setAnimListener(object : DayNightSwitchAnimListener {
            override fun onAnimEnd() {
                val intent = Intent(this@MainActivity, this@MainActivity.javaClass)
                intent.putExtras(getIntent())
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }

            override fun onAnimValueChanged(v: Float) {}
            override fun onAnimStart() {}
        })

        seeAllButton.setOnClickListener {
            when (seeAllButton.getShapeType()) {
                ShapeType.PRESSED -> seeAllButton.setShapeType(ShapeType.FLAT)
                ShapeType.FLAT -> seeAllButton.setShapeType(ShapeType.PRESSED)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dayNightSwitch.setIsNight(ThemeSettings.getInstance(this)!!.nightMode)
    }

}