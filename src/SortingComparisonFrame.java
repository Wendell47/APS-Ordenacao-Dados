import javax.swing.*;
import java.awt.*;
import java.util.List;


public class SortingComparisonFrame extends JFrame {
    private JTextField minRangeField, maxRangeField;
    private JTextArea resultArea;
    private SortingAlgorithms sortingAlgorithms;
    private List<Integer> generatedNumbers;

    public SortingComparisonFrame() {
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

        functions functions = new functions(minRangeField, maxRangeField, resultArea, sortingAlgorithms, generatedNumbers);
        insertDataButton.addActionListener(e -> functions.insertData());
        runButton.addActionListener(e -> functions.executeSorting());
        clearButton.addActionListener(e -> functions.clearFields());


        setLocationRelativeTo(null);
        setVisible(true);
    }


}
