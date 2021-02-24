package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
@Rule(
    key = "LogQuality",
    name = "Logging",
    description = "logging",
    priority = Priority.INFO,
    tags = {"log"})
public class LogQualityRule extends IssuableSubscriptionVisitor {

    @Override
    public List<Kind> nodesToVisit() {
        return ImmutableList.of(Kind.METHOD_INVOCATION);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void visitNode(Tree tree) {
        MethodInvocationTree method = (MethodInvocationTree) tree;

        if (method.methodSelect().kind() != Kind.MEMBER_SELECT) return;

        MemberSelectExpressionTree memberSelectTree = (MemberSelectExpressionTree) method.methodSelect();

        if (memberSelectTree.expression().kind() == Kind.IDENTIFIER) {
            String exprTypeName = memberSelectTree.expression().symbolType().name();
            Set<String> validMembers = new HashSet<>(Arrays.asList("info", "debug", "error"));
            String identifierName = memberSelectTree.identifier().name();

            if (!(exprTypeName.equals("Logger") && validMembers.contains(identifierName))) return;
        } else {
            // Maybe TODO ?
            return;
        }

        // OK, we have a logging statement
        if (method.arguments().size() == 1) {
            Tree arg = method.arguments().get(0);
//            // String literal
            if (arg.kind() == Kind.STRING_LITERAL) {
                reportIssue(tree, "Avoid logging only a string literal.");
                return;
            }
        }

//        String name = method.symbol().name();
//        System.out.println("Called method with name: " + name);

//        if (name.equals("info")) {
//            reportIssue(tree, "Called info!");
//        }
    }
}