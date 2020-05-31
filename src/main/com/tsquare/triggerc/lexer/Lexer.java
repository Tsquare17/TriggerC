package com.tsquare.triggerc.lexer;

import com.tsquare.triggerc.lexer.tokens.*;

import java.io.*;
import java.util.Hashtable;

public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private Hashtable<String, Identifier> words = new Hashtable<String, Identifier>();
    private final BufferedReader br;

    public Lexer(File file) throws FileNotFoundException {
        reserve(new Identifier(Tag.TRUE, "true"));
        reserve(new Identifier(Tag.FALSE, "false"));
        br = new BufferedReader(new FileReader(file));
    }

    public Token scan() throws IOException {
        for (;; peek = (char) this.br.read()) {
            if (peek == ' ' || peek == '\t') {
                continue;
            } else if (peek == '\n') {
                line = line + 1;
            } else if (peek == (char) -1) {
                return new Token(Tag.EOF);
            } else {
                break;
            }
        }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10*v + Character.digit(peek, 10);
                peek = (char) this.br.read();
            } while (Character.isDigit(peek));

            return new Num(v);
        }

        if (Character.isLetter(peek)) {
            StringBuffer buffer = new StringBuffer();

            do {
                buffer.append(peek);
                peek = (char) this.br.read();
            } while (Character.isLetter(peek));

            String string = buffer.toString();
            Identifier word = words.get(string);

            if (word != null) {
                return word;
            }

            word = new Identifier(Tag.ID, string);
            words.put(string, word);

            return word;
        }

        if (peek == '=') {
            StringBuffer buffer = new StringBuffer();
            do {
                buffer.append(peek);
                peek = (char) this.br.read();
            } while (this.isOperator(peek));
            Token assignmentToken;
            String assignment = buffer.toString();
            if (assignment.equals("=")) {
                assignmentToken = new Assignment(Tag.EQ, assignment);
            } else {
                assignmentToken = new Assignment(Tag.CEQ, assignment);
            }

            return assignmentToken;
        }

        if (peek == '+') {
            peek = (char) this.br.read();
            return new Add();
        }

        if (peek == '-') {
            peek = (char) this.br.read();
            return new Minus();
        }

        if (peek == ';') {
            peek = (char) this.br.read();
            return new Delim();
        }

        Token token = new Token(peek);
        peek = ' ';

        return token;
    }

    public void reserve(Identifier t) {
        words.put(t.lexeme, t);
    }

    protected boolean isOperator(char c) {
        return c == '=' || c == '+' || c == '-';
    }
}
