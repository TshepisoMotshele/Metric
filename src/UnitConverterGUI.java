import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverterGUI {
    private JFrame frame;

    private JComboBox<String> conversionComboBox;
    private JTextField quantityField;
    private JButton convertButton;
    private JLabel resultLabel;

    public UnitConverterGUI() {
        frame = new JFrame("VP Metric Converter");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(218, 185, 160), 10));
        contentPanel.setBackground(new Color(125, 167, 180));

        initComponents();
        addComponentsToPanel(contentPanel);
        addImageToPanel(contentPanel);

        frame.setContentPane(contentPanel);
        frame.setVisible(true);

        convertButton.addActionListener(e -> performConversion());
    }

    private void addImageToPanel(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("VP.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(imageLabel, BorderLayout.NORTH);
    }

    private void initComponents() {
        String[] conversions = {
                "Feet to Meters",
                "Meters to Feet",
                "Pounds to Kgs",
                "Kgs to Pounds",
                "Fahrenheit to Celsius",
                "Celsius to Fahrenheit"
        };

        conversionComboBox = new JComboBox<>(conversions);
        conversionComboBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        quantityField = new JTextField(14);
        quantityField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        Dimension fieldDimension = new Dimension(200, 30);
        quantityField.setPreferredSize(fieldDimension);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

        Color beigeColor = new Color(241, 234, 229);
        resultLabel = new JLabel("Result will be shown here");
        resultLabel.setForeground(beigeColor);
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
    }

    private void addComponentsToPanel(JPanel panel) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(125, 167, 180));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Select Conversion:"), gbc);
        gbc.gridy++;
        mainPanel.add(new JLabel("Enter Quantity:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(conversionComboBox, gbc);
        gbc.gridy++;
        mainPanel.add(quantityField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(convertButton, gbc);

        gbc.gridy++;
        mainPanel.add(resultLabel, gbc);

        panel.add(mainPanel, BorderLayout.CENTER);
    }


    private void performConversion() {
        String selectedConversion = (String) conversionComboBox.getSelectedItem();
        double quantity;

        try {
            quantity = Double.parseDouble(quantityField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number");
            return;
        }

        double result = 0;

        switch (selectedConversion) {
            case "Feet to Meters":
                result = feetToMeters(quantity);
                break;
            case "Meters to Feet":
                result = metersToFeet(quantity);
                break;
            case "Pounds to Kgs":
                result = poundsToKilograms(quantity);
                break;
            case "Kgs to Pounds":
                result = kilogramsToPounds(quantity);
                break;
            case "Fahrenheit to Celsius":
                result = fahrenheitToCelsius(quantity);
                break;
            case "Celsius to Fahrenheit":
                result = celsiusToFahrenheit(quantity);
                break;
            default:
                resultLabel.setText("Conversion not supported");
                return;
        }

        resultLabel.setText(quantity + " " + getFromUnit(selectedConversion) + " is equal to " + result + " " + getToUnit(selectedConversion));
    }

    private String getFromUnit(String conversion) {
        String[] units = conversion.split(" to ");
        return units[0].substring(units[0].indexOf(" ") + 1);
    }

    private String getToUnit(String conversion) {
        String[] units = conversion.split(" to ");
        return units[1].substring(units[1].indexOf(" ") + 1);
    }

    // Conversion methods
    private double feetToMeters(double feet) {
        return feet * 0.3048;
    }

    private double metersToFeet(double meters) {
        return meters / 0.3048;
    }

    private double poundsToKilograms(double pounds) {
        return pounds * 0.453592;
    }

    private double kilogramsToPounds(double kilograms) {
        return kilograms / 0.453592;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UnitConverterGUI();
            }
        });
    }
}
