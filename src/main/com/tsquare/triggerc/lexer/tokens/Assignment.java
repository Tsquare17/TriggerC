package com.tsquare.triggerc.lexer.tokens;

public class Assignment extends Token {
    public final String operator;

    public Assignment(int t, String operator) {
        super(t);
        this.operator = operator;
    }
}
