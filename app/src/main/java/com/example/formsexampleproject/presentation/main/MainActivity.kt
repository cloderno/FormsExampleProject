package com.example.formsexampleproject.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.formsexampleproject.R
import com.example.formsexampleproject.core.ConnectivityObserver
import com.example.formsexampleproject.core.ImageLoader
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    val tv by lazy { findViewById<TextView>(R.id.text) }
    val btnRu by lazy { findViewById<TextView>(R.id.btnRu) }
    val btnKz by lazy { findViewById<TextView>(R.id.btnKz) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityObserver = ConnectivityObserver.Base(applicationContext)

        lifecycleScope.launch {
            connectivityObserver.observe().collect { status ->
                tv.text = status.toString()
            }
        }

        btnRu.setOnClickListener {

        }

        btnKz.setOnClickListener {

        }
    }
}