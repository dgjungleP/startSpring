package com.jungle.spel;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.GregorianCalendar;

public class TestApplication {
    @Test
    public void testExpressionParse() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression world = parser.parseExpression("'Hello World'");
        System.out.println("world.getValue() = " + world.getValue());
    }

    @Test
    public void testExpressionParse2() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression world = parser.parseExpression("'Hello World'.concat('!')");
        System.out.println("world.getValue() = " + world.getValue());
    }

    @Test
    public void testExpressionParse3() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression world = parser.parseExpression("new String('Hello World').toUpperCase()");
        System.out.println("world.getValue() = " + world.getValue());
    }


}
