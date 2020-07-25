package com.bpmn2.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

import com.bpmn2.camunda.component.CatFactsIntegration

@Component("catFactsDelegate")
class CatFactsDelegate(val catFacts: CatFactsIntegration): JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        val fact: String = catFacts.getFact()
        execution.setVariableLocal("p_cat_fact", fact)
    }
}