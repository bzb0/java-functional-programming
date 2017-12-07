# Java Functional Programming with Vavr and Immutables annotations

### Description

Sample project that demonstrates the usage of the Java functional library Vavr and the _Immutables_ annotation processor. Vavr is a functional Java
library that provides immutable data types and functional control structures. The _Immutables_ annotation processor easies the development of
immutable/value objects and builders.

The Java source code is written in Java 8, while the unit tests are written in Groovy 2.4.13 (Spock framework). All of the test examples are written
as Spock unit tests. Gradle 4.4 is used as build and dependency management tool.

### Building the project

The implementation classes for the _Immutables_ annotated interfaces can be generated with the following command:

```
./gradlew clean compileJava
```

The generated classes can be found in the Gradle build directory ``build/generated/source/apt/main``. The next command builds the project and runs all
test examples.

```
./gradlew clean build
```
