package hospital_managment_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InterfejGraficzny extends JFrame {
    private final Szpital szpital;
    private final JPanel mainPanel;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public InterfejGraficzny(Szpital szpital) {
        this.szpital = szpital;
        setTitle("System zarządzania pacjentami");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createMenuBar();
        createMainButtons();

        add(mainPanel);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("System");
        JMenuItem exitItem = new JMenuItem("Wyjście");
        exitItem.addActionListener(e -> {
            szpital.saveAllData();
            System.exit(0);
        });
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void createMainButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addButton = new JButton("Dodaj pacjenta");
        JButton deleteButton = new JButton("Usuń pacjenta");
        JButton showButton = new JButton("Pokaż pacjentów");

        addButton.addActionListener(e -> showAddPatientDialog());
        deleteButton.addActionListener(e -> showDeletePatientDialog());
        showButton.addActionListener(e -> showAllPatients());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void showAddPatientDialog() {
        JDialog dialog = new JDialog(this, "Dodaj pacjenta", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField imieField = new JTextField();
        JTextField nazwiskoField = new JTextField();
        JTextField peselField = new JTextField();
        JTextField dataUrodzeniaField = new JTextField();
        JTextField telefonField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField adresField = new JTextField();

        panel.add(new JLabel("Imię:"));
        panel.add(imieField);
        panel.add(new JLabel("Nazwisko:"));
        panel.add(nazwiskoField);
        panel.add(new JLabel("PESEL:"));
        panel.add(peselField);
        panel.add(new JLabel("Data urodzenia (dd-MM-yyyy):"));
        panel.add(dataUrodzeniaField);
        panel.add(new JLabel("Telefon:"));
        panel.add(telefonField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Adres:"));
        panel.add(adresField);

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(e -> {
            try {
                LocalDate dataUrodzenia = LocalDate.parse(dataUrodzeniaField.getText(), formatter);
                Pacjent pacjent = new Pacjent(
                        imieField.getText(),
                        nazwiskoField.getText(),
                        peselField.getText(),
                        dataUrodzenia,
                        telefonField.getText(),
                        emailField.getText(),
                        adresField.getText()
                );
                szpital.dodajOsobe(pacjent);
                JOptionPane.showMessageDialog(dialog, "Pacjent został dodany pomyślnie");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Błąd podczas dodawania pacjenta: " + ex.getMessage());
            }
        });

        panel.add(saveButton);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showDeletePatientDialog() {
        String pesel = JOptionPane.showInputDialog(this, "Podaj PESEL pacjenta do usunięcia:");
        if (pesel != null && !pesel.isEmpty()) {
            szpital.usunOsobe(pesel);
            JOptionPane.showMessageDialog(this, "Pacjent został usunięty");
        }
    }

    private void showAllPatients() {
        JDialog dialog = new JDialog(this, "Lista pacjentów", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());
        StringBuilder text = new StringBuilder();

        for (Pacjent pacjent : szpital.listaPacjentow) {
            text.append("ID: ").append(pacjent.idJednostki)
                    .append(", Imię: ").append(pacjent.imie)
                    .append(", Nazwisko: ").append(pacjent.nazwisko)
                    .append(", PESEL: ").append(pacjent.pesel)
                    .append("\n");
        }

        JTextArea textArea = new JTextArea(text.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}