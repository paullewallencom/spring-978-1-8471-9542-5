package flowtrac.core.exceptions;

import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.TargetStateResolver;
import org.springframework.webflow.engine.Transition;
import org.springframework.webflow.engine.support.DefaultTargetStateResolver;
import org.springframework.webflow.execution.FlowExecutionException;

public class CustomExceptionHandler implements FlowExecutionExceptionHandler {

	/**
	 * @see org.springframework.webflow.engine.FlowExecutionExceptionHandler#canHandle(org.springframework.webflow.execution.FlowExecutionException)
	 */
	@Override
	public boolean canHandle(FlowExecutionException ex) {
		return ex.getCause().getClass().isAssignableFrom(
				IllegalStateException.class);
	}

	/**
	 * @see org.springframework.webflow.engine.FlowExecutionExceptionHandler#handle(org.springframework.webflow.execution.FlowExecutionException,
	 *      org.springframework.webflow.engine.RequestControlContext)
	 */
	@Override
	public void handle(FlowExecutionException ex, RequestControlContext ctx) {
		TargetStateResolver resolver = new DefaultTargetStateResolver("onError");
		Transition targetTransition = new Transition(resolver);
		ctx.execute(targetTransition);
	};
}
