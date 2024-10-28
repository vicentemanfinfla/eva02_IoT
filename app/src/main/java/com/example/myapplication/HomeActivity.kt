package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val titleTextView = findViewById<TextView>(R.id.textViewTitle)
        val buttonFeature1 = findViewById<Button>(R.id.buttonFeature1)
        val buttonFeature2 = findViewById<Button>(R.id.buttonFeature2)
        val buttonFeature3 = findViewById<Button>(R.id.buttonFeature3)

        titleTextView.text = "Dungeon Master Companion"

        buttonFeature1.setOnClickListener {
            startActivity(Intent(this, DevicesActivity::class.java))
        }

        buttonFeature2.setOnClickListener {
            startActivity(Intent(this, SFXActivity::class.java))
        }

        buttonFeature3.setOnClickListener {
            startActivity(Intent(this, PresetsActivity::class.java))
        }
    }
}