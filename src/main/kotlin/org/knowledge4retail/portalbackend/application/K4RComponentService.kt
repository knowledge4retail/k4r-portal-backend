package org.knowledge4retail.portalbackend.application

import org.knowledge4retail.portalbackend.domain.K4RComponent
import org.knowledge4retail.portalbackend.domain.K4RComponentHealth
import org.knowledge4retail.portalbackend.domain.K4RComponentHealthStatus
import org.knowledge4retail.portalbackend.domain.K4RComponentVersionInfo
import org.knowledge4retail.portalbackend.domain.converter.K4RComponentConverter
import org.knowledge4retail.portalbackend.infrastructure.KubernetesRepository
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.springframework.stereotype.Service

@Service
class K4RComponentService(
    private val repository: KubernetesRepository, private val converter: K4RComponentConverter
) {

    fun getAll(): List<K4RComponent> = repository.getAllInterfaces().map {
        converter.fromV1alpha1Interface(it, checkForNewVersion(it))
    }

    //TODO
    fun getHealth(serviceName: String): K4RComponentHealth = K4RComponentHealth(serviceName, K4RComponentHealthStatus.RUNNING)

    fun checkForNewVersion(v1alpha1Interface: V1alpha1Interface): K4RComponentVersionInfo? {
        //todo after open sourcing when public repositories are available TNDEK4R-251
        return null
    }
}