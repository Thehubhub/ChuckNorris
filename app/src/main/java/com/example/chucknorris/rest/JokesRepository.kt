package com.example.chucknorris.rest

import com.example.chucknorris.models.Jokes
import com.example.chucknorris.models.Value
import retrofit2.Response

interface JokesRepository {
    suspend fun getARandomJoke(oneRandomJoke: String): Response<Jokes>
    suspend fun getNeverEndingList(foreverList: String, number: Int = Services.JOKES_LOAD): Response<Jokes>
    suspend fun getCustomName(firstName: String, lastName: String): Response<Value>
}
class JokesRepositoryImpl(
    private val services: Services
): JokesRepository{
    override suspend fun getARandomJoke(oneRandomJoke: String): Response<Jokes> =
        services.getARandomJoke(oneRandomJoke)

    override suspend fun getNeverEndingList(foreverList: String, number: Int): Response<Jokes> =
        services.getNeverEndingList(foreverList, number)

    override suspend fun getCustomName(firstName: String, lastName: String): Response<Value> =
        services.getCustomName(firstName, lastName)
}

