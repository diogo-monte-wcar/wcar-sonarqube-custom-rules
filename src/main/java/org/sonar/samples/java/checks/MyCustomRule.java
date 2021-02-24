package org.sonar.samples.java.checks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;

@Rule(key = "MyCustom")
public class MyCustomRule extends BaseTreeVisitor implements JavaFileScanner {

  @Override
  public void scanFile(JavaFileScannerContext context) {
    if (context.getFileLines().isEmpty()) {
      return;
    }

    int totalLinesCount = context.getFileLines().size();
    int infoLogCount = 0;
    Pattern pattern = Pattern.compile("(LOGGER.info\\(.*\\);)");
    Matcher matcher = pattern.matcher(context.getFileContent());
    while (matcher.find()) {
      ++infoLogCount;
    }
    if (infoLogCount > 0) {
      double percentage = (double) infoLogCount / totalLinesCount;
      context.reportIssue(this, context.getTree(), String.format("Log count: %d - %.2f", infoLogCount, percentage));
    }
  }
}
