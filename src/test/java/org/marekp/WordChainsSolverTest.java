package org.marekp;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordChainsSolverTest {

    private WordChainsSolver solver = new WordChainsSolver();
    private WordDictionary wordDictionary = new WordDictionary("wordlist.txt");

    @Test
    public void shouldFindSimplestChain() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "cot"});

        List<List<String>> chains = solver.findChains("cat", "cot", dictionary);
        List<String> solutions = chains.get(0);

        assertEquals("cat", solutions.get(0));
        assertEquals("cot", solutions.get(1));
    }

    @Test
    public void shouldFindChains() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "dog", "cog", "cot", "dot", "dag"});

        List<List<String>> chains = solver.findChains("cat", "dog", dictionary);

        for (List<String> solution : chains) {
            assertEquals("cat", solution.get(0));
            assertEquals("dog", solution.get(solution.size() - 1));
        }
    }

    @Test
    public void shouldFindShortestChainsOnly() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "dog", "cog", "cot", "dot", "dag", "lag", "tag", "bag", "bat", "nat"});

        List<List<String>> chains = solver.findChains("cat", "dog", dictionary);

        for (List<String> solution : chains) {
            assertEquals(4, solution.size());
        }
    }

    @Test
    public void shouldFindChainsWhenDictionaryContainsBrokenEntries() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "dog", "cog", "cot", "dot", "dag", "Za^", "tag", "bag", "bat", "84%"});

        List<List<String>> chains = solver.findChains("cat", "dog", dictionary);

        for (List<String> solution : chains) {
            assertEquals(4, solution.size());
        }
    }

    @Test
    public void shouldFindFourCharacterChainFromWordList() {
        List<List<String>> chains = solver.findChains("lead", "gold", wordDictionary);

        assertTrue(chains.size() > 0);

        for (List<String> solution : chains) {
            assertEquals("lead", solution.get(0));
            assertEquals("gold", solution.get(solution.size() - 1));
        }
    }

    @Test
    public void shouldFindSolutionBackward() {
        List<List<String>> chains = solver.findChains("lead", "gold", wordDictionary);
        List<List<String>> chainsBackward = solver.findChains("gold", "lead", wordDictionary);

        for (List<String> solution : chains) {
            assertEquals("lead", solution.get(0));
            assertEquals("gold", solution.get(solution.size() - 1));
        }
        for (List<String> solution : chainsBackward) {
            assertEquals("gold", solution.get(0));
            assertEquals("lead", solution.get(solution.size() - 1));
        }
    }

    @Test
    public void shouldReturnEmptyResultIfLastWordNotInDictionary() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "cot"});

        List<List<String>> chains = solver.findChains("cat", "cog", dictionary);

        assertEquals(0, chains.size());
    }

    @Test
    public void shouldReturnEmptyResultIfDictionaryEmpty() {
        WordDictionary dictionary = new WordDictionary(new String[]{});

        List<List<String>> chains = solver.findChains("cat", "cog", dictionary);

        assertEquals(0, chains.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstWordNull() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "cot"});

        solver.findChains(null, "cog", dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfLastWordNull() {
        WordDictionary dictionary = new WordDictionary(new String[]{"cat", "cot"});

        solver.findChains("cat", null, dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfDictionaryNull() {
        solver.findChains("cat", "cot", null);
    }
}