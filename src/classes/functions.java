package classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JTextArea;

import components.TextField;
public class functions extends fields {
 
    public functions( TextField minRangeField,TextField maxRangeField, JTextArea resultArea, SortingAlgorithms sortingAlgorithms,List<Integer> generatedNumbers){
        this.generatedNumbers=generatedNumbers;
        this.maxRangeField=maxRangeField;
        this.minRangeField=minRangeField;
        this.resultArea=resultArea;
        this.sortingAlgorithms=sortingAlgorithms;
    }

       public void insertData() {
        try {
            int min = Integer.parseInt(minRangeField.getText());
            int max = Integer.parseInt(maxRangeField.getText());

            if (min >= max) {
                resultArea.setText("Erro: O menor número deve ser inferior ao maior número.");
                return;
            }

            generatedNumbers = sortingAlgorithms.generateUniqueRandomNumbers(min, max);
            Collections.shuffle(generatedNumbers);  // Embaralha os números para garantir que estejam desordenados
            resultArea.setText("Números Gerados (Desordenados):\n" + generatedNumbers + "\n");
        } catch (NumberFormatException e) {
            resultArea.setText("Erro: Insira valores numéricos válidos para o intervalo.");
        }
    }

       public void executeSorting() {
        if (generatedNumbers == null || generatedNumbers.isEmpty()) {
            resultArea.setText("Erro: Nenhum número foi gerado. Por favor, insira o intervalo e clique em 'Inserir Dados'.");
            return;
        }

        List<Integer> dataCopy = new ArrayList<>(generatedNumbers);  // Cópia da lista original para aplicar ordenação
        resultArea.append("\nExecutando ordenação...\n");

        long quickSortTime = sortingAlgorithms.measureQuickSort(dataCopy);
        resultArea.append("Números Ordenados:\n" + dataCopy + "\n\n");

        dataCopy = new ArrayList<>(generatedNumbers); // Reinicializa a lista para o próximo algoritmo
        long mergeSortTime = sortingAlgorithms.measureMergeSort(dataCopy);

        dataCopy = new ArrayList<>(generatedNumbers); // Reinicializa a lista para o próximo algoritmo
        long bubbleSortTime = sortingAlgorithms.measureBubbleSort(dataCopy);

        resultArea.append("Tempo de execução QuickSort: " + quickSortTime + " ns\n");
        resultArea.append("Tempo de execução MergeSort: " + mergeSortTime + " ns\n");
        resultArea.append("Tempo de execução BubbleSort: " + bubbleSortTime + " ns\n");

        String efficientAlgorithm = sortingAlgorithms.getMostEfficientAlgorithm(quickSortTime, mergeSortTime, bubbleSortTime);
        resultArea.append("\nAlgoritmo mais eficiente: " + efficientAlgorithm + "\n");
    }

    public void clearFields() {
        minRangeField.setText("");
        maxRangeField.setText("");
        resultArea.setText("");
        generatedNumbers = null;
    }
}
