# Plagiarism Detection System

## Overwiev
Java program that detects if two files are plagiarised using the Hamming Distance method.

## Files
- 'Hamming.java' - base class for calculating the Hamming distance
- 'HammingCleared.java' - extension of Hamming class (clear whitespace, underline)
- 'CheckPlagiarism.java' - checks if two files are similar and how similar to each other; decides if they were plagiarised
- 'TestClass.java' - class for testing chosen methods
- 'plagiarism_examples/' - files used in testing
- 'results.txt' - results of running the test file

## Compiling and running tests
### Compile main source files
javac -d bin src/*.java
### Compile test files
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin" -d bin test/*.java
### Run tests
java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin" org.junit.runner.JUnitCore TestClass

##Author
Alicja Krupczynska
Object Oriented Programming - task 7
November 20, 2025
