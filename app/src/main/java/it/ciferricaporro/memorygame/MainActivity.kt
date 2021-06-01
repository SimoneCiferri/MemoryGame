package it.ciferricaporro.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.ciferricaporro.memorygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUI(){

    }
}