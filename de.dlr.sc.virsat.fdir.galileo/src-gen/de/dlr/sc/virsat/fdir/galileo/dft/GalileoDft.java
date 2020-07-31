/**
 * generated by Xtext 2.22.0
 */
package de.dlr.sc.virsat.fdir.galileo.dft;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Galileo Dft</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft#getRoot <em>Root</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft#getGates <em>Gates</em>}</li>
 *   <li>{@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft#getBasicEvents <em>Basic Events</em>}</li>
 * </ul>
 *
 * @see de.dlr.sc.virsat.fdir.galileo.dft.DftPackage#getGalileoDft()
 * @model
 * @generated
 */
public interface GalileoDft extends EObject
{
  /**
   * Returns the value of the '<em><b>Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root</em>' reference.
   * @see #setRoot(GalileoFaultTreeNode)
   * @see de.dlr.sc.virsat.fdir.galileo.dft.DftPackage#getGalileoDft_Root()
   * @model
   * @generated
   */
  GalileoFaultTreeNode getRoot();

  /**
   * Sets the value of the '{@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoDft#getRoot <em>Root</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root</em>' reference.
   * @see #getRoot()
   * @generated
   */
  void setRoot(GalileoFaultTreeNode value);

  /**
   * Returns the value of the '<em><b>Gates</b></em>' containment reference list.
   * The list contents are of type {@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoFaultTreeNode}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gates</em>' containment reference list.
   * @see de.dlr.sc.virsat.fdir.galileo.dft.DftPackage#getGalileoDft_Gates()
   * @model containment="true"
   * @generated
   */
  EList<GalileoFaultTreeNode> getGates();

  /**
   * Returns the value of the '<em><b>Basic Events</b></em>' containment reference list.
   * The list contents are of type {@link de.dlr.sc.virsat.fdir.galileo.dft.GalileoFaultTreeNode}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Basic Events</em>' containment reference list.
   * @see de.dlr.sc.virsat.fdir.galileo.dft.DftPackage#getGalileoDft_BasicEvents()
   * @model containment="true"
   * @generated
   */
  EList<GalileoFaultTreeNode> getBasicEvents();

} // GalileoDft
