package org.knowledge4retail.portalbackend.interfaces

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.internal.toImmutableList
import org.junit.jupiter.api.Test
import org.knowledge4retail.portalbackend.domain.K4RComponent
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.KubeApiClient
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.knowledge4retail.portalbackend.k4rComponentFixture
import org.knowledge4retail.portalbackend.util.AppUtils
import org.knowledge4retail.portalbackend.v1ServiceListFixture
import org.knowledge4retail.portalbackend.v1alpha1InterfaceListFixture
import org.mockito.BDDMockito.given
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
internal class K4RComponentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var appUtils: AppUtils

    @MockBean
    private lateinit var kubeApiClient: KubeApiClient

    @Test
    @DirtiesContext
    fun shouldReturnOkWithEmptyArrayJsonWhenRepositoryIsEmpty() {
        given(kubeApiClient.queryInterfaces()).willAnswer { emptyList<V1alpha1Interface>() }

        mockMvc.get("/components").andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json("[]")
            }
        }.andDo {
            handle {
                println(it.response.contentAsString)
            }
        }
    }

    @Test
    @DirtiesContext
    fun shouldReturnOkAndComponentsJson() {
        given(kubeApiClient.queryInterfaces()).willAnswer{ v1alpha1InterfaceListFixture.toImmutableList() }

        val expected = listOf(k4rComponentFixture.copy(refHealth = appUtils.buildHealthEndpointPath(k4rComponentFixture.serviceName)))

        mockMvc.get("/components").andExpect {
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
    private fun convertObjectToJsonString(obj: List<K4RComponent>): String {
        return jacksonObjectMapper().writeValueAsString(obj)
    }
}