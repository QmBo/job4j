package ru.job4j.chat;

import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * AnswersTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class AnswersTest {

    @Test
    public void whenAnswerLoadThenList() {
        assertThat(
                new Answers("chat/TestAnswers.txt").init(),
                is(Arrays.asList("Hi!", "What??", "OK.OK.."))
        );
    }

    @Test
    public void whenNeedAnswerThenOneOfListReturn() {
        Answers answers = new Answers("chat/TestAnswers.txt");
        answers.init();
        assertThat(
                Arrays.asList("Hi!", "What??", "OK.OK..").contains(answers.getAnswers()),
                is(true));
    }
}