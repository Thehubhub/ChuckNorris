package com.example.chucknorris.rest

import com.example.chucknorris.models.Jokes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Services {

    @GET(RANDOM_JOKE)
    suspend fun getARandomJoke(
        @Query("something_random") oneRandomJoke: String = RANDOM_JOKE
    ): Response<List<Jokes>>


    @GET()
    suspend fun getNeverEndingList(
        @Query("never_ending") foreverList: String
    ): Response<List<Jokes>>

    companion object{
        const val BASE_URL="https://api.icndb.com/jokes/"
        private const val RANDOM_JOKE = "random"

    }
}