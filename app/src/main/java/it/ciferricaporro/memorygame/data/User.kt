package it.ciferricaporro.memorygame.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val player: String,
    val time: Double,
    //val Data: Time,
    val errors: Int
)
