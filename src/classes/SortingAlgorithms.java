package classes;
import java.util.*;

public class SortingAlgorithms {
    // Gera uma lista de números aleatórios únicos dentro de um intervalo
    public List<Integer> generateUniqueRandomNumbers(int min, int max) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random rand = new Random();

        while (uniqueNumbers.size() < (max - min + 1)) {
            uniqueNumbers.add(rand.nextInt((max - min + 1)) + min);
        }

        return new ArrayList<>(uniqueNumbers);
    }

    public long measureQuickSort(List<Integer> data) {
        long startTime = System.nanoTime();
        quickSort(data, 0, data.size() - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long measureMergeSort(List<Integer> data) {
        long startTime = System.nanoTime();
        mergeSort(data, 0, data.size() - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long measureBubbleSort(List<Integer> data) {
        long startTime = System.nanoTime();
        bubbleSort(data);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Implementação do QuickSort
    private void quickSort(List<Integer> data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

    private int partition(List<Integer> data, int low, int high) {
        int pivot = data.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (data.get(j) < pivot) {
                i++;
                Collections.swap(data, i, j);
            }
        }
        Collections.swap(data, i + 1, high);
        return i + 1;
    }

    // Implementação do MergeSort
    private void mergeSort(List<Integer> data, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(data, left, middle);
            mergeSort(data, middle + 1, right);
            merge(data, left, middle, right);
        }
    }

    private void merge(List<Integer> data, int left, int middle, int right) {
        List<Integer> leftList = new ArrayList<>(data.subList(left, middle + 1));
        List<Integer> rightList = new ArrayList<>(data.subList(middle + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i) <= rightList.get(j)) {
                data.set(k++, leftList.get(i++));
            } else {
                data.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            data.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            data.set(k++, rightList.get(j++));
        }
    }

    // Implementação do BubbleSort
    private void bubbleSort(List<Integer> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = 0; j < data.size() - i - 1; j++) {
                if (data.get(j) > data.get(j + 1)) {
                    Collections.swap(data, j, j + 1);
                }
            }
        }
    }

    // Determina o algoritmo mais eficiente com base no tempo de execução
    public String getMostEfficientAlgorithm(long quickSortTime, long mergeSortTime, long bubbleSortTime) {
        if (quickSortTime <= mergeSortTime && quickSortTime <= bubbleSortTime) return "QuickSort";
        else if (mergeSortTime <= quickSortTime && mergeSortTime <= bubbleSortTime) return "MergeSort";
        else return "BubbleSort";
    }
}