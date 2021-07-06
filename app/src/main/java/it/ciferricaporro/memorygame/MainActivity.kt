package it.ciferricaporro.memorygame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import it.ciferricaporro.memorygame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var bindingM: ActivityMainBinding
        lateinit var mp: MediaPlayer
        var mpState: Boolean = false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingM = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingM.root)
        setBottomNav()
        setOSTNow()
    }

    private fun setBottomNav(){
        val bottomNavigationView = bindingM.bottomNavigationView
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.isVisible = false
        supportActionBar?.hide()
    }

    private fun setOSTNow(){
        mp = MediaPlayer.create(this@MainActivity, R.raw.memorygameostreal)
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
}