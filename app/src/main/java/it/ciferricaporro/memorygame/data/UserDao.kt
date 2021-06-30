package it.ciferricaporro.memorygame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY score DESC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT COUNT(*) FROM user_table")
    fun getCount():Int
}