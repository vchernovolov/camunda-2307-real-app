package com.bpmn2.camunda.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.mail.internet.MimeMessage
import javax.mail.util.ByteArrayDataSource

@Component
class EmailServiceImpl(private val emailSender: JavaMailSender) : EmailService {
    @Value("\${spring.mail.username}")
    private val mailUserName: String? = null

    override fun sendEmail(to: String, subject: String, text: String, attachment: ByteArray?) {
        val from = this.mailUserName + ""
        if (attachment == null) {
            val message: SimpleMailMessage = SimpleMailMessage()

            message.setFrom(from)
            message.setTo(to)
            message.setSubject(subject)
            message.setText(text)

            emailSender.send(message)
        }
        else {
            val message: MimeMessage = emailSender.createMimeMessage()
            val helper: MimeMessageHelper = MimeMessageHelper(message, true)

            helper.setFrom(from)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(text)

            val attachmentStream: InputStream = ByteArrayInputStream(attachment)
            val attachmentStreamResource: ByteArrayDataSource =
                    ByteArrayDataSource(attachmentStream, "application/octet-stream")

            helper.addAttachment("attach.jpg", attachmentStreamResource)

            emailSender.send(message)
        }

    }
}
