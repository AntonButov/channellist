package com.butovanton.channellist.data

import com.butovanton.channellist.domain.Channel
import com.butovanton.channellist.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val service: IService) : IRepository {
    override fun getChannels(): Flow<List<Channel>?> = flow {
        emit(
            runCatching {
                service.getChannels().map {
                    Channel(
                        it.name,
                        it.url,
                        it.icon
                    )
                }
            }.getOrNull()
        )
    }
}

class ChannelResponse(
    val name: String,
    val url: String?,
    val icon: String?
)