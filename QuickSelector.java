package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSelector<T extends Comparable<? super T>> {
    public T quickSelect(ArrayList<T> array, int index) {
        //your implementation here

        ArrayList<T> leftPartition = new ArrayList<T>();
        ArrayList<T> rightPartition = new ArrayList<T>();

        if (array.size() == 0) {
            return null;
        }

        if (array.size() == 1) {
            return array.get(0);
        }

        T median = medianOfMedians(array);

        if (array.size() > 1) {
            for (T element : array) {
                if (element.compareTo(median) < 0) {
                    leftPartition.add(element);
                }
                else {
                    rightPartition.add(element);
                }
            }

            if (leftPartition.size() > index) {
                return quickSelect(leftPartition, index);
            }
            else if (leftPartition.size() == index) {
                return median;
            }
        }
        return quickSelect(rightPartition, index - leftPartition.size());
    }

    public T getMedian(ArrayList<T> array) {
        Collections.sort(array);
        return array.get(array.size() / 2);
    }

    private T medianOfMedians(ArrayList<T> array){
        //your implementation here
        ArrayList<T> medianList = new ArrayList<T>();
        ArrayList<T> partition;
        int i = 0;

        if (array.size() <= 5) {
            return getMedian(array);
        }

        while (i < array.size() - 5) {
            if (i - array.size() >= 5) {
                partition = new ArrayList<T>(array.subList(i, i + 5));
            }
            else {
                partition = new ArrayList<T>(array.subList(i, array.size()));
            }
            medianList.add(getMedian(partition));
            i += 5;
        }
        return medianOfMedians(medianList);
    }

}
