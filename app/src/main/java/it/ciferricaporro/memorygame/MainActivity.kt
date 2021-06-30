package it.ciferricaporro.memorygame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.ciferricaporro.memorygame.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var binding: ActivityMainBinding
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setOST()
        setContentView(binding.root)
        setBottomNav()


    }

    private fun setBottomNav(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.isVisible = false
        supportActionBar?.hide()
    }

    private fun setOST() = runBlocking{launch{
        val mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.memorygameost)
        //loop
        //mediaPlayer?.isLooping(true)
        mediaPlayer.start() }
    }
}