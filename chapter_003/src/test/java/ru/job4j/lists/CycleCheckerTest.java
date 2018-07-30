package ru.job4j.lists;

import org.junit.Test;
import ru.job4j.lists.CycleChecker.Node;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CycleCheckerTest {

    @Test
    public void whenCycleThenTrue() {
        CycleChecker<Integer> cc = new CycleChecker();
        Node<Integer> first = new Node(1);
        Node<Integer> two = new Node(2);
        Node<Integer> third = new Node(3);
        Node<Integer> four = new Node(4);
        Node<Integer> five = new Node(5);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;
        five.next = first;
        assertThat(cc.hasCycle(two), is(true));
    }

    @Test
    public void whenNoCycleThenFalse() {
        CycleChecker<Integer> cc = new CycleChecker();
        Node<Integer> first = new Node(1);
        Node<Integer> two = new Node(2);
        Node<Integer> third = new Node(3);
        Node<Integer> four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(cc.hasCycle(first), is(false));
    }

}