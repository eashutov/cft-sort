package ru.shutov.cft.utils;

import ru.shutov.cft.arguments.Order;

import java.util.List;

public class MergeSorter {

    public static void sortStrings(List<String> stringList, int start, int end, Order order) {
        if (start != end) {
            int mid = (start + end) / 2;
            sortStrings(stringList, start, mid, order);
            sortStrings(stringList, mid + 1, end, order);
            mergeStrings(stringList, start, mid, end, order);
        }
    }

    public static void sortIntegers(List<Integer> integerList, int start, int end, Order order) {
        if (start != end) {
            int mid = (start + end) / 2;
            sortIntegers(integerList, start, mid, order);
            sortIntegers(integerList, mid + 1, end, order);
            mergeIntegers(integerList, start, mid, end, order);
        }
    }

    private static void mergeStrings(List<String> list, int start, int mid, int end, Order order) {
        int i = start;
        int j = mid + 1;
        int k = 0;

        String[] temp = new String[end - start + 1];

        while ((i <= mid) && (j <= end)) {
            if (compareByOrder(list.get(i).compareTo(list.get(j)), order))
                temp[k++] = list.get(i++);
            else
                temp[k++] = list.get(j++);
        }

        while (i <= mid) {
            temp[k++] = list.get(i++);
        }

        while (j <= end) {
            temp[k++] = list.get(j++);
        }

        for (i = start; i <= end; i++) {
            list.remove(i);
            String e = temp[i - start];
            list.add(i, e);
        }
    }

    private static void mergeIntegers(List<Integer> list, int start, int mid, int end, Order order) {
        int i = start;
        int j = mid + 1;
        int k = 0;

        int[] temp = new int[end - start + 1];

        while ((i <= mid) && (j <= end)) {
            if (compareByOrder(list.get(i).compareTo(list.get(j)), order))
                temp[k++] = list.get(i++);
            else
                temp[k++] = list.get(j++);
        }

        while (i <= mid) {
            temp[k++] = list.get(i++);
        }

        while (j <= end) {
            temp[k++] = list.get(j++);
        }

        for (i = start; i <= end; i++) {
            list.remove(i);
            int e = temp[i - start];
            list.add(i, e);
        }
    }

    private static boolean compareByOrder(int comparison, Order order) {
        if(order == Order.DESCEND) {
            return comparison >= 0;
        } else {
            return comparison <= 0;
        }
    }
}
