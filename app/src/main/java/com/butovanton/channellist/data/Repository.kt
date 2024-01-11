package com.butovanton.channellist.data

import com.butovanton.channellist.domain.Channel
import com.butovanton.channellist.domain.IRepository

class Repository(private val service: IService) : IRepository {
    override suspend fun getChannels(): List<Channel>? =
        runCatching {
            service.getChannels().map {
                Channel(
                    it.name,
                    it.url,
                    it.icon
                )
            }
        }.getOrNull()
}

class ChannelResponse(
    val name: String,
    val url: String,
    val icon: String?
)