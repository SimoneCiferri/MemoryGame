package it.ciferricaporro.memorygame.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val userDao = UserDatabase.getDatabase(application).userDao()
    private var count: Int = 0
    private val tag = "DB"
    private val check = "Counting"

    init{
        readAllData = userDao.readAllData()
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
        userDao.addUser(user)
        }
    }

    fun getC(): Int{
        val job =  (viewModelScope.launch(Dispatchers.IO) {
            count = userDao.getCount()
        })
        while(job.isActive){
            Log.i(tag, check)
        }
        return count
    }

}