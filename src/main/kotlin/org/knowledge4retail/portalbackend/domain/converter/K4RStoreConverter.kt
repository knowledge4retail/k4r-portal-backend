package org.knowledge4retail.portalbackend.domain.converter

import org.knowledge4retail.portalbackend.domain.K4RStore
import org.knowledge4retail.portalbackend.infrastructure.dtapi.models.V0StoreAggregate
import org.springframework.stereotype.Component

@Component
class K4RStoreConverter {
    fun fromV0StoreAggregate(v0StoreAggregate: V0StoreAggregate) = K4RStore(
        id = v0StoreAggregate.id,
        storeName = v0StoreAggregate.storeName,
        storeNumber = v0StoreAggregate.storeNumber,
        addressCountry = v0StoreAggregate.addressCountry,
        addressState = v0StoreAggregate.addressState,
        addressCity = v0StoreAggregate.addressCity,
        addressPostcode = v0StoreAggregate.addressPostcode,
        addressStreet = v0StoreAggregate.addressStreet,
        addressStreetNumber = v0StoreAggregate.addressStreetNumber,
        addressAdditional = v0StoreAggregate.addressAdditional,
        longitude = v0StoreAggregate.longitude.toString(),
        latitude = v0StoreAggregate.latitude.toString(),
        cadPlanId = v0StoreAggregate.cadPlanId,
        externalReferenceId = v0StoreAggregate.externalReferenceId,
        shelfCount = v0StoreAggregate.shelfCount,
        shelfLayerCount = v0StoreAggregate.shelfLayerCount,
        barcodeCount = v0StoreAggregate.barcodeCount,
        productCount = v0StoreAggregate.productCount
    )
}