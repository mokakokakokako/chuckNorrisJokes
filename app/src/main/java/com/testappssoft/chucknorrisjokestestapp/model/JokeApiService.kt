package com.testappssoft.chucknorrisjokestestapp.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class JokeApiService @Inject constructor() {

    suspend fun getJoke(): String = withContext(Dispatchers.IO) {
        val urlString = "https://api.chucknorris.io/jokes/random"
        var resultToDisplay = ""
        var inputStream: java.io.InputStream? = null

        try {
            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            inputStream = urlConnection.inputStream

            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            resultToDisplay = stringBuilder.toString()
            Log.d("JokeApiService", "Response: $resultToDisplay")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        resultToDisplay
    }
}
