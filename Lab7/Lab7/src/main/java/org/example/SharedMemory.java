package org.example;

import java.util.ArrayList;
import java.util.List;


public class SharedMemory {
    private final Token tokens;// declare a collection of tokens;
    public SharedMemory(int n) {
        tokens=new Token(n);
    }
    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(/*poll one token from the collection*/);
        }
        return extracted;
    }
}
