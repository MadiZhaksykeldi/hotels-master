package ru.test.hotels.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.hotels.BuildConfig
import ru.test.hotels.model.data.server.RestApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = createOkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = createRetrofit(okHttpClient)

    @Provides
    @Singleton
    fun provideRestService(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(createConverterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    private fun createConverterFactory() = GsonConverterFactory.create(
        GsonBuilder()
            .create()
    )

    private fun createOkHttpClient() = OkHttpClient.Builder()
        //.addInterceptor(HeaderInterceptor())  todo
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(RestApi.MAX_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(RestApi.MAX_READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}