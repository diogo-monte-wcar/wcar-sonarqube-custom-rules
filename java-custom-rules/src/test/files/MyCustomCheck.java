/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class MyCustomCheck {  // Noncompliant

  MyCustomCheck(MyClass mc) {
  }
  void info(String v) {
  }
  int foo1() {
    return 0;
  }
  void foo2(int value) {
    this.info("123");
    LOGGER.info("123");
  }
  Object foo4(int value) {
    return null;
  }
  int foo6(int value, String name) {
    return 0;
  }
  int foo7(int... values) {
    return 0;
  }

}
