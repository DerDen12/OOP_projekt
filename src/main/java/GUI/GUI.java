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
    private JPanel reviewButtonPanel;
    private LoanSystem loanSystem;
    private JPanel buttonPanel;
    private JPanel rolePanel;
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
        basicPanel.setLayout(new GridLayout(1,3));

        JButton createLoan = new JButton("Vytvořit úvěr");
        JButton reviewLoan = new JButton("Zkontrolovat úvěr");
        JButton approveLoan = new JButton("Schválit úvěr");

        createLoan.setFont(new Font("Arial", Font.BOLD, 25));
        reviewLoan.setFont(new Font("Arial", Font.BOLD, 25));
        approveLoan.setFont(new Font("Arial", Font.BOLD, 25));

        basicPanel.add(createLoan);
        basicPanel.add(reviewLoan);
        basicPanel.add(approveLoan);

        rolePanel= new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton LoanOfficerButton = new JButton("Úvěrový poradce");
        JButton ManagerButton = new JButton("Manažer");
        JButton OwnerButton = new JButton("Majitel");

        LoanOfficerButton.setBackground(new Color(99, 129, 185));
        ManagerButton.setBackground(new Color(185, 174, 99));
        OwnerButton.setBackground(new Color(123, 191, 119));

        LoanOfficerButton.setFont(new Font("Arial", Font.BOLD, 15));
        ManagerButton.setFont(new Font("Arial", Font.BOLD, 15));
        OwnerButton.setFont(new Font("Arial", Font.BOLD, 15));

        rolePanel.add(LoanOfficerButton);
        rolePanel.add(ManagerButton);
        rolePanel.add(OwnerButton);

        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(basicPanel, BorderLayout.NORTH);
        buttonPanel.add(rolePanel, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);

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
        reviewPanel.setLayout(new GridLayout(6,6));

        JLabel reviewIDText = new JLabel("ID pujčky:");
        JLabel reviewAmountText = new JLabel("Částka (CZK):", SwingConstants.CENTER);
        JLabel reviewPayText = new JLabel("měsiční splátka:", SwingConstants.CENTER);
        JLabel reviewMonthsText = new JLabel("Po dobu měsíců:");
        JLabel reviewInterestText = new JLabel("S úrokem:", SwingConstants.CENTER);
        JLabel reviewTypeText = new JLabel("Typ půjčky:");


        JLabel reviewID = new JLabel();
        JLabel reviewAmount = new JLabel();
        JLabel reviewPay = new JLabel();
        JLabel reviewMonths = new JLabel();
        JLabel reviewInterest = new JLabel();
        JLabel reviewType = new JLabel();

        reviewIDText.setFont(new Font("Arial", Font.BOLD, 25));
        reviewAmountText.setFont(new Font("Arial", Font.BOLD, 25));
        reviewPayText.setFont(new Font("Arial", Font.BOLD, 25));
        reviewMonthsText.setFont(new Font("Arial", Font.BOLD, 25));
        reviewInterestText.setFont(new Font("Arial", Font.BOLD, 25));
        reviewTypeText.setFont(new Font("Arial", Font.BOLD, 25));

        reviewID.setFont(new Font("Arial", Font.BOLD, 25));
        reviewAmount.setFont(new Font("Arial", Font.BOLD, 25));
        reviewPay.setFont(new Font("Arial", Font.BOLD, 25));
        reviewMonths.setFont(new Font("Arial", Font.BOLD, 25));
        reviewInterest.setFont(new Font("Arial", Font.BOLD, 25));
        reviewType.setFont(new Font("Arial", Font.BOLD, 25));

        reviewPanel.add(reviewIDText);
        reviewPanel.add(reviewID);
        reviewPanel.add(reviewAmountText);
        reviewPanel.add(reviewAmount);
        reviewPanel.add(reviewPayText);
        reviewPanel.add(reviewPay);
        reviewPanel.add(reviewMonthsText);
        reviewPanel.add(reviewMonths);
        reviewPanel.add(reviewInterestText);
        reviewPanel.add(reviewInterest);
        reviewPanel.add(reviewTypeText);
        reviewPanel.add(reviewType);

        reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton returnButton = new JButton();
        JButton DenyButton = new JButton();
        JButton ConfirmButton = new JButton();

    }
    private void showForm() {
        remove(buttonPanel);
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

