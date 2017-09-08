package org.marekp;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class WordChainTester {

    public static void main(String[] args) {
        String beginWord = "cat";
        String endWord = "dog";
        if (args.length < 2) {
            System.out.println(String.format("No arguments (or not enough) provided, test will be performed" +
                    " on predefined values - begin word [%s], end [%s]", beginWord, endWord));
        } else {
            beginWord = args[0];
            endWord = args[1];
        }
        WordDictionary dictionary = new WordDictionary("wordlist.txt");
        WordChainsSolver solver = new WordChainsSolver();

        System.out.println(String.format("Checking possible paths between begin word [%s] and end [%s]", beginWord, endWord));
        LocalTime beforeSolving = LocalTime.now();
        List<List<String>> chains = solver.findChains(beginWord, endWord, dictionary);
        LocalTime afterSolving = LocalTime.now();
        if (chains.size() > 0) {
            displayResults(chains, beforeSolving, afterSolving);
            System.out.println("Checking possible paths backward");
            LocalTime beforeBackward = LocalTime.now();
            List<List<String>> backwardChains = solver.findChains(endWord, beginWord, dictionary);
            LocalTime afterBackward = LocalTime.now();
            displayResults(backwardChains, beforeBackward, afterBackward);
        } else {
            System.out.println("There is no word chain in current dictionary");
        }

    }

    private static void displayResults(List<List<String>> chains, LocalTime beforeSolving, LocalTime afterSolving) {
        for (List<String> solutions : chains) {
            System.out.println(Arrays.toString(solutions.toArray()));
        }
        System.out.println(String.format(" > Test took [%d] milliseconds", ChronoUnit.MILLIS.between(beforeSolving, afterSolving)));
    }

}
