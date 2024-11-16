package GUI;

import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel basicPanel;
    private JPanel aboveLoanPanel;
    private JPanel loanListPanel;
    private JPanel reviewPanel;
    private JPanel loanForm;
    private LoanSystem loanSystem;
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
        setLayout(new BorderLayout());
        aboveLoanPanel = new JPanel();
        aboveLoanPanel.setLayout(new GridLayout(1,7 ));

        JLabel idlabelText = new JLabel("ID", SwingConstants.CENTER);
        JLabel amountlabelText = new JLabel("Částka", SwingConstants.CENTER);
        JLabel monthlyPaylabelText = new JLabel("Splátka", SwingConstants.CENTER);
        JLabel timelabelText = new JLabel("Počet měs.", SwingConstants.CENTER);
        JLabel interestlabelText = new JLabel("Úrok", SwingConstants.CENTER);
        JLabel typeLabelText = new JLabel("Typ půjčky", SwingConstants.CENTER);
        JLabel statusLabelText = new JLabel("Stav", SwingConstants.CENTER);

        idlabelText.setFont(new Font("Arial", Font.BOLD, 18));
        amountlabelText.setFont(new Font("Arial", Font.BOLD, 18));
        monthlyPaylabelText.setFont(new Font("Arial", Font.BOLD, 18));
        timelabelText.setFont(new Font("Arial", Font.BOLD, 18));
        interestlabelText.setFont(new Font("Arial", Font.BOLD, 18));
        typeLabelText.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabelText.setFont(new Font("Arial", Font.BOLD, 18));

        aboveLoanPanel.add(idlabelText);
        aboveLoanPanel.add(amountlabelText);
        aboveLoanPanel.add(monthlyPaylabelText);
        aboveLoanPanel.add(timelabelText);
        aboveLoanPanel.add(interestlabelText);
        aboveLoanPanel.add(typeLabelText);
        aboveLoanPanel.add(statusLabelText);

        add(aboveLoanPanel, BorderLayout.NORTH);

        basicPanel = new JPanel();
        basicPanel.setLayout(new FlowLayout());

        JButton createLoan = new JButton("Vytvořit úvěr");
        JButton reviewLoan = new JButton("Zkontrolovat úvěr");
        JButton approveLoan = new JButton("Schválit úvěr");

        createLoan.setFont(new Font("Arial", Font.BOLD, 30));
        reviewLoan.setFont(new Font("Arial", Font.BOLD, 30));
        approveLoan.setFont(new Font("Arial", Font.BOLD, 30));

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
    private void displayLoans() {
        if (loanListPanel == null) {
            loanListPanel = new JPanel();
            loanListPanel.setLayout(new GridLayout(0, 7));
            add(loanListPanel, BorderLayout.CENTER);
        } else {
            loanListPanel.removeAll();
        }

        for (Loan loan : loanSystem.getLoans()) {

            JLabel id = new JLabel(String.valueOf(loan.getID()), SwingConstants.CENTER);
            JLabel amount = new JLabel(String.valueOf(loan.getAmount()), SwingConstants.CENTER);
            JLabel monthlyPay = new JLabel(String.valueOf(loan.getCanPay()), SwingConstants.CENTER);
            JLabel time = new JLabel(String.valueOf(loan.getLoanDuration()), SwingConstants.CENTER);
            JLabel interest = new JLabel(String.valueOf(loan.getInterest()), SwingConstants.CENTER);
            JLabel type = new JLabel(String.valueOf(loan.getType()), SwingConstants.CENTER);
            JLabel status = new JLabel(String.valueOf(loan.getStatus()), SwingConstants.CENTER);
            

            loanListPanel.add(id);
            loanListPanel.add(amount);
            loanListPanel.add(monthlyPay);
            loanListPanel.add(time);
            loanListPanel.add(interest);
            loanListPanel.add(type);
            loanListPanel.add(status);

        }

        revalidate();
        repaint();
    }
    private void reviewForm() {
        remove(basicPanel);
        remove(aboveLoanPanel);
        if (loanListPanel != null) {
            loanListPanel.setVisible(false);
        }

        reviewPanel = new JPanel();
        reviewPanel.setLayout(new GridLayout(5,5));

    }
    private void showForm() {
        remove(basicPanel);
        remove(aboveLoanPanel);
        if (loanListPanel != null) {
            loanListPanel.setVisible(false);
        }

        loanForm = new JPanel();
        loanForm.setLayout(new GridLayout(4,4));

        JLabel amountText = new JLabel("Částka (CZK):", SwingConstants.CENTER);
        JLabel monthlyPayText = new JLabel("měsiční splátka:", SwingConstants.CENTER);
        JLabel interestText = new JLabel("S úrokem:", SwingConstants.CENTER);
        JButton cancelButton = new JButton("Zpět");
        JButton confirmButton = new JButton("Potvrdit");


        JTextField amountInput = new JTextField();
        JTextField monthlyPayInput = new JTextField();
        JTextField interestInput = new JTextField();

        amountText.setFont(new Font("Arial", Font.BOLD, 30));
        monthlyPayText.setFont(new Font("Arial", Font.BOLD, 30));
        interestText.setFont(new Font("Arial", Font.BOLD, 30));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 30));
        confirmButton.setFont(new Font("Arial", Font.BOLD, 30));

        amountInput.setFont(new Font("Arial", Font.BOLD, 30));
        monthlyPayInput.setFont(new Font("Arial", Font.BOLD, 30));
        interestInput.setFont(new Font("Arial", Font.BOLD, 30));
        amountInput.setHorizontalAlignment(SwingConstants.CENTER);
        monthlyPayInput.setHorizontalAlignment(SwingConstants.CENTER);
        interestInput.setHorizontalAlignment(SwingConstants.CENTER);

        loanForm.add(amountText);
        loanForm.add(amountInput);
        loanForm.add(monthlyPayText);
        loanForm.add(monthlyPayInput);
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
                displayLoans();
                loanListPanel.setVisible(true);
                revalidate();
                repaint();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(amountInput.getText());
                int canPay = Integer.parseInt(monthlyPayInput.getText());
                double interest = Double.parseDouble(interestInput.getText());

                loanSystem.createLoan(amount, interest, 2, canPay, startID++);

                remove(loanForm);
                showMenu();
                displayLoans();
                loanListPanel.setVisible(true);
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

