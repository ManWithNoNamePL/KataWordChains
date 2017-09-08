# KataWordChains
Solution for Word Chain Kata with use of BFS and DFS algorithms.

Implementation was performed with interative TDD approach, full set of unit tests may be found in WordChainsSolverTest class.

#### Word Dictionary
In resources wordlist.txt contains word dictionary that app is using.
If for any reason word dictionary need to be changed:
- add new one to resources,
- in WordChainTester adjust:
```java
WordDictionary dictionary = new WordDictionary("wordlist.txt");
```

_At this point loading dictionary mechanism is designed to load it from resources, but if needed it may be easly extended to load dictionary from any place_

#### Running 

To check how algorith work download project and open it in your favourtie IDE, WordChainTester have main that may accept 2 args for begin and end word. It may be of course modified directly in in this class just by changing:
```java
String beginWord = "cat";
String endWord = "dog";
```

Output will print found solutions (if there are any). It will also print bacward solutions and computation time in millis.

_WordChainTester is not too sophisticated, it was added just to present results as simple output_
