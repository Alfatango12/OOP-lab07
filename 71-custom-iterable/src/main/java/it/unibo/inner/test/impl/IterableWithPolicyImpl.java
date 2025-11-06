package it.unibo.inner.test.impl;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private T[] elements;
    private Predicate<T> policy;

    public IterableWithPolicyImpl(final T[] e) {
        this(e, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] e, final Predicate<T> p) {
        this.elements = e;
        setIterationPolicy(p);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.policy = filter;
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
            while (this.currentIndex >= 0 && this.currentIndex < IterableWithPolicyImpl.this.elements.length && !policy.test(IterableWithPolicyImpl.this.elements[this.currentIndex]) ) { //out of bounds
                this.currentIndex++;
            }
            
            if (this.currentIndex >= 0 && this.currentIndex < IterableWithPolicyImpl.this.elements.length) {
                return true;
            }

            else {
                return false;
            }


        }

        public T next() {
            return IterableWithPolicyImpl.this.elements[this.currentIndex++];
        }
    }
}
