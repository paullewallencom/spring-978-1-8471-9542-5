package com.webflow2book;

import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 * Tests for chapter 6. Supposed to fail!
 *
 * @author Sven
 */
public class MyTestWithoutDB extends AbstractXmlFlowExecutionTests {

	private PersistenceExceptionHandler persistenceExceptionHandler;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.persistenceExceptionHandler = new PersistenceExceptionHandler();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.webflow.test.execution.
	 * AbstractExternalizedFlowExecutionTests
	 * #getResource(org.springframework.webflow
	 * .config.FlowDefinitionResourceFactory)
	 */
	@Override
	protected FlowDefinitionResource getResource(
			FlowDefinitionResourceFactory resourceFactory) {
		return resourceFactory
				.createFileResource("src/main/webapp/WEB-INF/flows/loginform.xml");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.webflow.test.execution.
	 * AbstractExternalizedFlowExecutionTests
	 * #configureFlowBuilderContext(org.springframework
	 * .webflow.test.MockFlowBuilderContext)
	 */
	@Override
	protected void configureFlowBuilderContext(
			MockFlowBuilderContext builderContext) {
		builderContext.registerBean("userservice", new UserServiceImpl());
		builderContext.registerBean("persistenceExceptionHandler", this.persistenceExceptionHandler);
	}

    /**
     * Test the start of the flow
     */
	public void testStartLoginFlow() {
		MockExternalContext context = new MockExternalContext();

		startFlow(context);

		assertFlowExecutionActive();
		assertCurrentStateEquals("loginPage");
	}

    /**
     * Testcase for a valid login
     */
	public void testValidLogin() {
		// Create user
		User user = this.createValidUser();

		// Standard Web Flow tests
		this.setCurrentState("loginPage");

		this.getFlowScope().put("user", user);

		MockExternalContext context = new MockExternalContext();
		context.setEventId("login");
		this.resumeFlow(context);

		assertFlowExecutionOutcomeEquals("welcomeView");
		assertFlowExecutionEnded();
	}

    /**
     * Test for an invalid login
     * @throws java.lang.Exception
     */
	public void testInvalidLogin() {
		User user = this.createInvalidUser();

		this.setCurrentState("loginPage");

		this.getFlowScope().put("user", user);

		MockExternalContext context = new MockExternalContext();
		context.setEventId("login");
		this.resumeFlow(context);

		assertFlowExecutionOutcomeEquals("failedView");
		assertFlowExecutionEnded();
	}

    /**
     * Private helper method that creates a valid user
     * @return A valid User
     */
	private User createValidUser() {
		User user = new User();
		user.setUsername("John");
		user.setPassword("myPass!");

		return user;
	}

    /**
     * Private helper method that creates an invalid user
     * @return An invalid User
     */
	private User createInvalidUser() {
		User user = new User();
		user.setUsername("Jane");
		user.setPassword("Doe");

		return user;
	}

}
