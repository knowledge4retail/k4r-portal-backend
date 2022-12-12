package org.knowledge4retail.portalbackend.infrastructure.dtapi.models

data class V0StoreAggregate(
    val id: Int,
    val storeName: String,
    val storeNumber: String?,
    val addressCountry: String?,
    val addressState: String?,
    val addressCity: String?,
    val addressPostcode: String?,
    val addressStreet: String?,
    val addressStreetNumber: String?,
    val addressAdditional: String?,
    val longitude: Double?,
    val latitude: Double?,
    val cadPlanId: String?,
    val externalReferenceId: String?,
    val shelfCount: Int,
    val shelfLayerCount: Int,
    val barcodeCount: Int,
    val productCount: Int
)
