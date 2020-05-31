package com.tsquare.triggerc;

import com.tsquare.triggerc.lexer.Lexer;
import com.tsquare.triggerc.lexer.tokens.Tag;
import com.tsquare.triggerc.lexer.tokens.Token;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);

        Lexer lexer = new Lexer(file);

        Token token;
        List<List<Token>> expressions = new ArrayList<List<Token>>();
        do {
            List<Token> expression = new ArrayList<Token>();
            do {
                token = lexer.scan();
                if (token.tag != Tag.EOF && token.tag != Tag.DELIM && token.tag != Tag.EOL) {
                    expression.add(token);
                }
            } while (token.tag != Tag.EOF && token.tag != Tag.DELIM);
            if (!expression.isEmpty()) {
                expressions.add(expression);
            }
        } while (token.tag != Tag.EOF);


        // Parser parser = new Parser(file);
        // parser.expression();
        System.out.write('\n');
    }
}
