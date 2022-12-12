package org.knowledge4retail.portalbackend.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.knowledge4retail.portalbackend.serviceNameFixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AppUtilsTest {
    @Autowired
    lateinit var appUtils: AppUtils

    @Value("\${portalbackend.publicFQDN}")
    lateinit var ownFqdn: String

    @Test
    fun shouldBuildHealthEndpointPathCorrectly() {
        val url = appUtils.buildHealthEndpointPath(serviceNameFixture)
        assertEquals("https://$ownFqdn/components/$serviceNameFixture/health", url)
    }
}