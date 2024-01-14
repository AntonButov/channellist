package com.butovanton.channellist.data

import retrofit2.http.GET

interface IService {
    @GET("v1/playlist")
    suspend fun getChannels(): ChannelWrapper
}