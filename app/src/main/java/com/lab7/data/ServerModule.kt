package com.lab7.data

import com.lab7.data.entity.ApiResponse
import com.lab7.data.entity.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ServerApi - interface with functions-mappers for API requests
 * - will be used by Retrofit library to generate convenient API class of communication with API
 */
interface ServerApi {

    // Fetch a paginated list of articles
    @GET("/v4/articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0
    ): ApiResponse

    // Fetch details of a specific article
    @GET("/v4/articles/{id}")
    suspend fun getArticleDetails(
        @Path("id") articleId: Int
    ): Article

    
}