package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Converter
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 17.07.2018
 */
public class Converter {
    /**
     * Covert iterator of iterators in iterator of integers.
     * @param iit iterator to convert.
     * @return iterator.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iit) {
        return new IteratorOfIterators(iit);
    }

    /**
     * Iterator Of Iterators class.
     */
    private class IteratorOfIterators implements Iterator<Integer> {
        /**
         * Iterators.
         */
        private final Iterator<Iterator<Integer>> itOfIterators;
        /**
         * Work space.
         */
        private Iterator<Integer> itOfInteger;

        /**
         * Constructor.
         * @param iit input iterators.
         */
        public IteratorOfIterators(final Iterator<Iterator<Integer>> iit) {
            this.itOfIterators = iit;
            this.itOfInteger = iit.next();
        }

        /**
         * Check has next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            boolean done = false;
            while (!done) {
                if (this.itOfInteger.hasNext()) {
                    result = true;
                    done = true;
                } else if (this.itOfIterators.hasNext()) {
                    this.itOfInteger = this.itOfIterators.next();
                } else {
                    done = true;
                }
            }
            return result;
        }

        /**
         * Return next element.
         * @throws NoSuchElementException if no next element.
         * @return next element.
         */
        @Override
        public Integer next() {
            Integer result;
            if (this.hasNext()) {
                result = this.itOfInteger.next();
            } else {
                throw new NoSuchElementException("NoSuchElementException");
            }
            return result;
        }
    }
}
