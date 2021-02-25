package org.sonar.samples.java.checks;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import java.util.stream.StreamSupport;

public class LogCountCompute implements MeasureComputer {

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext measureComputerDefinitionContext) {
        return measureComputerDefinitionContext.newDefinitionBuilder()
                .setOutputMetrics(LogsMetrics.TOTAL.key(), LogsMetrics.INFO.key(), LogsMetrics.DEBUG.key())
                .build();
    }

    @Override
    public void compute(MeasureComputerContext ctx) {
        if (ctx.getComponent().getType() != Component.Type.FILE) {

            int totalCount = sumTotal(ctx, LogsMetrics.TOTAL.key());
            int infoCount = sumTotal(ctx, LogsMetrics.INFO.key());
            int debugCount = sumTotal(ctx, LogsMetrics.DEBUG.key());

            ctx.addMeasure(LogsMetrics.TOTAL.key(), totalCount);
            ctx.addMeasure(LogsMetrics.INFO.key(), infoCount);
            ctx.addMeasure(LogsMetrics.DEBUG.key(), debugCount);
        }
    }

    private int sumTotal(MeasureComputerContext ctx, String key) {
        return StreamSupport
                .stream(ctx.getChildrenMeasures(key).spliterator(), false)
                .mapToInt(Measure::getIntValue)
                .sum();
    }

}
