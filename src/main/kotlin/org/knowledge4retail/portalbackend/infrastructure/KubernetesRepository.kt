package org.knowledge4retail.portalbackend.infrastructure

import io.kubernetes.client.openapi.models.V1Service
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.KubeApiClient
import org.knowledge4retail.portalbackend.infrastructure.kubeapi.models.V1alpha1Interface
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository

@Repository
class KubernetesRepository(private val kubeApiClient: KubeApiClient, private val cacheManager: CacheManager) {
    private val logger = mu.KotlinLogging.logger(this::class.java.name)

    companion object {
        const val interfacesCacheName = "k4rinterfaces"
    }

    @Cacheable(interfacesCacheName)
    fun getAllInterfaces(): List<V1alpha1Interface> {
        logger.debug("Getting interface CRDs from Kubernetes API. Caching in $interfacesCacheName")
        return kubeApiClient.queryInterfaces()
    }

    @Scheduled(fixedRateString = "\${portalbackend.cache.refresh-interval-ms.k4rinterfaces}")
    fun flushInterfacesCache() = flush(interfacesCacheName)

    private fun flush(cacheName: String) {
        logger.trace("Flushing cache. Cache name: $cacheName")
        cacheManager.cacheNames.stream().forEach { (cacheManager.getCache(cacheName)?.clear()) }
    }
}