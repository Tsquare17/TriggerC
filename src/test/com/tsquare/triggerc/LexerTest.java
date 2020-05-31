package com.tsquare.triggerc;

import com.tsquare.triggerc.lexer.Lexer;
import com.tsquare.triggerc.lexer.tokens.Tag;
import com.tsquare.triggerc.lexer.tokens.Token;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {
    protected File file;
    protected Lexer lexer;

    @Before
    public void setUp() throws FileNotFoundException {
        file = new File("program.tr");
        lexer = new Lexer(file);
    }

    @Test
    public void testLexerCanGetToken() throws IOException {
        Token token = lexer.scan();

        assertEquals(Tag.ID, token.tag);
    }

    @Test
    public void testLexerCanDetectEndOfFileOrDelimiter() throws IOException {
        Token token;
        List<Token> expression = new ArrayList<Token>();
        do {
            token = lexer.scan();
            if (token.tag != Tag.EOF && token.tag != Tag.DELIM && token.tag != Tag.EOL) {
                expression.add(token);
            }
        } while (token.tag != Tag.EOF && token.tag != Tag.DELIM);

        int numberOfTokens = 5;
        assertEquals(expression.size(), numberOfTokens);
    }
}
