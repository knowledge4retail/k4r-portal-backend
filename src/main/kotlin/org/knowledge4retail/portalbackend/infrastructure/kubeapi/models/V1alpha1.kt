package org.knowledge4retail.portalbackend.infrastructure.kubeapi.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class V1alpha1InterfaceList(
    val items: List<V1alpha1Interface>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class V1alpha1InterfaceSpec(
    val description: String,
    val interfaceType: String,
    val prettyName: String,
    val refDocs: String,
    val refFunctional: String,
    val serviceName: String,
    val version: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class V1alpha1Interface(
    val spec: V1alpha1InterfaceSpec,
)
