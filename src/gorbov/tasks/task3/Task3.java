package gorbov.tasks.task3;

public class Task3 {
    public static void main(String[] args) {

        if (args.length != 1 || !args[0].equals("--help")) {
            System.out.println("Invalid argument");
        }
        else {
            System.out.println("""
                    This program was created by ALex Gorbov, as a part of IDP

                    \tTask 1.jar\s
                    \t
                    \tTask1.jar is a simple program, that divides a full log file into 5 different files
                    \tThe programm will create a directory and put all 5 files into it
                    \t
                    \tTask 2.jar
                    \t
                    \tTask2.jar is a program that that could do 2 things:
                    \t
                    \t\t1) If the first argument has length 1, the program will find all files that are located according to the path in the 2nd argument
                    \t\tand will divide all the lines with the divider in the first argument
                    \t\t
                    \t\t2) If the first argument is longer than 1 char, the program will consider this string as regex and will find all lines with this string
                    \t\t
                    \t\tAfter that the result will be saved in a new directory named "filename.log"
                    \t\t
                    \tTask 3.jar
                    \t
                    \tTask3.jar is a program, that can consume only "--help" string and shows all information about 3 programs\s
                    """);
        }
    }
}
