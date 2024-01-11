package com.butovanton.channellist.domain

interface IRepository {
    suspend fun getChannels(): List<Channel>
}