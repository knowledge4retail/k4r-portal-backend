package org.knowledge4retail.portalbackend.domain

import io.swagger.v3.oas.annotations.media.Schema
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface

@Schema(enumAsRef = true)
enum class K4RComponentType {
    WEBUI, API, ADMINISTRATIVE
}

data class K4RComponent(
    val serviceName: String,
    val prettyName: String,
    val description: String,
    val version: String,
    val type: K4RComponentType,
    val refFunctional: String,
    val refHealth: String,
    val refDocs: String,
    val newVersion: K4RComponentVersionInfo?
)

data class K4RComponentVersionInfo(
    val version: String, val url: String
)

@Schema(enumAsRef = true)
enum class K4RComponentHealthStatus {
    RUNNING, PENDING, TERMINATED
}

data class K4RComponentHealth(
    val serviceName: String, val status: K4RComponentHealthStatus
)
