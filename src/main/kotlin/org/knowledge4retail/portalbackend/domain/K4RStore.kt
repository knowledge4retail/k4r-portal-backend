package org.knowledge4retail.portalbackend.domain

data class K4RStore (
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
    val longitude: String?,
    val latitude: String?,
    val cadPlanId: String?,
    val externalReferenceId: String?,
    val shelfCount: Int,
    val shelfLayerCount: Int,
    val barcodeCount: Int,
    val productCount: Int
)