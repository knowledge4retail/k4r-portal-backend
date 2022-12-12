package org.knowledge4retail.portalbackend.infrastructure.dtapi

import org.knowledge4retail.portalbackend.infrastructure.dtapi.models.V0StoreAggregate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class DtApiClient(
    @Value("\${portalbackend.dtapi.url}") private val apiUrl: String,
    private val restTemplate: RestTemplate = RestTemplate()
) {
    fun getStoreAggregates(): List<V0StoreAggregate> =
        restTemplate.getForObject(apiUrl+"/stores/aggregates", Array<V0StoreAggregate>::class.java)?.toList() ?: emptyList()
}