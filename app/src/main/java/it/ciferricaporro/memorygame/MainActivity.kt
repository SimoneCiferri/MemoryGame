package it.ciferricaporro.memorygame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.ciferricaporro.memorygame.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_memory_game.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var mp: MediaPlayer
        val MP_STATE = "mpState"
        var mpState: Boolean = false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setBottomNav()
        setOSTNow()

    }

    private fun setBottomNav(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.isVisible = false
        supportActionBar?.hide()
    }

    private fun setOSTNow(){
        mp = MediaPlayer.create(this@MainActivity, R.raw.memorygameost)
        mp.isLooping = true
        mp.start()
        mpState = true
    }

    override fun onPause() {
        super.onPause()
        mp.pause()
    }

    override fun onResume() {
        super.onResume()
        if(mpState){
            mp.start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(MP_STATE, mp.isPlaying)
        super.onSaveInstanceState(outState)
    }
}