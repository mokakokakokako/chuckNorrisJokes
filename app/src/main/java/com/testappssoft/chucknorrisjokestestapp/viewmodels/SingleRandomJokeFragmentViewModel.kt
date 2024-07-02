package com.testappssoft.chucknorrisjokestestapp.viewmodels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testappssoft.chucknorrisjokestestapp.model.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SingleRandomJokeFragmentViewModel @Inject constructor(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    val jokeText = MutableLiveData<String>()
    val errorText = MutableLiveData<String>()

    private val handler = Handler(Looper.getMainLooper())
    private val updateJokeRunnable = object : Runnable {
        override fun run() {
            fetchJoke()
            handler.postDelayed(this, 5 * 60 * 1000)
        }
    }

    init {
        handler.post(updateJokeRunnable)
    }

    fun fetchJoke() {
        viewModelScope.launch {
            val result = jokeRepository.getJoke()
            if (result.isNotEmpty()) {
                try {
                    val jsonObject = JSONObject(result)
                    val joke = jsonObject.getString("value")
                    jokeText.postValue(joke)
                } catch (e: Exception) {
                    e.printStackTrace()
                    errorText.postValue("Error parsing JSON: ${e.message}")
                }
            } else {
                errorText.postValue("Empty response from server")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(updateJokeRunnable)
    }
}


