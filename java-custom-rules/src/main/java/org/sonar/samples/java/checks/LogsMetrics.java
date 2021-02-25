package org.sonar.samples.java.checks;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class LogsMetrics implements Metrics {

  private static final String LOGS_TOTAL_COUNT = "logs_total_count";
  private static final String LOGS_INFO_COUNT = "logs_info_count";
  private static final String LOGS_DEBUG_COUNT = "logs_debug_count";

  static final Metric TOTAL = new Metric
      .Builder(LOGS_TOTAL_COUNT, "Count of logs (all levels)", Metric.ValueType.INT)
      .setDescription("Total Logs in project")
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_MAINTAINABILITY)
      .create();

  static final Metric INFO = new Metric
      .Builder(LOGS_INFO_COUNT, "Count of info logs", Metric.ValueType.INT)
      .setDescription("Total info logs in project")
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_MAINTAINABILITY)
      .create();

  static final Metric DEBUG = new Metric
      .Builder(LOGS_DEBUG_COUNT, "Count of debug logs", Metric.ValueType.INT)
      .setDescription("Total debug ogs in project")
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_MAINTAINABILITY)
      .create();

  @Override
  public List<Metric> getMetrics() {
    return Arrays.asList(TOTAL, INFO, DEBUG);
  }
}
