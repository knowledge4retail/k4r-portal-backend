package org.knowledge4retail.portalbackend.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppUtils(
    @Value("\${portalbackend.publicFQDN}") val publicFQDN: String
) {

    fun buildHealthEndpointPath(serviceName: String): String {
        return "https://$publicFQDN/components/$serviceName/health"
    }
}
