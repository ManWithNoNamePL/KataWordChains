package org.marekp;

import java.util.*;

public class WordChainsSolver {


    public List<List<String>> findChains(String firstWord, String lastWord, WordDictionary dictionary) {
        if (firstWord == null || lastWord == null || dictionary == null) {
            throw new IllegalArgumentException(String.format("firstWord, lastWord and dictionary may not be null - " +
                    "firstWord [%s], lastWord [%s], dictionary [%s]", firstWord, lastWord, dictionary));
        }
        Set<String> words = dictionary.getWords();
        List<List<String>> solutionChains = new ArrayList<>();
        if (words.isEmpty()) {
            return solutionChains;
        }
        Map<String, List<String>> adjustedWords = new HashMap<>();
        Map<String, Integer> distancesFromStartNode = new HashMap<>();

        traverseBFS(firstWord, lastWord, words, adjustedWords, distancesFromStartNode);

        List<String> solution = new ArrayList<>();
        traverseDFS(firstWord, lastWord, adjustedWords, distancesFromStartNode, solution, solutionChains);

        return solutionChains;
    }

    private void traverseBFS(String beginWord, String endWord, Set<String> words,
                             Map<String, List<String>> adjustedWords, Map<String, Integer> distance) {

        for (String word : words) {
            adjustedWords.put(word, new ArrayList<>());
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                String currentWord = queue.poll();
                List<String> eligibleAdjustedWords = findEligibleAdjustedWords(currentWord, words);
                for (String adjusted : eligibleAdjustedWords) {
                    adjustedWords.get(currentWord).add(adjusted);
                    if (!distance.containsKey(adjusted)) {
                        distance.put(adjusted, distance.get(currentWord) + 1);
                        if (endWord.equals(adjusted)) {
                            return;
                        } else {
                            queue.offer(adjusted);
                        }
                    }
                }
            }
        }
    }

    private void traverseDFS(String currentWord, String endWord, Map<String, List<String>> adjustedWords,
                             Map<String, Integer> distance, List<String> solution, List<List<String>> allChains) {
        solution.add(currentWord);
        if (endWord.equals(currentWord)) {
            allChains.add(new ArrayList<>(solution));
        } else {
            adjustedWords.get(currentWord).stream().filter(next -> distance.get(next) == distance.get(currentWord) + 1).
                    forEach(next -> traverseDFS(next, endWord, adjustedWords, distance, solution, allChains));
        }
        solution.remove(solution.size() - 1);
    }

    private List<String> findEligibleAdjustedWords(String currentWord, Set<String> words) {
        List<String> adjusted = new ArrayList<>();
        char chars[] = currentWord.toCharArray();
        for (char ch = 'A'; ch <= 'z'; ch++) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ch) {
                    continue;
                }
                char toReplace = chars[i];
                chars[i] = ch;
                if (words.contains(String.valueOf(chars))) {
                    adjusted.add(String.valueOf(chars));
                }
                chars[i] = toReplace;
            }
        }
        return adjusted;
    }
}
