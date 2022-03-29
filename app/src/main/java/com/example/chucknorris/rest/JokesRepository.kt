package com.example.chucknorris.rest

import com.example.chucknorris.models.Jokes
import retrofit2.Response

interface JokesRepository {
    suspend fun getARandomJoke(oneRandomJoke: String): Response<List<Jokes>>
}
class JokesRepositoryImpl(
    private val services: Services
): JokesRepository{

    override suspend fun getARandomJoke(oneRandomJoke: String): Response<List<Jokes>> {
       return services.getARandomJoke(oneRandomJoke = oneRandomJoke)
    }
}

