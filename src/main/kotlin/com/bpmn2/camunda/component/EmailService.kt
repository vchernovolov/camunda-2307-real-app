package com.bpmn2.camunda.component

interface EmailService {
    fun sendEmail(to: String, subject: String, text: String, attachment: ByteArray?)
}
