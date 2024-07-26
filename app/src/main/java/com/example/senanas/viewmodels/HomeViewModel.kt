package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.modelDto.CategoryDto
import com.example.senanas.network.user.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val userRepository: UserRepository) {
    private val _categories = MutableLiveData<Result<List<CategoryDto>?>>()
    val categories: LiveData<Result<List<CategoryDto>?>> = _categories
    fun getCategories(token:String) {
        val call = userRepository.getCategory(token)
        call.enqueue(object : Callback<List<CategoryDto>> {
            override fun onResponse(p0: Call<List<CategoryDto>>, p1: Response<List<CategoryDto>>) {
                if(p1.isSuccessful) {
                    val data = p1.body()
                    _categories.postValue(Result.success(data))
                }
            }

            override fun onFailure(p0: Call<List<CategoryDto>>, p1: Throwable) {
            }
        })
    }
}
