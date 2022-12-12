package org.knowledge4retail.portalbackend

import io.kubernetes.client.openapi.models.V1ObjectMeta
import io.kubernetes.client.openapi.models.V1Service
import org.knowledge4retail.portalbackend.domain.K4RComponent
import org.knowledge4retail.portalbackend.domain.K4RComponentType
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1InterfaceSpec

const val serviceNameFixture = "k4r-dt-api"

val k4rComponentFixture = K4RComponent(
    serviceName = serviceNameFixture,
    prettyName = "DT-API Rest - Swagger",
    description = "my K4R Program",
    version = "0.0.1",
    type = K4RComponentType.WEBUI,
    refFunctional = "http://knowledge4retail.local/k4r/swagger-ui/index.html",
    refHealth = "",
    refDocs = "https://www.knowledge4retail.org/docs/dt-api",
    newVersion = null
)

val v1alpha1InterfaceFixture = V1alpha1Interface(spec = V1alpha1InterfaceSpec(
    description = "my K4R Program",
    interfaceType = "WEBUI",
    prettyName = "DT-API Rest - Swagger",
    refDocs = "https://www.knowledge4retail.org/docs/dt-api",
    refFunctional = "http://knowledge4retail.local/k4r/swagger-ui/index.html",
    serviceName = serviceNameFixture,
    version = "0.0.1",
))

val v1alpha1InterfaceListFixture = listOf(v1alpha1InterfaceFixture)

fun metadata(): V1ObjectMeta {
    var metadata = V1ObjectMeta()
    metadata.name = serviceNameFixture
    return metadata
}
val v1ServiceFixture: V1Service = V1Service().metadata(metadata())

val v1ServiceListFixture = listOf(v1ServiceFixture)