package com.lab7.di

import android.content.Context
import androidx.room.Room
import com.lab7.data.ServerApi
import com.lab7.data.database.AppDatabase
import com.lab7.ui.screens.articles.ArticlesScreenViewModel
import com.lab7.ui.screens.articles.FavoritesScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Base URL of API
private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

val appModule = module {

    /**
     * Initializing of the Retrofit instance, which generate API schema from ServerApi interface
     */
    single<ServerApi> {
        val client = OkHttpClient() // unique client for server
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) // interceptor here just makes pretty logs of each request in the LogCat
        val clientBuilder: OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL) // base url set here
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter factory converts pure json to your data classes
            .client(clientBuilder.build()) // add client here
            .build()
            .create(ServerApi::class.java) // creating schema of requests by retrofit
    }

    // Database instance
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "Lab7database"
        ).fallbackToDestructiveMigration().build()
    }

    // DAO
    single { get<AppDatabase>().articleDao() }

    // ViewModel for ArticlesScreen
    viewModel { ArticlesScreenViewModel(get(), get()) }

    // ViewModel for FavoritesScreen
    viewModel { FavoritesScreenViewModel(get()) }
}
