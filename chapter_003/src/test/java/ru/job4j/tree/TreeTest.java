package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenHasntElementThenNoAdd() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(tree.add(2, 3), is(false));
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 2), is(false));
    }

    @Test
    public void whenAddElementsThenElementsInTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 9);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        int[] result = new int[6];
        int index = 0;
        while (it.hasNext()) {
            result[index++] = it.next();
        }
        assertThat(result, is(new int[] {1, 2, 9, 4, 5, 6}));
    }

    @Test
    public void whenTreeBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 9);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenTreeNotBinaryThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 9);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        assertThat(tree.isBinary(), is(false));
    }
}