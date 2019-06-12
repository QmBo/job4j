package ru.job4j.macros;

import java.util.*;
/**
 * MacrosSelector
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 12.06.2019
 */
public class MacrosSelector {
    /**
     * Return chain of macro's when started before select macros.
     * @param ds macro's chains.
     * @param scriptId start macros.
     * @return chain of macro's.
     */
    public List load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        List<Integer> result = new ArrayList<>(100);
        Deque<Integer> tempResult = new ArrayDeque<>(100);
        Queue<Integer> temp = new ArrayDeque<>(ds.get(scriptId));
        do {
            Integer integer = temp.poll();
            tempResult.add(integer);
            ds.get(integer).forEach(mac -> {
                if (!tempResult.contains(mac)) {
                    temp.add(mac);
                }
            });
            temp.remove(integer);
        } while (!temp.isEmpty());
        Iterator<Integer> it = tempResult.descendingIterator();
        it.forEachRemaining(result::add);
        return result;
    }
}
