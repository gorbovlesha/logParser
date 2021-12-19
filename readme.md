# Log Parser

This program to parse logs is created within the IDP program

## Structure
The program consists of 3 different .jar files

### Task 1.jar
Firstly you need to start the app with the command:
```bash
java -jar logParser.jar filePath firstPartOfLogsPath
```
Task1.jar is a simple program, that divides a full log file into 5 different files
The program will create a directory and put all 5 files into it

### Task 2.jar

Task2.jar is a program that that could do 2 things:

Firstly you need to start the app with the command:
```bash
java -jar logParser.jar regExp logsPath newFileName
```
or
```bash
java -jar logParser.jar ; logsPath newFileName
```
1) If the first argument has length 1, the programm will find all files that are located according to the path in the 2nd argument
   and will divide all the lines with the divider in the first argument

2) If the first argument is longer than 1 char, the program will consider this string as regex and will find all lines with this string

After that the result will be saved in a new directory named "filename.log"

### Task 3.jar

Firstly you need to start the app with the command:
```bash
java -jar logParser.jar --help
```

Task3.jar is a program, that can consume only "--help" string and shows all information about 3 programs
