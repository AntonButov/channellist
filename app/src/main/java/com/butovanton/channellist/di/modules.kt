package com.butovanton.channellist.di

import com.butovanton.channellist.data.FavoriteRepository
import com.butovanton.channellist.data.IFavoriteRepository
import com.butovanton.channellist.data.IService
import com.butovanton.channellist.data.Repository
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.TabsViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val baseUrl = "https://limeapi.online/api/"
private const val apiKey = "fh3487klskhjk2fh782kjhsdi72knjwfk7i2efdjbm"

val repositoryModule = module {
    val client = OkHttpClient().newBuilder().addInterceptor(Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("X-Key", apiKey)
        chain.proceed(requestBuilder.build())
    }).build()
    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    single<IRepository> { Repository(retrofit.create(IService::class.java)) }
    single<IFavoriteRepository> { FavoriteRepository() }
}

val presentationModule = module {
    viewModelOf(::TabsViewModel)
}