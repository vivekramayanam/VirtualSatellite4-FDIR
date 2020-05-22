/**
 * generated by Xtext 2.18.0.M3
 */
package de.dlr.sc.virsat.fdir.galileo.dft;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dlr.sc.virsat.fdir.galileo.dft.DftPackage
 * @generated
 */
public interface DftFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DftFactory eINSTANCE = de.dlr.sc.virsat.fdir.galileo.dft.impl.DftFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Galileo Dft</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Galileo Dft</em>'.
   * @generated
   */
  GalileoDft createGalileoDft();

  /**
   * Returns a new object of class '<em>Galileo Fault Tree Node</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Galileo Fault Tree Node</em>'.
   * @generated
   */
  GalileoFaultTreeNode createGalileoFaultTreeNode();

  /**
   * Returns a new object of class '<em>Galileo Repair Action</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Galileo Repair Action</em>'.
   * @generated
   */
  GalileoRepairAction createGalileoRepairAction();

  /**
   * Returns a new object of class '<em>Galileo Node Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Galileo Node Type</em>'.
   * @generated
   */
  GalileoNodeType createGalileoNodeType();

  /**
   * Returns a new object of class '<em>Named</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Named</em>'.
   * @generated
   */
  Named createNamed();

  /**
   * Returns a new object of class '<em>Observer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Observer</em>'.
   * @generated
   */
  Observer createObserver();

  /**
   * Returns a new object of class '<em>Parametrized</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parametrized</em>'.
   * @generated
   */
  Parametrized createParametrized();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DftPackage getDftPackage();

} //DftFactory
