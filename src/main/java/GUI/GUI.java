package GUI;

import Logic.Person;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel basicPanel;
    private JPanel loanForm;
    private JButton createLoan;
    private JButton reviewLoan;
    private JButton approveLoan;
    private JButton denyLoan;
    private Person currentUser;
    private int requiredAccess = 1;

    public GUI() {

    setTitle("Bankovnictvý-Úvěry");
    setSize(800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    createLoan = new JButton("Create");
    createLoan.setFont(new Font("Arial", Font.BOLD, 30));


    basicPanel = new JPanel();
    basicPanel.setLayout(new FlowLayout());
    basicPanel.add(createLoan);

    add(basicPanel,  BorderLayout.SOUTH);

    createLoan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showForm();
        }
    });
    setVisible(true);
    }
    private void showForm() {
        System.out.println("odstranen");
        remove(basicPanel);

        loanForm = new JPanel();
        loanForm.setLayout(new GridLayout(2,2));
        JLabel amountText = new JLabel("Částka:");
        JLabel timeText = new JLabel("Po dobu:");

        JTextField amountInput = new JTextField();
        JTextField timeInput = new JTextField();

        amountText.setFont(new Font("Arial", Font.BOLD, 30));
        timeText.setFont(new Font("Arial", Font.BOLD, 30));

        loanForm.add(amountText);
        loanForm.add(amountInput);
        loanForm.add(timeText);
        loanForm.add(timeInput);

        add(loanForm, BorderLayout.CENTER);

        revalidate();
        repaint();

    }
    public static void main(String[] args) {
        new GUI();
    }
}
