package com.swaptech.workula.di.network

import com.swaptech.workula.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

@Module
class InterceptorModule {

    @Provides
    fun provideRequestInterceptor(): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
                .addHeader("AUTHORIZATION", BuildConfig.CLIENT_SECRET)
            val newRequest = builder.build()
            return chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        )
}