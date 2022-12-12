package org.knowledge4retail.portalbackend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletResponse

@Component
class CORSFilter : Filter {

    @Value("\${portalbackend.allowed-origins}")
    private val allowedOrigins: String? = null

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse, filterChain: FilterChain) {
        val response = servletResponse as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", allowedOrigins)
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH")
        response.setHeader("Access-Control-Allow-Headers", "*")
        filterChain.doFilter(servletRequest, servletResponse)
    }
}