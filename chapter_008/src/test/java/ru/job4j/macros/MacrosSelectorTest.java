package ru.job4j.macros;

import org.junit.Test;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MacrosSelectorTest {

    @Test
    public void whenInput1Then32() {
        MacrosSelector macros = new MacrosSelector();
        Map<Integer, List<Integer>> map = new IdentityHashMap<>();
        map.put(1, Collections.singletonList(2));
        map.put(2, Collections.singletonList(3));
        map.put(3, Collections.emptyList());
        assertThat(macros.load(map, 1), is(Arrays.asList(3, 2)));
    }

    @Test
    public void whenInput1Then2345() {
        MacrosSelector macros = new MacrosSelector();
        Map<Integer, List<Integer>> map = new IdentityHashMap<>();
        map.put(1, Arrays.asList(2, 3));
        map.put(2, Collections.singletonList(4));
        map.put(3, Arrays.asList(4, 5));
        map.put(4, Collections.emptyList());
        map.put(5, Collections.emptyList());
        assertThat(macros.load(map, 1), is(Arrays.asList(5, 4, 3, 2)));
    }

    @Test
    public void whenInput2Then4() {
        MacrosSelector macros = new MacrosSelector();
        Map<Integer, List<Integer>> map = new IdentityHashMap<>();
        map.put(1, Arrays.asList(2, 3));
        map.put(2, Collections.singletonList(4));
        map.put(3, Arrays.asList(4, 5));
        map.put(4, Collections.emptyList());
        map.put(5, Collections.emptyList());
        assertThat(macros.load(map, 2), is(Collections.singletonList(4)));
    }
}