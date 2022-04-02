package com.example.chucknorris.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.chucknorris.models.Jokes
import com.example.chucknorris.models.Value

@Database(
    entities = [Value::class],
    version = 1
)
abstract class JokeDb: RoomDatabase() {
    abstract fun getJokeDao(): JokesDAO
}

@Dao
interface JokesDAO{

    @Insert(onConflict = REPLACE)
    suspend fun insertValue(newJokes: List<Jokes>)

    @Query("SELECT * FROM jokes")
    suspend fun getAllJokes(): List<Jokes>

    @Query("SELECT * FROM value WHERE id = :searchId")
    suspend fun getJokeById(searchId: Int): Value

    @Delete
    suspend fun deleteAllJokes(jokes: List<Jokes>)


}