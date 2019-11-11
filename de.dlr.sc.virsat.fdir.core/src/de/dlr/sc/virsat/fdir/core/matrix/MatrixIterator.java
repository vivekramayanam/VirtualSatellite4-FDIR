/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.fdir.core.matrix;

/**
 * @author piet_ci
 * 
 * Class representing an Iterator iterating a matrix and probability distribution over time 
 *
 */
public class MatrixIterator {

	private TransitionMatrix tmTerminal;
	private double[] probabilityDistribution;
	private double delta;
	private double eps;
	private double[] resultBuffer;

	/**
	 * @param tmTerminal transition matrix
	 * @param probabilityDistribution probability distribution
	 * @param delta delta
	 * @param eps epsilon
	 */
	public MatrixIterator(TransitionMatrix tmTerminal, double[] probabilityDistribution, double delta, double eps) {
		this.tmTerminal = tmTerminal;
		this.probabilityDistribution = probabilityDistribution;
		this.delta = delta;
		this.eps = eps;
		this.resultBuffer = new double[probabilityDistribution.length];
	}
	
	/**
	 * Performs one update iteration
	 */
	public void iterate() {
		double[] res = getProbabilityDistribution();
		probabilityDistribution = new double[getProbabilityDistribution().length];

		double lambda = 1;
		int i = 0;
		boolean convergence = false;
		while (!convergence) {
			for (int j = 0; j < getProbabilityDistribution().length; ++j) {
				getProbabilityDistribution()[j] += res[j] * lambda;
			}

			lambda = lambda / (i + 1);
			double change = lambda * tmTerminal.multiply(res, resultBuffer) / delta;

			// Swap the discrete time buffers
			double[] tmp = res;
			res = resultBuffer;
			resultBuffer = tmp;

			if (change < eps * eps || !Double.isFinite(change)) {
				for (int j = 0; j < getProbabilityDistribution().length; ++j) {
					getProbabilityDistribution()[j] += res[j] * lambda;
				}

				convergence = true;
			} else {
				++i;
			}
		}
	}

	/**
	 * @return probability distribution at current time
	 */
	public double[] getProbabilityDistribution() {
		return probabilityDistribution;
	}
}
