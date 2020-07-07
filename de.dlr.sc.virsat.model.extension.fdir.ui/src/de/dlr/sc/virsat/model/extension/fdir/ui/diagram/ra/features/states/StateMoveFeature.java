/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.fdir.ui.diagram.ra.features.states;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatMoveShapeFeature;
import de.dlr.sc.virsat.model.extension.fdir.model.State;
import de.dlr.sc.virsat.model.extension.fdir.ui.diagram.comments.CommentUtil;
import de.dlr.sc.virsat.model.extension.fdir.ui.diagram.ra.features.transitions.FaultEventTransitionCreateFeature;

/**
 * This class handles the drag & drop behavior of statesfor easy reconnection
 * @author muel_s8
 *
 */
public class StateMoveFeature extends VirSatMoveShapeFeature {

	private static final String Y_POS_REL_TO_FAULT_NODE = "y-pos-rel-to-fault-node";
	private static final String X_POS_REL_TO_FAULT_NODE = "x-pos-rel-to-fault-node";

	/**
	 * Standard constructor
	 * @param fp the feature provider
	 */
	public StateMoveFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void moveShape(IMoveShapeContext context) {
		ContainerShape containerShape = (ContainerShape) context.getShape();

		PictogramElement pe = context.getPictogramElement();
		State bean = (State) getBusinessObjectForPictogramElement(pe);

		Connection targetConnection = context.getTargetConnection();
		if (targetConnection != null) {
			Anchor start = targetConnection.getStart();
			Anchor end = targetConnection.getEnd();

			Anchor movedObjectAnchor = containerShape.getAnchors().get(0);

			ReconnectionContext reconnectionContext = new ReconnectionContext(targetConnection, start, movedObjectAnchor, null);
			reconnectionContext.setReconnectType(ReconnectionContext.RECONNECT_TARGET);
			getFeatureProvider().getReconnectionFeature(reconnectionContext).reconnect(reconnectionContext);

			CreateConnectionContext createConnectionContext = new CreateConnectionContext();
			createConnectionContext.setSourceAnchor(movedObjectAnchor);
			createConnectionContext.setTargetAnchor(end);
			new FaultEventTransitionCreateFeature(getFeatureProvider()).create(createConnectionContext);

		}

		super.moveShape(context);
		moveComments(containerShape, bean);
	}

	/**
	 * @param containerShape to layout comments for
	 * @param bean business object of pictogram
	 */
	private void moveComments(ContainerShape containerShape, State bean) {
		ContainerShape parent = containerShape.getContainer();

		EList<Shape> children = parent.getChildren();
		for (Object element : children) {
			Shape shape = (Shape) element;
			if (CommentUtil.isComment(shape) && CommentUtil.shapeBelongsToStateNode(shape, bean)) {
				int xPos = Integer.parseInt(Graphiti.getPeService().getPropertyValue(shape, X_POS_REL_TO_FAULT_NODE));
				int yPos = Integer.parseInt(Graphiti.getPeService().getPropertyValue(shape, Y_POS_REL_TO_FAULT_NODE));
				Graphiti.getGaService().setLocation(shape.getGraphicsAlgorithm(), containerShape.getGraphicsAlgorithm().getX() + xPos, containerShape.getGraphicsAlgorithm().getY() + yPos);
			}
		}
	}

}
