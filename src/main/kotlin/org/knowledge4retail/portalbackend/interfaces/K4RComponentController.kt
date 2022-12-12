package org.knowledge4retail.portalbackend.interfaces

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.knowledge4retail.portalbackend.application.K4RComponentService
import org.knowledge4retail.portalbackend.domain.K4RComponent
import org.knowledge4retail.portalbackend.domain.K4RComponentHealth
import org.knowledge4retail.portalbackend.exception.K4RComponentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/components")
class K4RComponentController(
    private val k4RComponentService: K4RComponentService
) {

    @Operation(description = "Lists all currently deployed K4R components.")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Successful Operation")]
    )
    @RequestMapping(
        value = [""], produces = [MediaType.APPLICATION_JSON_VALUE], method = [RequestMethod.GET]
    )
    fun getAllComponents(): ResponseEntity<List<K4RComponent>> =
        ResponseEntity(k4RComponentService.getAll(), HttpStatus.OK)

    @Operation(description = "Provides health information for the given k4r component.")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Successful Operation"
        ), ApiResponse(responseCode = "404", description = "Component with given service name not found")]
    )
    @RequestMapping(
        value = ["/{serviceName}/health"], produces = [MediaType.APPLICATION_JSON_VALUE], method = [RequestMethod.GET]
    )
    fun getComponentHealth(@PathVariable serviceName: String): ResponseEntity<K4RComponentHealth> = try {
        ResponseEntity(k4RComponentService.getHealth(serviceName), HttpStatus.OK)
    } catch (e: NoSuchElementException) {
        throw K4RComponentNotFoundException(serviceName)
    }

}