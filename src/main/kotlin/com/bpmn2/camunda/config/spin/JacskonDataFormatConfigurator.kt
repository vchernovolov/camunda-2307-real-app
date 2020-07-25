package com.bpmn2.camunda.config.spin

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat
import org.camunda.spin.spi.DataFormatConfigurator

open class JacksonDataFormatConfigurator : DataFormatConfigurator<JacksonJsonDataFormat> {

    override fun configure(dataFormat: JacksonJsonDataFormat) {
        dataFormat.objectMapper.registerModule(KotlinModule())
    }

    override fun getDataFormatClass(): Class<JacksonJsonDataFormat> =
            JacksonJsonDataFormat::class.java
}
