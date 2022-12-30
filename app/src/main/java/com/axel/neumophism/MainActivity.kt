package com.axel.neumophism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import soup.neumorphism.NeumorphButton
import soup.neumorphism.ShapeType

class MainActivity : AppCompatActivity() {

    private lateinit var seeAllButton: NeumorphButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seeAllButton = findViewById(R.id.seeAllButton)

        seeAllButton.setShapeType(ShapeType.FLAT)
        seeAllButton.setOnClickListener {

            when(seeAllButton.getShapeType()){
                ShapeType.PRESSED -> seeAllButton.setShapeType(ShapeType.FLAT)
                ShapeType.FLAT    -> seeAllButton.setShapeType(ShapeType.PRESSED)
            }
        }
    }
}