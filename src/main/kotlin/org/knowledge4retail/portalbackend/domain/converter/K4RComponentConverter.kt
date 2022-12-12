package org.knowledge4retail.portalbackend.domain.converter

import org.knowledge4retail.portalbackend.domain.K4RComponent
import org.knowledge4retail.portalbackend.domain.K4RComponentType
import org.knowledge4retail.portalbackend.domain.K4RComponentVersionInfo
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.knowledge4retail.portalbackend.util.AppUtils
import org.springframework.stereotype.Component

@Component
class K4RComponentConverter(private val appUtils: AppUtils) {

    fun fromV1alpha1Interface(v1alpha1Interface: V1alpha1Interface, newVersion: K4RComponentVersionInfo?) = K4RComponent(
        serviceName = v1alpha1Interface.spec.serviceName,
        prettyName = v1alpha1Interface.spec.prettyName,
        description = v1alpha1Interface.spec.description,
        version = v1alpha1Interface.spec.version,
        type = mapInterfaceType(v1alpha1Interface.spec.interfaceType),
        refFunctional = v1alpha1Interface.spec.refFunctional,
        refHealth = appUtils.buildHealthEndpointPath(v1alpha1Interface.spec.serviceName),
        refDocs = v1alpha1Interface.spec.refDocs,
        newVersion = newVersion
    )

    private fun mapInterfaceType(v1alpha1InterfaceType: String): K4RComponentType =
        when(v1alpha1InterfaceType) {
            "ADMINISTRATION"-> K4RComponentType.ADMINISTRATIVE
            else -> K4RComponentType.valueOf(v1alpha1InterfaceType)
        }
}