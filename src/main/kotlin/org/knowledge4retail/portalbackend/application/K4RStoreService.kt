package org.knowledge4retail.portalbackend.application

import org.knowledge4retail.portalbackend.domain.K4RStore
import org.knowledge4retail.portalbackend.domain.converter.K4RStoreConverter
import org.knowledge4retail.portalbackend.infrastructure.DigitalTwinRepository
import org.springframework.stereotype.Service

@Service
class K4RStoreService(
    private val repository: DigitalTwinRepository, private val converter: K4RStoreConverter
) {
    fun getAllAggregates(): List<K4RStore> = repository.getStores().map {
        converter.fromV0StoreAggregate(it)
    }
}