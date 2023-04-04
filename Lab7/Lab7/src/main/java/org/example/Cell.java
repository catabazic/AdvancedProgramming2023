package org.example;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Token> tokens;

    public Cell() {
        this.tokens = new ArrayList<>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public boolean isVisited() {
        return !tokens.isEmpty();
    }
}