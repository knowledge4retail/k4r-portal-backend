package org.knowledge4retail.portalbackend.infrastructure

import org.knowledge4retail.portalbackend.infrastructure.dtapi.DtApiClient
import org.knowledge4retail.portalbackend.infrastructure.dtapi.models.V0StoreAggregate
import org.springframework.stereotype.Repository
import java.net.ConnectException

@Repository
class DigitalTwinRepository(private val dtApiClient: DtApiClient) {
    private val logger = mu.KotlinLogging.logger(this::class.java.name)

    fun getStores(): List<V0StoreAggregate> {
        logger.debug("Getting stores from Digital Twin API.")
        val result = dtApiClient.getStoreAggregates()
        logger.debug("Found ${result.size}")
        logger.trace(result.toString())
        return result
    }
}