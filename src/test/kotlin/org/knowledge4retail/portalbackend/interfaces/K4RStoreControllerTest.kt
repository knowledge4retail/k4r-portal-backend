package org.knowledge4retail.portalbackend.interfaces

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.internal.toImmutableList
import org.junit.jupiter.api.Test
import org.knowledge4retail.portalbackend.*
import org.knowledge4retail.portalbackend.domain.K4RStore
import org.knowledge4retail.portalbackend.infrastructure.dtapi.DtApiClient
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class K4RStoreControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var dtApiClient: DtApiClient

    @Test
    @DirtiesContext
    fun shouldReturnOkAndStoresJson() {
        BDDMockito.given(dtApiClient.getStoreAggregates()).willAnswer{ v0StoreAggregateListFixture.toImmutableList() }

        val expected = k4rStoreListFixture.toImmutableList()

        mockMvc.get("/stores/aggregates").andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
            }
            content {
                json(convertObjectToJsonString(expected))
            }
        }.andDo {
            print()
            handle {
                println(it.response.contentAsString)
            }
        }
    }

    @Throws(JsonProcessingException::class)
    private fun convertObjectToJsonString(obj: List<K4RStore>): String {
        println(jacksonObjectMapper().writeValueAsString(obj))
        return jacksonObjectMapper().writeValueAsString(obj)
    }
}