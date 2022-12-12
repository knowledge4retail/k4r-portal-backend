package org.knowledge4retail.portalbackend.interfaces

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.knowledge4retail.portalbackend.application.K4RStoreService
import org.knowledge4retail.portalbackend.domain.K4RStore
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stores")
class K4RStoreController(
    private val k4RStoreService: K4RStoreService
) {

    @Operation(description = "Lists all K4R Store including aggregate object count metadata")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Successful Operation")]
    )
    @RequestMapping(
        value = ["aggregates"], produces = [MediaType.APPLICATION_JSON_VALUE], method = [RequestMethod.GET]
    )
    fun getAllStores(): ResponseEntity<List<K4RStore>> =
        ResponseEntity(k4RStoreService.getAllAggregates(), HttpStatus.OK)
}