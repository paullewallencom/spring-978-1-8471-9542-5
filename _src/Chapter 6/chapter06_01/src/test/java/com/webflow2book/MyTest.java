package com.webflow2book;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.binding.mapping.Mapper;
import org.springframework.binding.mapping.MappingResults;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.engine.EndState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 * Tests for chapter 6
 * 
 * @author Sven
 */
public class MyTest extends AbstractXmlFlowExecutionTests {

	private UserService userService;
	private EntityManager entityManager;
	private PersistenceExceptionHandler persistenceExceptionHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.userService = new UserServiceImpl();
		this.entityManager = createStrictMock(EntityManager.class);
		this.userService.setEntityManager(this.entityManager);
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
		// return
		// resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/subflow.xml");
		// // To test subflows
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
		builderContext.registerBean("userservice", this.userService);
		builderContext.registerBean("persistenceExceptionHandler",
				this.persistenceExceptionHandler);
	}

	/**
	 * Test the start of the flow
	 */
	public void testStartLoginFlow() {
		MockExternalContext context = new MockExternalContext();

		this.startFlow(context);

		assertFlowExecutionActive();
		assertCurrentStateEquals("loginPage");
	}

	/**
	 * Testcase for a valid login
	 */
	public void testValidLogin() {
		// Create user
		User user = this.createValidUser();

		Query query = createMock(Query.class);

		// EasyMock
		// Expect certain method calls on the EntityManager
		expect(
				this.entityManager
						.createQuery("select user from com.webflow2book.User user "
								+ "where user.username like :username"))
				.andReturn(query);
		expect(query.setParameter("username", user.getUsername())).andReturn(
				query);
		expect(query.getSingleResult()).andReturn(user);

		replay(this.entityManager, query);

		// Standard Web Flow tests
		this.setCurrentState("loginPage");

		this.getFlowScope().put("user", user);

		MockExternalContext context = new MockExternalContext();
		context.setEventId("login");
		this.resumeFlow(context);

		assertFlowExecutionOutcomeEquals("success");
		assertFlowExecutionEnded();
		verify(this.entityManager, query);
	}

	/**
	 * Test for an invalid login
	 * 
	 * @throws java.lang.Exception
	 */
	public void testInvalidLogin() throws Exception {
		User user = this.createInvalidUser();

		Query query = createMock(Query.class);
		// Again we expect certain specific calls to the EntityManager
		expect(
				this.entityManager
						.createQuery("select user from com.webflow2book.User user "
								+ "where user.username like :username"))
				.andReturn(query);
		expect(query.setParameter("username", user.getUsername())).andReturn(
				query);
		expect(query.getSingleResult()).andThrow(new NoResultException());
		replay(this.entityManager, query);

		this.setCurrentState("loginPage");

		this.getFlowScope().put("user", user);

		MockExternalContext context = new MockExternalContext();

		context.setEventId("login");
		this.resumeFlow(context);

		assertFlowExecutionOutcomeEquals("failedView");
		assertFlowExecutionEnded();
		verify(this.entityManager, query);
	}

	/**
	 * Tests subflows. If you want to use this test, you have to use the line
	 * return resourceFactory.createFileResource(
	 * "src/main/webapp/WEB-INF/flows/subflow.xml"); in the
	 * getResource()-method.
	 */
	public void testLoginSubflow() {
//		this.setCurrentState("testPage");
//
//		User user = this.createValidUser();
//		this.getFlowScope().put("user", user);
//
//		this.getFlowDefinitionRegistry().registerFlowDefinition(
//				this.createMockLoginFlow(user));
//
//		MockExternalContext context = new MockExternalContext();
//		context.setEventId("login");
//		this.resumeFlow(context);
//
//		assertFlowExecutionEnded();
//		assertFlowExecutionOutcomeEquals("welcomeView");
	}

	/**
	 * Private helper method that creates a mock flow
	 * 
	 * @param user
	 *            User needed for the mock flow
	 * @return A mock flow
	 */
	private Flow createMockLoginFlow(final User user) {
		Flow loginFlow = new Flow("loginform");
		loginFlow.setInputMapper(new Mapper() {

			@Override
			public MappingResults map(Object source, Object target) {
				assertEquals(user, ((AttributeMap) source).get("user"));
				return null;
			}
		});
		new EndState(loginFlow, "success");
		return loginFlow;
	}

	/**
	 * Private helper method that creates a valid user
	 * 
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
	 * 
	 * @return An invalid User
	 */
	private User createInvalidUser() {
		User user = new User();
		user.setUsername("Jane");
		user.setPassword("Doe");

		return user;
	}
}
