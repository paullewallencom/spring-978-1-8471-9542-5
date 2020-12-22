package com.webflow2book;

import org.springframework.binding.expression.EvaluationException;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.TargetStateResolver;
import org.springframework.webflow.engine.Transition;
import org.springframework.webflow.engine.support.DefaultTargetStateResolver;
import org.springframework.webflow.execution.FlowExecutionException;

/**
 * Example of a custom exception handler. If an EvaluationException occurs in the
 * flow, we will forward to the "failedView"-state.
 * @author Sven
 */
public class PersistenceExceptionHandler implements
        FlowExecutionExceptionHandler {

    @Override
    public boolean canHandle(FlowExecutionException ex) {
        // Check if error happened while evaluating an expression
        return ex.getCause().getClass().isAssignableFrom(EvaluationException.class);
    }

    @Override
    public void handle(FlowExecutionException ex, RequestControlContext ctx) {
        // Transition to the "failedView"-state
        TargetStateResolver resolver = new DefaultTargetStateResolver(
                "failedView");
        Transition targetTransition = new Transition(resolver);
        ctx.execute(targetTransition);
    }
    ;
}
