package org.marekp;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class WordDictionary {

    private final Set<String> words;

    public WordDictionary(String[] words) {
        this.words = Arrays.stream(words).collect(Collectors.toSet());
    }

    public WordDictionary(String fileName) {
        if (fileName == null) {
            throw new IllegalStateException("File name need to be provided");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        this.words = loadFromFile(file);
    }

    public Set<String> getWords() {
        return new HashSet<>(words);
    }

    private Set<String> loadFromFile(File file) {
        Set<String> words = new HashSet<>();

        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while (((line = bufferedReader.readLine()) != null)) {
                words.add(line);
            }
        } catch (FileNotFoundException fne) {
            System.err.println(String.format("File not found [%s], exception message [%s]", file, fne.getMessage()));
        } catch (IOException ioe) {
            System.err.println(String.format("Exception while reading file [%s], exception message [%s]", file, ioe.getMessage()));
        }
        return words;
    }
}
