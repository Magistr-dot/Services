package com.frigate.services

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frigate.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bService.setOnClickListener {
            startService(MyService.newIntent(this))
        }
    }
}