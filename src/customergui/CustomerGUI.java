/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customergui;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

/**
 *
 * @author IX Hero
 */
public class CustomerGUI extends JFrame {

    JTextArea customerTextArea = new JTextArea();

    JLabel idLabel = new JLabel("ID: ");
    JTextField idTextField = new JTextField(10);

    JLabel nameLabel = new JLabel("Name: ");
    JTextField nameTextField = new JTextField(10);

    JLabel surnameLabel = new JLabel("Last Name: ");
    JTextField surnameTextField = new JTextField(10);

    JLabel phoneNumberLabel = new JLabel("Phone Number: ");
    JTextField phoneNumberTextField = new JTextField(15);

    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton exitButton = new JButton("Exit");

    private LinkedList<Customer> customerList = new LinkedList<Customer>();

    public CustomerGUI() {

        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));

        customerTextArea.setEditable(false);

        flow1Panel.add(idLabel);
        flow1Panel.add(idTextField);
        flow1Panel.add(nameLabel);
        flow1Panel.add(nameTextField);
        flow1Panel.add(surnameLabel);
        flow1Panel.add(surnameTextField);
        flow1Panel.add(phoneNumberLabel);
        flow1Panel.add(phoneNumberTextField);

        flow2Panel.add(addButton);
        flow2Panel.add(deleteButton);
        flow2Panel.add(exitButton);

        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);

        add(customerTextArea, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);

        addButton.addActionListener(event -> addCustomer());
        deleteButton.addActionListener(event -> deleteCustomer());
        exitButton.addActionListener(event -> exitApp());

    }

    private boolean isCustomerIdInLinkedList(String idStr) {
        boolean inList = false;
        for (Customer customer : customerList) {
            if (customer.getId().compareToIgnoreCase(idStr) == 0) {
                inList = true;
            }
        }
        return inList;
    }

    private void addCustomer() {
        if (isCustomerIdInLinkedList(idTextField.getText()) == true) {
            JOptionPane.showMessageDialog(null, "Error: student ID is already in the database.");
        } else {
            Customer customer = new Customer(idTextField.getText(), nameTextField.getText(), surnameTextField.getText(), phoneNumberTextField.getText());
            customerList.add(customer);
            displayAll();
            idTextField.setText("");
            nameTextField.setText("");
            surnameTextField.setText("");
            phoneNumberTextField.setText("");
        }
    }

    private void deleteCustomer() {
        if (customerList.size() == 0) {
            JOptionPane.showMessageDialog(null, "Error: Database is empty.");
        } else if (isCustomerIdInLinkedList(idTextField.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Error: student ID is not in the database.");
        } else {
            for (int s = 0; s < customerList.size(); s++) {
                String currId = customerList.get(s).getId();
                if (currId.compareToIgnoreCase(idTextField.getText()) == 0) {
                    customerList.remove(s);
                }
            }
            displayAll();
            idTextField.setText("");
            nameTextField.setText("");
            surnameTextField.setText("");
            phoneNumberTextField.setText("");
        }
    }
    
    private void displayAll() {
        customerTextArea.setText("");
        for (Customer customer : customerList) {
            customerTextArea.append(customer + "\n");
        }
    }

    private void exitApp() {
        System.exit(0);
    }

    public static void main(String[] args) {

        CustomerGUI app = new CustomerGUI();
        app.setVisible(true);
        app.setSize(800, 500);
        app.setLocation(500, 400);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
