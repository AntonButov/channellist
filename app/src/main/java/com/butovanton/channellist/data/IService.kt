package com.butovanton.channellist.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface IService {
    @Headers("X-Key: fh3487klskhjk2fh782kjhsdi72knjwfk7i2efdjbm")
    @GET("v1/playlist'")
    suspend fun getChannels(): List<ChannelResponse>
}