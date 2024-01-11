package com.butovanton.channellist

import com.butovanton.channellist.data.ChannelResponse
import com.butovanton.channellist.data.IService
import com.butovanton.channellist.data.Repository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class RepositoryTest {

    @Test
    fun `on getChannels should return list of channels`() = runBlocking{
        val service = mockk<IService>()
        coEvery {
            service.getChannels()
        } returns listOf(ChannelResponse(name = "name", url = "url", icon = "icon"))
        val repositoryTest = Repository(service)
        val result = repositoryTest.getChannels()?.first()
        assertNotNull(result)
        assertEquals("name", result!!.name)
        assertEquals("url", result.url)
        assertEquals("icon", result.icon)
    }

    @Test
    fun `on exception getChannels should return null`() = runBlocking{
        val service = mockk<IService>()
        coEvery {
            service.getChannels()
        } throws Throwable()
        val repositoryTest = Repository(service)
        val result = repositoryTest.getChannels()
        assertNull(result)
    }


}