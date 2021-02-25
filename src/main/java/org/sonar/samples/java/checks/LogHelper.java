package org.sonar.samples.java.checks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogHelper {

  private static final Pattern infoPattern = Pattern.compile("(LOGGER.info\\(.*\\);)");
  private static final Pattern debugPattern = Pattern.compile("(LOGGER.debug\\(.*\\);)");

  public static int countInfoLogs(String fileContent) {
    return countLogLevelInFile(infoPattern, fileContent);
  }

  public static int countDebugLogs(String fileContent) {
    return countLogLevelInFile(debugPattern, fileContent);
  }

  private static int countLogLevelInFile(Pattern logPattern, String fileContent) {
    Matcher matcher = logPattern.matcher(fileContent);
    int infoLogCount = 0;
    while (matcher.find()) {
      ++infoLogCount;
    }
    return infoLogCount;
  }
}
