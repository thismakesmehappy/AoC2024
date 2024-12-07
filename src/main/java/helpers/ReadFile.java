package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private final String filePath;

    public static List<String> readLines(String baseDirectory, String fileName) {
        ReadFile readFile = new ReadFile(baseDirectory, fileName);
        return readFile.readLines();
    }

    public static List<String> readLines(String fileName) {
        ReadFile readFile = new ReadFile(fileName);
        return readFile.readLines();
    }

    private ReadFile(String baseDirectory, String fileName) {
        filePath = baseDirectory + "/" + fileName;
    }

    private ReadFile(String fileName) {
        filePath = fileName;
    }

    private List<String> readLines() {
        File file = new File(getClass().getResource(filePath).getFile());
        try {
            return readLinesFromFile(file);
        } catch (Exception e) {
            System.out.println("Error reading file: " + filePath);
            return List.of();
        }
    }

    private List<String> readLinesFromFile(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }
}
