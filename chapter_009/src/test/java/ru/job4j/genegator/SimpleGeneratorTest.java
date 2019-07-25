package ru.job4j.genegator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    @Test
    public void whenGetTextThenInsertKey() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        SimpleGenerator generator = new SimpleGenerator();
        String exp = "I am a Petr, Who are you?";
        String result = generator.generate(template, keys);
        assertThat(result, is(exp));
    }

    @Test(expected = IllegalStateException.class)
    public void whenMorKeysThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        keys.put("subject", "you");
        keys.put("sub", "you");
        SimpleGenerator generator = new SimpleGenerator();
        generator.generate(template, keys);
    }

    @Test(expected = IllegalStateException.class)
    public void whenMorKeysInTextThenException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = new HashMap<>();
        keys.put("name", "Petr");
        SimpleGenerator generator = new SimpleGenerator();
        generator.generate(template, keys);
    }

    @Test
    public void whenManiKeysThenInsertKey() {
        String template =  " Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = new HashMap<>();
        keys.put("sos", "Aaa");
        SimpleGenerator generator = new SimpleGenerator();
        String exp = " Help, Aaa, Aaa, Aaa";
        String result = generator.generate(template, keys);
        assertThat(result, is(exp));
    }
}
