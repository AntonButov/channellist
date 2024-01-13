package com.butovanton.channellist.di

import com.butovanton.channellist.data.FavoriteRepository
import com.butovanton.channellist.data.IFavoriteRepository
import com.butovanton.channellist.data.IService
import com.butovanton.channellist.data.Repository
import com.butovanton.channellist.domain.IRepository
import com.butovanton.channellist.presentation.TabsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://limeapi.online/api/"

val repositoryModule = module {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    single<IRepository> { Repository(retrofit.create(IService::class.java)) }
    single<IFavoriteRepository> { FavoriteRepository() }
}

val presentationModule = module {
    viewModelOf(::TabsViewModel)
}