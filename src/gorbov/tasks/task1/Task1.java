package gorbov.tasks.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Task1 {

    public static void main(String[] args) {

        if (args[0] == null || args[1] == null || !new File(args[0]).isFile()) {
            System.out.println("Invalid arguments");
            System.exit(-1);
        }

        Path path = Paths.get(args[0]);
        String prefix = args[1];

        readFile(path, prefix);


    }

    public static long linesNum(Path filePath) {
        long linesNumber = 0;

        try {
            linesNumber = Files.lines(filePath, Charset.forName("Windows-1251")).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesNumber;
    }

    public static void createFile(String filePrefix, int fileNum, StringBuilder contents) throws IOException {
        Files.write(Paths.get(filePrefix + fileNum + ".log"), contents.toString().getBytes(), StandardOpenOption.CREATE);
    }

    public static void readFile(Path path, String prefix) {
        long lines = linesNum(path);
        long linesInFile = lines / 5;

        System.out.println("All lines in file: " + lines + "\nLines in one file: ~" + lines / 5);

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            int fileSize = 0;
            int fileNum = 1;
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {

                fileSize++;

                if (fileNum < 5 && fileSize > linesInFile) {
                    createFile(prefix, fileNum, builder);
                    fileNum++;
                    builder = new StringBuilder();
                    fileSize = 1;
                }

                builder.append(line).append("\n");

            }

            createFile(prefix, fileNum, builder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
