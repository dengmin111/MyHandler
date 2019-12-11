package com.example.myhandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class QuickSort {

    public int[] solution(int[] array) {
        int number = array.length;
        sort(array, 0, number - 1);
        return array;
    }

    void sort(int array[], int first, int end) {
        Stack<Integer> s = new Stack<>();
        if (first < end) {
            s.push(first);
            s.push(end);
        }
        while (!s.empty()) {
            int high = s.peek();
            s.pop();
            int low = s.peek();
            s.pop();
            if (low < high) {
                int pivot = partition(array, low, high);
                if (low < pivot - 1) {
                    s.push(low);
                    s.push(pivot - 1);
                }
                if (pivot + 1 < high) {
                    s.push(pivot + 1);
                    s.push(high);
                }
            }
        }
    }

    public int partition(int[] array, int first, int end) {
        int i = first, j = end;
        while (i < j) {
            while (i < j && array[i] <= array[j]) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
            while (i < j && array[i] <= array[j]) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j--;
            }
        }
        return i;
    }

    @Test
    public void test_quickSort_inputnull() {
        int[] input = null;
        int[] res = solution(input);
        Assert.assertNull("input is null,res should be null", res);
    }

}
