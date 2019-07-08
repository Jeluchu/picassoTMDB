package com.jeluchu.picassotmdb.data.api

import com.jeluchu.picassotmdb.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key: String): Call<MoviesResponse>

    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String): Call<MoviesResponse>

    @GET("search/movie")
    fun searchMovie(@Query("api_key") api_key: String, @Query("query") q: String): Call<MoviesResponse>
}