package com.mmnaseri.cs.algorithm.clrs.ch7.s3;

import com.mmnaseri.cs.algorithm.clrs.ch7.s1.QuickSorter;
import com.mmnaseri.cs.algorithm.common.ArrayUtils;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 4:41 PM)
 */
public class RandomizedQuickSorter<E extends Comparable<E>> extends QuickSorter<E> {

    public RandomizedQuickSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected int partition(E[] items, int from, int to) {
        final int index = new Random().nextInt(to - from) + from;
        ArrayUtils.swap(items, index, to - 1);
        return super.partition(items, from, to);
    }

}
