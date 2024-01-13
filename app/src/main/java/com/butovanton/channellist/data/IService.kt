package com.butovanton.channellist.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface IService {
    @GET("v1/playlist")
    suspend fun getChannels(): ChannelWrapper
}