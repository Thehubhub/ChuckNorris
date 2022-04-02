package com.example.chucknorris.di

import android.content.Context
import androidx.room.Room
import com.example.chucknorris.database.DbRepo
import com.example.chucknorris.database.DbRepoImpl
import com.example.chucknorris.database.JokeDb
import com.example.chucknorris.database.JokesDAO
import com.example.chucknorris.rest.JokesRepository
import com.example.chucknorris.rest.JokesRepositoryImpl
import com.example.chucknorris.rest.Services
import com.example.chucknorris.viewmodel.JokesViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesNetworkService(okHttpClient: OkHttpClient): Services =
        Retrofit.Builder()
            .baseUrl(Services.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Services::class.java)

    fun providesGson(): Gson = Gson()

    fun providesJokesRepository(networksServices: Services): JokesRepository =
        JokesRepositoryImpl(networksServices)

    single {providesLoggingInterceptor()}
    single {providesOkHttpClient(get())}
    single {providesNetworkService(get())}
    single { providesGson() }
    single {providesJokesRepository(get())}
}

val applicationModule = module {
    fun providesJokeDb(context: Context): JokeDb =
        Room.databaseBuilder(
            context,
            JokeDb::class.java,
            "jokes-db"
        ).build()

    fun providesJokesDao(jokesDb: JokeDb): JokesDAO =
        jokesDb.getJokeDao()

    fun providesDbRepo(databaseDAO: JokesDAO): DbRepo =
        DbRepoImpl(databaseDAO)

    single { providesJokeDb(androidContext()) }
    single { providesJokesDao(get())}
    single { providesDbRepo(get())}
}

val viewModelModule = module {
    viewModel { JokesViewModel(get(), get(), get()) }
}
