package org.knowledge4retail.portalbackend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such K4R Component found")
class K4RComponentNotFoundException(uuid: String) : RuntimeException(uuid) {
    @Synchronized
    fun fillInStackTrace(): Throwable {
        return this
    }
}