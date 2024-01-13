package com.butovanton.channellist

import app.cash.turbine.test
import com.butovanton.channellist.data.ChannelResponse
import com.butovanton.channellist.data.ChannelWrapper
import com.butovanton.channellist.data.IService
import com.butovanton.channellist.data.Repository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class RepositoryTest {

    @Test
    fun `on getChannels should return list of channels`() = runBlocking{
        val service = mockk<IService>()
        coEvery {
            service.getChannels()
        } returns ChannelWrapper(channels = listOf(ChannelResponse(name_ru = "name", url = "url", image = "icon")))
        val repositoryTest = Repository(service)
        val result = repositoryTest.getChannels().first()?.first()
        assertNotNull(result)
        assertEquals("name", result!!.name)
        assertEquals("url", result.url)
        assertEquals("icon", result.icon)
    }

    @Test
    fun `on exception getChannels should return null`() = runBlocking {
        val service = mockk<IService>()
        coEvery {
            service.getChannels()
        } throws Throwable()
        val repository = Repository(service)
        repository.getChannels().test {
            assertNull(awaitItem())
            awaitComplete()
        }
    }

}