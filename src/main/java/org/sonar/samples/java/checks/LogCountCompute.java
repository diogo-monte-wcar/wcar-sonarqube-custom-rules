package org.sonar.samples.java.checks;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

public class LogCountCompute implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext measureComputerDefinitionContext) {
    return measureComputerDefinitionContext.newDefinitionBuilder()
        .setOutputMetrics(LogsMetrics.TOTAL.key())
        .build();
  }

  @Override
  public void compute(MeasureComputerContext measureComputerContext) {
    if (measureComputerContext.getComponent().getType() != Component.Type.FILE) {
      int sum = 0;
      int count = 0;
      for (Measure child : measureComputerContext.getChildrenMeasures(LogsMetrics.TOTAL.key())) {
        sum += child.getIntValue();
        count++;
      }
      int average = count == 0 ? 0 : sum / count;
      measureComputerContext.addMeasure(LogsMetrics.TOTAL.key(), average);
    }
  }
}
