package ru.shutov.cft;


import org.junit.Assert;
import org.junit.Test;
import ru.shutov.cft.arguments.Order;
import ru.shutov.cft.utils.MergeSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSorterTest {
    @Test
    public void integerListShouldBeSortedAsc() {
        List<Integer> actual = new ArrayList<>(Arrays.asList(6, 3, 7, 1, 5, 8, 12, 23, 1, 6));
        MergeSorter.sortIntegers(actual, 0, actual.size()-1, Order.ASCEND);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 1, 3, 5, 6, 6, 7, 8, 12, 23));
        Assert.assertArrayEquals(expected.toArray(new Integer[0]), actual.toArray(new Integer[0]));
    }

    @Test
    public void integerListShouldBeSortedDesc() {
        List<Integer> actual = new ArrayList<>(Arrays.asList(6, 3, 7, 1, 5, 8, 12, 23, 1, 6));
        MergeSorter.sortIntegers(actual, 0, actual.size()-1, Order.DESCEND);
        List<Integer> expected = new ArrayList<>(Arrays.asList(23, 12, 8, 7, 6, 6, 5, 3, 1, 1));
        Assert.assertArrayEquals(expected.toArray(new Integer[0]), actual.toArray(new Integer[0]));
    }

    @Test
    public void listShouldRemainUnchanged() {
        List<Integer> actual = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        MergeSorter.sortIntegers(actual, 0, actual.size() - 1, Order.ASCEND);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertArrayEquals(expected.toArray(new Integer[0]), actual.toArray(new Integer[0]));
    }

    @Test
    public void stringListShouldBeSortedAsc() {
        List<String> actual = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Abb", "Bba", "Aba"));
        MergeSorter.sortStrings(actual, 0, actual.size() - 1, Order.ASCEND);
        List<String> expected = new ArrayList<>(Arrays.asList("Aaa", "Aba", "Abb", "Bba", "Bbb", "Ccc"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), actual.toArray(new String[0]));
    }

    @Test
    public void stringListShouldBeSortedDesc() {
        List<String> actual = new ArrayList<>(Arrays.asList("Aaa", "Bbb", "Ccc", "Abb", "Bba", "Aba"));
        MergeSorter.sortStrings(actual, 0, actual.size() - 1, Order.DESCEND);
        List<String> expected = new ArrayList<>(Arrays.asList("Ccc", "Bbb", "Bba", "Abb", "Aba", "Aaa"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), actual.toArray(new String[0]));
    }
}
