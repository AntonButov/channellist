package com.butovanton.channellist.data

import com.butovanton.channellist.domain.Channel
import com.butovanton.channellist.domain.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val service: IService) : IRepository {
    override fun getChannels(): Flow<List<Channel>?> = flow {
        emit(
            runCatching {
                service.getChannels().channels?.map {
                    Channel(
                        it.name_ru,
                        it.url,
                        it.image
                    )
                }
            }.onFailure {
                it.printStackTrace()
            }.getOrNull()
        )
    }
}

data class ChannelWrapper(
    val channels: List<ChannelResponse>?
)

data class ChannelResponse(
    val name_ru: String,
    val url: String?,
    val image: String?
)