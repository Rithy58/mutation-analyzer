# Mutation Analyzer

[![Linux Build][circleci-img]][circleci-url]
[![Mac Build][travisci-img]][travisci-url]
[![Windows Build][appveyor-img]][appveyor-url]
[![Test Coverage][codecov-img]][codecov-url]

Private Repo for CS520 Project

### Getting Started:
This project come with a sample folder consisting of a program and a TestSuite
to be use with the Mutation Analyzer.

The project can be build with Gradle using the following commands. If gradle is not installed on the system, there is a gradle wrapper, so instead of "gradle tasks" simply use "./gradlew tasks" for Linux/Mac or "gradlew.bat tasks" for Windows.

##### Building and Testing the Analyzer

`gradle extractMajor` will extract the required Major framework

`gradle build` will build the project

`gradle test` will test the project



##### Running the Analyzer on the provided Sample Program and Test Suite

For best result, run the following commands in order. However, simply running `gradle runSampleAnalyzer` should works too.

`gradle extractMajor` will extract the required Major framework

`gradle compileSampleProgram` will compile and mutate the sample program

`gradle compileSampleTestSuite` will compile the sample Test suite

`gradle compileSampleAnalyzer` will compile the sample Analyzer into the sample folder

`gradle runSampleAnalyzer` will do all of the above then run the sample analyzer

### Authors:
+ SeihakRithy Muth (Rithy58)
+ Kenny Tsui
+ Ethan Liu
+ Jing Liu

[circleci-img]: https://img.shields.io/circleci/project/github/Rithy58/mutation-analyzer.svg?label=linux
[circleci-url]: https://circleci.com/gh/Rithy58/mutation-analyzer
[travisci-img]: https://img.shields.io/travis/Rithy58/mutation-analyzer/master.svg?label=mac
[travisci-url]: https://travis-ci.org/Rithy58/mutation-analyzer
[appveyor-img]: https://img.shields.io/appveyor/ci/Rithy58/mutation-analyzer/master.svg?label=windows
[appveyor-url]: https://ci.appveyor.com/project/Rithy58/mutation-analyzer/
[codecov-img]: https://img.shields.io/codecov/c/github/Rithy58/mutation-analyzer/master.svg
[codecov-url]: https://codecov.io/gh/Rithy58/mutation-analyzer
