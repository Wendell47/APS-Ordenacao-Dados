package classes;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import components.TextField;
import components.resultArea;
import components.Label;
import components.Button;


public class SortingComparisonFrame extends JFrame {
    private TextField minRangeField, maxRangeField;
    private resultArea resultArea;
    private SortingAlgorithms sortingAlgorithms;
    private List<Integer> generatedNumbers;
    private Label minRangeLabel,maxRangeLabel;
    private Button insertDataButton,runButton,clearButton;
    private JPanel inputPanel,bottomPanel;
    private functions functions;

    public SortingComparisonFrame() {
        setTitle("Comparação de Algoritmos de Ordenação");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        sortingAlgorithms = new SortingAlgorithms();
        resultArea = new resultArea();
        minRangeLabel = new Label("Menor número:");
        maxRangeLabel = new Label("Maior número:");
        minRangeField = new TextField();
        maxRangeField = new TextField();

        insertDataButton = new Button("Inserir Dados");
        runButton = new Button("Executar Ordenação");
        clearButton = new Button("Limpar Campos");

        inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(minRangeLabel);
        inputPanel.add(minRangeField);
        inputPanel.add(maxRangeLabel);
        inputPanel.add(maxRangeField);
        inputPanel.add(insertDataButton);
        inputPanel.add(runButton); 

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(clearButton);

        functions = new functions(minRangeField, maxRangeField, resultArea, sortingAlgorithms, generatedNumbers);
        insertDataButton.addActionListener(e -> functions.insertData());
        runButton.addActionListener(e -> functions.executeSorting());
        clearButton.addActionListener(e -> functions.clearFields());

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }


}
