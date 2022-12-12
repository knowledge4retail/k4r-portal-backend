package org.knowledge4retail.portalbackend.infrastructure.kubeapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.Configuration
import io.kubernetes.client.openapi.apis.CoreV1Api
import io.kubernetes.client.openapi.apis.CustomObjectsApi
import io.kubernetes.client.openapi.models.V1Pod
import io.kubernetes.client.openapi.models.V1Service
import io.kubernetes.client.util.Config
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1InterfaceList
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1InterfaceSpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class KubeApiClient(
    @Value("\${portalbackend.kubeapi.token}") private val token: String,
    @Value("\${portalbackend.kubeapi.url}") private val apiUrl: String,
    @Value("\${portalbackend.kubeapi.namespace}") private val namespace: String,
    private val mapper: ObjectMapper,
) {

    private var client: ApiClient = Config.fromToken(apiUrl, token, false)
    private val logger = mu.KotlinLogging.logger(this::class.java.name)

    init {
        Configuration.setDefaultApiClient(client)
    }

    fun queryInterfaces(): List<V1alpha1Interface> {
        val objects = CustomObjectsApi().listNamespacedCustomObject(
            "knowledge4retail.org",
            "v1alpha1",
            namespace,
            "interfaces",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        return objects
            .mapToV1alpha1InterfaceList()
            .traceLog()
            .items
    }

    private fun Any.mapToV1alpha1InterfaceList(): V1alpha1InterfaceList = mapper.readValue(mapper.writeValueAsString(this))
    private fun V1alpha1InterfaceList.traceLog(): V1alpha1InterfaceList {
        logger.trace(this.toString())
        return this
    }

    fun queryServices(): List<V1Service> {
        val services =
            CoreV1Api().listNamespacedService(namespace, null, null, null, null, null, null, null, null, null, null)
        logger.trace(services.toString())
        return services.items
    }

    fun queryPods(): List<V1Pod> {
        val pods = CoreV1Api().listNamespacedPod(namespace, null, null, null, null, null, null, null, null, null, null)
        logger.trace(pods.toString())
        return pods.items
    }
}


