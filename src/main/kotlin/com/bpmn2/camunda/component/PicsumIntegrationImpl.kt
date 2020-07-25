package com.bpmn2.camunda.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PicsumIntegrationImpl: PicsumIntegration {
    @Value("\${app.pics.api.url}")
    private val picsumBaseUrl: String? = null

    private val rest = RestTemplate()

    override fun getPicture(): ByteArray? {
        val response = rest.getForEntity("$picsumBaseUrl/500/300", ByteArray::class.java)
        if (response.statusCode != HttpStatus.OK)
            return null

        return response.body
    }
}