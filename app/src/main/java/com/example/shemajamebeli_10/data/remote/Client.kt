package com.example.shemajamebeli_10.data.remote

import android.content.Context
import com.example.shemajamebeli_10.extensions.checkNetworkAvailability
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    fun getInstance(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getOfflineInterceptor(context))
            .addNetworkInterceptor(getOnlineInterceptor())
            .cache(getCache(context))
            .build()
    }

    private fun getOnlineInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    private fun getOfflineInterceptor(context: Context) = Interceptor { chain ->
        var request: Request = chain.request()
        if (!context.checkNetworkAvailability()) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    private fun getCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        return Cache(context.cacheDir, cacheSize)
    }

}

