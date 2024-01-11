package com.butovanton.channellist.data

interface IService {
    suspend fun getChannels(): List<ChannelResponse>
}