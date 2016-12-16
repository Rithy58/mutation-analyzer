# cs520-project [![CircleCI](https://circleci.com/gh/Rithy58/mutation-analyzer.svg?style=shield)](https://circleci.com/gh/Rithy58/mutation-analyzer) [![Build Status](https://travis-ci.com/Rithy58/cs520-project.svg?token=D8MZUVqYSoqQhwnmjbyh&branch=master)](https://travis-ci.com/Rithy58/cs520-project) [![codecov](https://codecov.io/gh/Rithy58/mutation-analyzer/branch/master/graph/badge.svg)](https://codecov.io/gh/Rithy58/mutation-analyzer) [![Build status](https://ci.appveyor.com/api/projects/status/juu9yxwa260j7u6y/branch/master?svg=true)](https://ci.appveyor.com/project/Rithy58/mutation-analyzer/branch/master)

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
