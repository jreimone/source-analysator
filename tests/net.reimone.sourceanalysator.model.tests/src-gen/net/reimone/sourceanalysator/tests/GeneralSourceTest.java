/**
 */
package net.reimone.sourceanalysator.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.SourceanalysatorFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>General Source</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class GeneralSourceTest extends TestCase {

	/**
	 * The fixture for this General Source test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneralSource fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(GeneralSourceTest.class);
	}

	/**
	 * Constructs a new General Source test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralSourceTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this General Source test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(GeneralSource fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this General Source test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneralSource getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SourceanalysatorFactory.eINSTANCE.createGeneralSource());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //GeneralSourceTest
