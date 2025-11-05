package it.unibo.inner.test.impl;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private T[] elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] e) {
        this.elements = e;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    public class CustomIterator implements Iterator<T> {
        private int currentIndex;
        
        public CustomIterator() {
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            if (this.currentIndex >= 0 && this.currentIndex < IterableWithPolicyImpl.this.elements.length) {
                return true;
            }

            else {
                return false;
            }
        }

        public T next() {
            try {
                return IterableWithPolicyImpl.this.elements[currentIndex];
            } finally {
                this.currentIndex++;
            }
        }
    }
}
