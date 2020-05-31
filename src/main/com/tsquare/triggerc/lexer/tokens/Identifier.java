package com.tsquare.triggerc.lexer.tokens;

public class Identifier extends Token {
    public final String lexeme;

    public Identifier(int t, String string) {
        super(t);
        lexeme = string;
    }
}
