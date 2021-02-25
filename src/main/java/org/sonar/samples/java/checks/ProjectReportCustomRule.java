package org.sonar.samples.java.checks;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.check.Rule;
import org.sonar.samples.java.MyJavaRulesDefinition;

@Rule(key = "ProjectReportCustom")
public class ProjectReportCustomRule implements Sensor {

  @Override
  public void describe(SensorDescriptor sensorDescriptor) {
    sensorDescriptor.name("Log information");
  }

  @Override
  public void execute(SensorContext sensorContext) {
    int totalLines = 10000;
    int debugLinesCount = 1000;
    int infoLogLinesCount = 3000;
    int totalLogLines = infoLogLinesCount + debugLinesCount;

    for (InputFile inputFile : sensorContext.fileSystem().inputFiles(sensorContext.fileSystem().predicates().hasLanguage("java"))) {
      sensorContext.newMeasure()
          .withValue(totalLogLines)
          .forMetric(LogsMetrics.TOTAL)
          .on(inputFile)
          .save();
    }



  }
}
