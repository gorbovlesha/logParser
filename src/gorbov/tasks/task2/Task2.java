package gorbov.tasks.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {

        if (args[0] == null || args[1] == null || args[2] == null || !new File(args[1]).isFile()) {
            System.out.println("Invalid arguments");
            System.exit(-1);
        }

        String first = args[0];
        String path = args[1];
        String fileName = args[2];
        String result;

        if (first.length() > 1) {
            result = regExParse(first, path);
        } else {
            result = csvParse(first.charAt(0), path);
        }

        writeInFile(result, path, fileName);


    }

    public static String regExParse(String regEx, String path) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile(regEx);
        List<String> listFiles = getFilesInDir(path);

        while (count < listFiles.size()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(listFiles.get(count), Charset.forName("Windows-1251")))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find()) {
                        result.append(line).append("\n");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
        return result.toString();
    }

    public static String csvParse(char divider, String path) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        List<String> listFiles = getFilesInDir(path);

        while (count < listFiles.size()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(listFiles.get(count), Charset.forName("Windows-1251")))) {

                String line;
                StringBuilder parcedLine;


                while ((line = reader.readLine()) != null) {
                    parcedLine = new StringBuilder(line.replace((char) 9, divider));
                    String s = line.replace((char) 9, divider);
                    parcedLine.setCharAt(10, divider);
                    parcedLine.setCharAt(23, divider);
                    parcedLine.setCharAt(30, divider);

                    result.append(parcedLine).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            count++;
        }

        return result.toString();
    }

    public static void writeInFile(String result, String path, String fileName) {
        boolean dir = new File(path + "\\" + fileName).mkdir();

        try {
            Files.write(Paths.get(path + "\\" + fileName + "\\" + fileName + ".log"), result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String> getFilesInDir(String path) {
        List<String> listFiles = null;

        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            listFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listFiles;
    }
}
