package com.nikoskatsanos.jutils.core.array;

import java.util.Iterator;

/**
 * <p>A simple wrapper on top of an array. Its solely purpose is to to hold an offset and operate on the underlying
 * array directly, by offseting its indexes. For example a user would like to operate on an array's second half but
 * using a zero-index approach. Some use cases can be recursive algorithms when the caller would like to operate on the
 * same array rather than taking a copy of it.</p>
 * <p><b>NOTE:</b> This class is not thread safe</p>
 *
 * @author nikkatsa
 */
public class ArrayOffsetWrapper<T> implements Iterable<T> {

    private final T[] under;
    private final int offset;
    private final int length;

    public ArrayOffsetWrapper(final T[] under, final int offset) {
        this(under, offset, under.length);
    }

    public ArrayOffsetWrapper(final T[] under, final int offset, final int length) {
        this.under = under;
        this.offset = offset;
        this.length = length;
    }

    /**
     * @param idx Index of the element to retrieve
     * @return The underlying array's element at the specified {@code idx} after applying the {@code offset}
     */
    public final T get(final int idx) {
        this.checkIdx(idx);

        return this.under[offset + idx];
    }

    /**
     * @param idx     Index of the element to set
     * @param element The element to set to the underlying array's {@code idx} after applying the {@code offset}
     */
    public final void set(final int idx, final T element) {
        this.checkIdx(idx);

        this.under[offset + idx] = element;
    }

    /**
     * @return The wrapper's length
     */
    public final int length() {
        return this.length - this.offset;
    }

    private final void checkIdx(final int idx) {
        if (idx >= this.length) {
            throw new ArrayIndexOutOfBoundsException(String.format("[Idx = %d, ArrayOffsetWrapperLength = %d, " +
                    "ArrayLength = %d]", idx, this.length, this.under.length));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        private int cursor;

        public Itr() {
            this.cursor = offset;
        }

        @Override
        public boolean hasNext() {
            return cursor < length;
        }

        @Override
        public T next() {
            return under[cursor++];
        }
    }
}
