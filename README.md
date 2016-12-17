# Mutation Analyzer

[![Linux Build][circleci-img]][circleci-url]
[![Mac Build][travisci-img]][travisci-url]
[![Windows Build][appveyor-img]][appveyor-url]
[![Test Coverage][codecov-img]][codecov-url]

A simple mutation analyzer library for Major Mutation Framework

## Getting Started

The project can be build with Gradle using the following commands. If gradle is not installed on the system, there is a gradle wrapper, so instead of "gradle tasks" simply use "./gradlew tasks" for Linux/Mac or "gradlew.bat tasks" for Windows.

### Building and Testing the Analyzer

Extract the required Major Framework:
```bash
$ gradle extractMajor
```

Build the project:
```bash
$ gradle build
```

Test the project:
```bash
$ gradle test
```

### Running the Analyzer on the provided Sample Program and Test Suite

This project come with a sample folder consisting of a program and a TestSuite. The provided SampleAnalyzer can be run to demo the Mutation Analyzer library.
```bash
$ gradle runSampleAnalyzer
```

## Example:

```java
import major.mutation.Analyzer;
import major.mutation.plugin.*;
import major.mutation.plugin.SingleTestLoader;
import major.mutation.plugin.naive.NaiveRunner;
import major.mutation.plugin.MutantKilled;

public class ExampleAnalyzer {
  public static void main(String[] args) {
    // Instantiate Analyzer
    Analyzer analyzer = new Analyzer();

    // Instantiate Components
    TestSuiteBuilder testLoader = new SingleTestLoader("TestSuite");
    TestSuiteRunner testRunner = new NaiveRunner();
    ScoreCalculator testResult = new MutantKilled();

    // Register plugins with Analyzer
    analyzer.registerTestSuiteBuilder(testLoader);
    analyzer.registerTestSuiteRunner(testRunner);
    analyzer.registerScoreCalculator(testResult);

    // Run Analyzer
    analyzer.run();
  }
}
```

## Authors:

+ SeihakRithy Muth (Rithy58)
+ Kenny Tsui
+ Ethan Liu
+ Jing Liu

[circleci-img]: https://img.shields.io/circleci/project/github/Rithy58/mutation-analyzer/master.svg?label=linux
[circleci-url]: https://circleci.com/gh/Rithy58/mutation-analyzer
[travisci-img]: https://img.shields.io/travis/Rithy58/mutation-analyzer/master.svg?label=mac
[travisci-url]: https://travis-ci.org/Rithy58/mutation-analyzer
[appveyor-img]: https://img.shields.io/appveyor/ci/Rithy58/mutation-analyzer/master.svg?label=windows
[appveyor-url]: https://ci.appveyor.com/project/Rithy58/mutation-analyzer/
[codecov-img]: https://img.shields.io/codecov/c/github/Rithy58/mutation-analyzer/master.svg
[codecov-url]: https://codecov.io/gh/Rithy58/mutation-analyzer
