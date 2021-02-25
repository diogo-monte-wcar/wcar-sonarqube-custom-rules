package org.sonar.samples.java.checks;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.check.Rule;

@SuppressWarnings("ALL")
public class LogCountSensor implements Sensor {

  @Override
  public void describe(SensorDescriptor sensorDescriptor) {
    sensorDescriptor.name("Log information");
  }

  @Override
  public void execute(SensorContext sensorContext) {
    for (InputFile inputFile : sensorContext.fileSystem().inputFiles(sensorContext.fileSystem().predicates().hasLanguage("java"))) {

      String fileContent = "";
      try {
        fileContent = inputFile.contents();
      } catch (IOException e) {
        e.printStackTrace();
      }

      int infoCount = LogHelper.countInfoLogs(fileContent);
      int debugCount = LogHelper.countDebugLogs(fileContent);
      int totalCount = infoCount + debugCount;

      sensorContext.newMeasure()
          .withValue(inputFile.lines())
          .forMetric(LogsMetrics.TOTAL_FILE_LINES)
          .on(inputFile)
          .save();

      sensorContext.newMeasure()
          .withValue(totalCount)
          .forMetric(LogsMetrics.TOTAL)
          .on(inputFile)
          .save();

      sensorContext.newMeasure()
          .withValue(infoCount)
          .forMetric(LogsMetrics.INFO)
          .on(inputFile)
          .save();

      sensorContext.newMeasure()
          .withValue(debugCount)
          .forMetric(LogsMetrics.DEBUG)
          .on(inputFile)
          .save();
    }
  }
}
