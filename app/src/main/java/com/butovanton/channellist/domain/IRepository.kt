package com.butovanton.channellist.domain

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getChannels(): Flow<List<Channel>?>
}