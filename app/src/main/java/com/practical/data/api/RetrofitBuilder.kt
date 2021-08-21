package com.practical.data.api

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.practical.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private const val cacheSize = 5 * 1024 * 1024.toLong() // 5 MB
    private const val TAG = "ServiceGenerator"
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"
    var CONNECTION_TIMEOUT = 15000
    private const val BASE_URL = "https://newsapi.org/v2/"
    internal val loggingInterceptor: HttpLoggingInterceptor
        get() = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }
    fun getClient(): OkHttpClient? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    internal fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
//            .addNetworkInterceptor(networkInterceptor()) // only used when network is on
//            .addInterceptor(offlineInterceptor()).
//            writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS).readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS).build()
//    }


//    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
//        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(
//            TAG, "log: http log: $message") })
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return httpLoggingInterceptor
//    }
//

//    private fun offlineInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            Log.d(TAG, "offline interceptor: called.")
//            var request = chain.request()
//            // prevent caching when network is on. For that we use the "networkInterceptor"
//
//                val cacheControl = CacheControl.Builder()
//                    .maxStale(7, TimeUnit.DAYS)
//                    .build()
//                request = request.newBuilder()
//                    .removeHeader(HEADER_PRAGMA)
//                    .removeHeader(HEADER_CACHE_CONTROL)
//                    .cacheControl(cacheControl)
//                    .build()
//
//            chain.proceed(request)
//        }
//    }

}