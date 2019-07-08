package com.jeluchu.picassotmdb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeluchu.picassotmdb.data.api.RetrofitClient
import com.jeluchu.picassotmdb.data.model.Movie
import com.jeluchu.picassotmdb.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInteractor {


    fun getPopularMovies(): LiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        RetrofitClient.getPopularMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.postValue(null)
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                data.postValue(response.body()?.results)
            }

        })
        return data
    }

    fun getSimpsonsMovies(): LiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        RetrofitClient.getSimpsonsMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.postValue(null)
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                data.postValue(response.body()?.results)
            }

        })
        return data
    }
}