package com.bpmn2.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

import com.bpmn2.camunda.component.EmailService
import com.bpmn2.camunda.component.PicsumIntegration

@Component("sendEmailDelegate")
class SendEmailDelegate(val emailService: EmailService,
                        val picsumSvc: PicsumIntegration): JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val email = execution.getVariableLocal("p_email") as String

        val emailSubject: String = (execution.getVariableLocal("p_email_subject") ?: "") as String
        val emailText: String = (execution.getVariableLocal("p_email_text") ?: "") as String

        val pictureBytes: ByteArray? = picsumSvc.getPicture()

        emailService.sendEmail(
                to = email,
                subject = emailSubject,
                text = emailText,
                attachment = pictureBytes
        )
    }
}
