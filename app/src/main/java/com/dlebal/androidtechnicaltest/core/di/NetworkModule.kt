package com.dlebal.androidtechnicaltest.core.di

import com.dlebal.androidtechnicaltest.core.common.AppConstants
import com.dlebal.androidtechnicaltest.data.network.ApiClient
import com.dlebal.androidtechnicaltest.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // 🔍 Logs HTTP requests & responses
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)  // 🛠 Adds logging interceptor
            .connectTimeout(30, TimeUnit.SECONDS)  // ⏳ Connection timeout
            .readTimeout(30, TimeUnit.SECONDS)  // 📖 Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)  // ✍ Write timeout
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)  // 🔗 Sets base API URL
            .client(okHttpClient)  // 🚀 Uses OkHttpClient for networking
            .addConverterFactory(GsonConverterFactory.create())  // 📦 Converts JSON to Kotlin objects
            .build()
    }

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
}