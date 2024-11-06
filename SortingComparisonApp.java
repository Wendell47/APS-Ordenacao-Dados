import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SortingComparisonApp extends JFrame {
    private JTextField minRangeField, maxRangeField;
    private JTextArea resultArea;
    private SortingAlgorithms sortingAlgorithms;
    private List<Integer> generatedNumbers;

    public SortingComparisonApp() {
        setTitle("Comparação de Algoritmos de Ordenação");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        sortingAlgorithms = new SortingAlgorithms();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel minRangeLabel = new JLabel("Menor número:");
        minRangeLabel.setFont(minRangeLabel.getFont().deriveFont(20f));
        minRangeField = new JTextField();
        minRangeField.setFont(minRangeField.getFont().deriveFont(20f));
        JLabel maxRangeLabel = new JLabel("Maior número:");
        maxRangeLabel.setFont(maxRangeLabel.getFont().deriveFont(20f));
        maxRangeField = new JTextField();
        maxRangeField.setFont(maxRangeField.getFont().deriveFont(20f));

        JButton insertDataButton = new JButton("Inserir Dados");
        insertDataButton.setFont(insertDataButton.getFont().deriveFont(20f));
        JButton runButton = new JButton("Executar Ordenação");
        runButton.setFont(runButton.getFont().deriveFont(20f));
        JButton clearButton = new JButton("Limpar Campos");
        clearButton.setFont(clearButton.getFont().deriveFont(20f));


        inputPanel.add(minRangeLabel);
        inputPanel.add(minRangeField);
        inputPanel.add(maxRangeLabel);
        inputPanel.add(maxRangeField);
        inputPanel.add(insertDataButton);
        inputPanel.add(runButton);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(clearButton);
        add(bottomPanel, BorderLayout.SOUTH);

        insertDataButton.addActionListener(e -> insertData());
        runButton.addActionListener(e -> executeSorting());
        clearButton.addActionListener(e -> clearFields());
    }

    private void insertData() {
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

    private void executeSorting() {
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

    private void clearFields() {
        minRangeField.setText("");
        maxRangeField.setText("");
        resultArea.setText("");
        generatedNumbers = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingComparisonApp app = new SortingComparisonApp();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}
