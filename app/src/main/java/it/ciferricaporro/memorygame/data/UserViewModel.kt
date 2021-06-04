package it.ciferricaporro.memorygame.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val userDao = UserDatabase.getDatabase(application).userDao()

    init{
        readAllData = userDao.readAllData()
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
        userDao.addUser(user)

        }
    }
}