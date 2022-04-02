package com.example.chucknorris.database

import com.example.chucknorris.models.Jokes
import com.example.chucknorris.models.Value

interface DbRepo {
    suspend fun insertValue(newJokes: List<Jokes>)
    suspend fun getAllJokes(): List<Jokes>
    suspend fun deleteAllJokes(jokes: List<Jokes>)
}

class DbRepoImpl(
    private val jokesDb: JokesDAO)
    : DbRepo{
    override suspend fun insertValue(newJokes: List<Jokes>) =
        jokesDb.insertValue(newJokes)

    override suspend fun getAllJokes(): List<Jokes> {
        return jokesDb.getAllJokes()
    }

    override suspend fun deleteAllJokes(jokes: List<Jokes>) {
        return jokesDb.deleteAllJokes(jokes)
    }
}