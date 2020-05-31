package com.tsquare.triggerc.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    static int lookahead;
    private final BufferedReader br;

    public Parser(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        this.br = new BufferedReader(fileReader);
        lookahead = br.read();
    }

    public void next() throws IOException {
        lookahead = this.br.read();
    }

    public void expression() throws IOException {
        term();

        while (true) {
            if (lookahead == '+') {
                match('+');
                term();
                System.out.write('+');
            } else if (lookahead == '-') {
                match('-');
                term();
                System.out.write('-');
            } else {
                return;
            }
        }
    }

    public void term() throws IOException {
        if (Character.isDigit((char) lookahead)) {
            System.out.write((char) lookahead);
            match(lookahead);
        } else {
            throw new Error("Syntax error");
        }
    }

    public void match(int t) throws IOException {
        if (lookahead == t) {
            this.next();
        } else {
            throw new Error("Syntax error");
        }
    }
}
