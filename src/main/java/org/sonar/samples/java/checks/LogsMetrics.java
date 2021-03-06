package org.sonar.samples.java.checks;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class LogsMetrics implements Metrics {

  private static final String TOTAL_LINES_COUNT = "lines_total_count";
  private static final String LOGS_TOTAL_COUNT = "logs_total_count";
  private static final String LOGS_INFO_COUNT = "logs_info_count";
  private static final String LOGS_DEBUG_COUNT = "logs_debug_count";

  static final Metric TOTAL_FILE_LINES = new Metric
      .Builder(TOTAL_LINES_COUNT, "Total number of lines", Metric.ValueType.INT)
      .setDescription("Total number of lines in java files")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_GENERAL)
      .setDeleteHistoricalData(true)
      .create();

  static final Metric TOTAL = new Metric
      .Builder(LOGS_TOTAL_COUNT, "Count of logs (all levels)", Metric.ValueType.INT)
      .setDescription("Total Logs in project")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_GENERAL)
      .setDeleteHistoricalData(true)
      .create();

  static final Metric INFO = new Metric
      .Builder(LOGS_INFO_COUNT, "Count of info logs", Metric.ValueType.INT)
      .setDescription("Total info logs in project")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_GENERAL)
      .setDeleteHistoricalData(true)
      .create();

  static final Metric DEBUG = new Metric
      .Builder(LOGS_DEBUG_COUNT, "Count of debug logs", Metric.ValueType.INT)
      .setDescription("Total debug ogs in project")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_GENERAL)
      .setDeleteHistoricalData(true)
      .create();

  @Override
  public List<Metric> getMetrics() {
    return Arrays.asList(TOTAL_FILE_LINES, TOTAL, INFO, DEBUG);
  }
}
