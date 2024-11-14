package GUI;

import Logic.Loan;
import Logic.LoanSystem;
import Logic.Person;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel basicPanel;
    private JPanel loanForm;
    private LoanSystem loanSystem;
    private JButton reviewLoan;
    private JButton approveLoan;
    private JButton denyLoan;
    private Person currentUser;
    private int requiredAccess = 1;
    private int startID = 1;

    public GUI() {
        loanSystem = new LoanSystem(currentUser);

        setTitle("Bankovnictvý-Úvěry");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showMenu();

        setVisible(true);
    }

    private void showMenu() {
        JButton createLoan = new JButton("Vytvořit úvěr");
        JButton reviewLoan = new JButton("Zkontrolovat úvěr");
        JButton approveLoan = new JButton("Schválit úvěr");


        createLoan.setFont(new Font("Arial", Font.BOLD, 30));
        reviewLoan.setFont(new Font("Arial", Font.BOLD, 30));
        approveLoan.setFont(new Font("Arial", Font.BOLD, 30));


        basicPanel = new JPanel();
        basicPanel.setLayout(new FlowLayout());
        basicPanel.add(createLoan);
        basicPanel.add(reviewLoan);
        basicPanel.add(approveLoan);

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
        loanForm.setLayout(new GridLayout(5,5));

        JLabel amountText = new JLabel("Částka (CZK):", SwingConstants.CENTER);
        JLabel monthlyPayText = new JLabel("měsiční splátka:", SwingConstants.CENTER);
        JLabel timeText = new JLabel("Po dobu měsiců:", SwingConstants.CENTER);
        JLabel interestText = new JLabel("S úrokem:", SwingConstants.CENTER);
        JButton cancelButton = new JButton("Zpět");
        JButton confirmButton = new JButton("Potvrdit");


        JTextField amountInput = new JTextField();
        JTextField monthlyPayInput = new JTextField();
        JTextField timeInput = new JTextField();
        JTextField interestInput = new JTextField();

        amountText.setFont(new Font("Arial", Font.BOLD, 30));
        monthlyPayText.setFont(new Font("Arial", Font.BOLD, 30));
        timeText.setFont(new Font("Arial", Font.BOLD, 30));
        interestText.setFont(new Font("Arial", Font.BOLD, 30));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 30));
        confirmButton.setFont(new Font("Arial", Font.BOLD, 30));

        amountInput.setFont(new Font("Arial", Font.BOLD, 30));
        monthlyPayInput.setFont(new Font("Arial", Font.BOLD, 30));
        timeInput.setFont(new Font("Arial", Font.BOLD, 30));
        interestInput.setFont(new Font("Arial", Font.BOLD, 30));
        amountInput.setHorizontalAlignment(SwingConstants.CENTER);
        monthlyPayInput.setHorizontalAlignment(SwingConstants.CENTER);
        timeInput.setHorizontalAlignment(SwingConstants.CENTER);
        interestInput.setHorizontalAlignment(SwingConstants.CENTER);

        loanForm.add(amountText);
        loanForm.add(amountInput);
        loanForm.add(monthlyPayText);
        loanForm.add(monthlyPayInput);
        loanForm.add(timeText);
        loanForm.add(timeInput);
        loanForm.add(interestText);
        loanForm.add(interestInput);
        loanForm.add(cancelButton);
        loanForm.add(confirmButton);

        add(loanForm, BorderLayout.CENTER);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(loanForm);
                showMenu();
                revalidate();
                repaint();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(amountInput.getText());
                int canPay = Integer.parseInt(monthlyPayInput.getText());
                int loanDuration = Integer.parseInt(timeInput.getText());
                double interest = Double.parseDouble(interestInput.getText());

                Loan newloan = new Loan(amount, loanDuration, interest,2,canPay,startID++);
                System.out.println(newloan);

                loanSystem.createLoan(newloan);

                remove(loanForm);
                showMenu();
                revalidate();
                repaint();
            }
        });

        revalidate();
        repaint();

    }
    public static void main(String[] args) {
        new GUI();
    }
}
