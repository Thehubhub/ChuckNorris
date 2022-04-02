package com.example.chucknorris.rest

import com.example.chucknorris.models.Jokes
import com.example.chucknorris.models.Value
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Services {

    @GET(RANDOM_JOKE)
    suspend fun getARandomJoke(
        @Query("something_random") oneRandomJoke: String = RANDOM_JOKE
    ): Response<Jokes>


    @GET(LIST_OF_JOKES)
    suspend fun getNeverEndingList(
        @Query("never_ending") foreverList: String,
        @Path("load_size") number: Int = JOKES_LOAD
    ): Response<Jokes>



    @GET(RANDOM_JOKE)
    suspend fun getCustomName(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String
    ): Response<Value>

    companion object{
        const val BASE_URL="https://api.icndb.com/jokes/"
        private const val RANDOM_JOKE = "random"
        private const val LIST_OF_JOKES = "random/{load_size}"
        const val JOKES_LOAD = 20


    }
}