package com.bpmn2.camunda.component

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CatFactsIntegrationImpl: CatFactsIntegration {
    @Value("\${app.cats.api.url}")
    private val catFactsBaseUrl: String? = null

    private val rest = RestTemplate()
    var headers = HttpHeaders().also {
        it.contentType = MediaType.APPLICATION_JSON
    }

    private fun createHTTPEntity(json: String?): HttpEntity<String> {
        return HttpEntity(json, headers)
    }

    override fun getFact(): String {
        val response = rest
                .exchange(
                        "$catFactsBaseUrl/fact",
                        HttpMethod.GET,
                        createHTTPEntity(null),
                        String::class.java
                )

        if (response.statusCode != HttpStatus.OK)
            return ""

        val obj = JSONObject(response.body)

        return obj.getString("fact")
    }
}