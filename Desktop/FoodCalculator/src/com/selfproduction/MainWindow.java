package com.selfproduction;

import javax.swing.*;
import java.io.Console;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private JTable _mainTable;
    private JButton _button;
    private JTextField summaryTextField;

    private MainTableModel _mainTableModel;


    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);

        _button.addActionListener(e -> _mainTableModel.addEmptyItem());
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        _mainTableModel = new MainTableModel();
        _mainTable = new JTable(_mainTableModel);

        _mainTableModel.addListener((model, data) -> {
            double result = 0;
            for (MainTableItem item : data)
            {
                if (item.isInclude())
                {
                    result += item.getActualPrice();
                }
            }

            MainWindow.this.summaryTextField.setText("" + result);
        });
    }
}
