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

      int totalCount = sumTotal(measureComputerContext, LogsMetrics.TOTAL.key());
      //int infoCount = sumTotal(measureComputerContext, LogsMetrics.INFO.key());
      //int debugCount = sumTotal(measureComputerContext, LogsMetrics.DEBUG.key());

      measureComputerContext.addMeasure(LogsMetrics.TOTAL.key(), totalCount);
      //measureComputerContext.addMeasure(LogsMetrics.INFO.key(), infoCount);
      //measureComputerContext.addMeasure(LogsMetrics.DEBUG.key(), debugCount);
    }
  }

  private int sumTotal(MeasureComputerContext measureComputerContext, String key) {
    int totalSum = 0;
    for (Measure child : measureComputerContext.getChildrenMeasures(key)) {
      totalSum += child.getIntValue();
    }
    return totalSum;
  }
}
