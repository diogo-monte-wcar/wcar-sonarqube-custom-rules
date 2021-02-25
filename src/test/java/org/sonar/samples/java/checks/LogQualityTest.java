package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class LogQualityTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/LogQualityCheck.java")
                .withCheck(new LogQualityRule())
                .verifyIssues();
    }
}
