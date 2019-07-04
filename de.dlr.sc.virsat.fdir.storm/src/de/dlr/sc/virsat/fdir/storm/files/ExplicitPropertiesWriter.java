/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.fdir.storm.files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import de.dlr.sc.virsat.fdir.core.metrics.IMetric;
import de.dlr.sc.virsat.fdir.core.metrics.IMetricVisitor;
import de.dlr.sc.virsat.fdir.core.metrics.MTTF;
import de.dlr.sc.virsat.fdir.core.metrics.PointAvailability;
import de.dlr.sc.virsat.fdir.core.metrics.Reliability;
import de.dlr.sc.virsat.fdir.core.metrics.SteadyStateAvailability;

/**
 * 
 * @author yoge_re
 *
 */
public class ExplicitPropertiesWriter implements IExplicitFileWriter, IMetricVisitor {
	private double delta;
	private String instancePath;
	private IMetric[] metrics;
	private PrintWriter printWriter;

	/**
	 * 
	 * @param delta
	 *            time slice
	 * @param instancePath
	 *            file path
	 * @param metrics
	 *            the metrics
	 */
	public ExplicitPropertiesWriter(double delta, String instancePath, IMetric[] metrics) {
		this.delta = delta;
		this.instancePath = instancePath;
		this.metrics = metrics;
	}

	@Override
	public void writeFile() {
		try {
			FileWriter fileWriter = new FileWriter(instancePath);
			printWriter = new PrintWriter(fileWriter);
			for (IMetric metric : metrics) {
				metric.accept(this);
			}
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Reliability reliabilityMetric) {
		if (delta > 0) {
			for (double timepoint = delta; timepoint <= reliabilityMetric.getTime(); timepoint += delta) {
				printWriter.println("Pmin=? [F<=" + timepoint + " \"" + FAILED_STATE + "\"];");
			}
		}
	}

	@Override
	public void visit(MTTF mttfMetric) {
		printWriter.println("Tmin=? [F \"" + FAILED_STATE + "\"];");
	}

	@Override
	public void visit(SteadyStateAvailability steadyStateAvailabilityMetric) {
		printWriter.println("Rmin=? [LRA];");
	}

	@Override
	public void visit(PointAvailability pointAvailabilityMetric) {
		// TODO Auto-generated method stub
	}

}
