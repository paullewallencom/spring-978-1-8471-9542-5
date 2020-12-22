package com.webflow2book;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class ShoppingCartTests extends AbstractXmlFlowExecutionTests {

	private PaymentService paymentService;
	
	@Override
	public void setUp() {
		this.paymentService = createMock(PaymentService.class);
	}
	
    @Override
	protected FlowDefinitionResource getResource(
			FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory
				.createFileResource("src/main/webapp/WEB-INF/flows/shoppingcart-flow.xml");
	}
	
    @Override
	protected void configureFlowBuilderContext(
			MockFlowBuilderContext builderContext) {
		builderContext.registerBean("paymentservice", this.paymentService);
	}
	
	public void testStartShoppingCartFlow() {
		MockExternalContext context = new MockExternalContext();

		startFlow(context);

		assertFlowExecutionActive();
		assertCurrentStateEquals("homepage");
	}

	public void testSuccessfulTransitionToContents() {
		this.setCurrentState("homepage");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("order");
		this.resumeFlow(context);

		assertFlowExecutionActive();
		assertCurrentStateEquals("shoppingCartContents");
	}

	public void testSuccessfulTransitionToPayment() {
		this.setCurrentState("shoppingCartContents");
		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		this.resumeFlow(context);

		assertFlowExecutionActive();
		assertCurrentStateEquals("payment");
	}

	public void testValidPayment() {
		Payment validPayment = createMock(Payment.class);

		expect(this.paymentService.checkPayment(validPayment)).andReturn(true);

		replay(this.paymentService, validPayment);

		this.setCurrentState("payment");

		MockExternalContext context = new MockExternalContext();
		context.setEventId("next");
		this.getFlowScope().put("chosenPaymentOption", validPayment);
		this.resumeFlow(context);

		boolean paymentValid = (Boolean)this.getFlowScope().get("paymentValid");
		
		assertTrue(paymentValid);
		assertFlowExecutionActive();
		assertCurrentStateEquals("review");

		verify(this.paymentService, validPayment);
	}
}
