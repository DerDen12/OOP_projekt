package GUI;

import Logic.*;
import Roles.LoanOfficer;
import Roles.Manager;
import Roles.Owner;
import Roles.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel basicPanel;
    private JPanel aboveLoanPanel;
    private JPanel loanListPanel;
    private JPanel reviewPanel;
    private JPanel reviewIDpanel;
    private JPanel approveIDpanel;
    private JPanel loanForm;
    private JPanel reviewButtonPanel;
    private LoanSystem loanSystem;
    private JPanel buttonPanel;
    private JPanel rolePanel;
    private Person currentUser;
    private int startID = 1;

    public GUI() {
        loanSystem = new LoanSystem();
        currentUser = new User("New User");

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
                if (currentUser.getAccess()>= 1) {
                showForm();
                revalidate();
                repaint();
                } else {
                    JOptionPane.showMessageDialog(GUI.this,"Nemáte dostatečný přístup k vytvoření úvěru.", "Chyba přístupu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        reviewLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getAccess()>= 2) {
                reviewIDmenu();
                revalidate();
                repaint();
                } else {
                    JOptionPane.showMessageDialog(GUI.this,"Nemáte dostatečný přístup k zkontrlování úvěru.", "Chyba přístupu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        approveLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getAccess()>= 3) {
                    approveIDmenu();
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(GUI.this,"Nemáte dostatečný přístup k povolení úvěru.", "Chyba přístupu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        LoanOfficerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = new LoanOfficer("Loan Officer");
                revalidate();
                repaint();

            }
        });
        ManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = new Manager("Loan Officer");
                revalidate();
                repaint();

            }
        });
        OwnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = new Owner("Loan Officer");
                revalidate();
                repaint();
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
    private void reviewIDmenu() {
        remove(buttonPanel);

        reviewIDpanel = new JPanel();
        reviewIDpanel.setLayout(new GridLayout(2,2));

        JLabel reviewIDtext = new JLabel("ID pujčky:", SwingConstants.CENTER);

        reviewIDtext.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField reviewIDinput = new JTextField("",SwingConstants.CENTER);

        reviewIDinput.setHorizontalAlignment(SwingConstants.CENTER);
        reviewIDinput.setFont(new Font("Arial", Font.BOLD, 18));

        JButton reviewbackButton = new JButton("Vrátit se");
        JButton reviewgoButton = new JButton("Ukázat pujčku");

        reviewbackButton.setFont(new Font("Arial", Font.BOLD, 25));
        reviewgoButton.setFont(new Font("Arial", Font.BOLD, 25));
        reviewbackButton.setPreferredSize(new Dimension(200, 80));
        reviewgoButton.setPreferredSize(new Dimension(200, 80));

        reviewIDpanel.add(reviewIDtext);
        reviewIDpanel.add(reviewIDinput);
        reviewIDpanel.add(reviewbackButton);
        reviewIDpanel.add(reviewgoButton);

        add(reviewIDpanel, BorderLayout.SOUTH);

        reviewgoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IDInputText = reviewIDinput.getText();

                int loanID = Integer.parseInt(IDInputText);

                Loan loan = loanSystem.findLoanByID(loanID);
                if (loan != null) {
                    reviewForm(loan);
                    revalidate();
                    repaint();
                } else {
                    System.out.println("Pujčka nenalezena");
                }
            }
        });
        reviewbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(aboveLoanPanel);
                remove(reviewIDpanel);
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
    private void approveIDmenu() {
        remove(buttonPanel);

        approveIDpanel = new JPanel();
        approveIDpanel.setLayout(new GridLayout(2,2));

        JLabel approveIDtext = new JLabel("ID pujčky:", SwingConstants.CENTER);

        approveIDtext.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField approveIDinput = new JTextField("",SwingConstants.CENTER);

        approveIDinput.setHorizontalAlignment(SwingConstants.CENTER);
        approveIDinput.setFont(new Font("Arial", Font.BOLD, 18));

        JButton approvebackButton = new JButton("Vrátit se");
        JButton approvegoButton = new JButton("Ukázat pujčku");

        approvebackButton.setFont(new Font("Arial", Font.BOLD, 25));
        approvegoButton.setFont(new Font("Arial", Font.BOLD, 25));
        approvebackButton.setPreferredSize(new Dimension(200, 80));
        approvegoButton.setPreferredSize(new Dimension(200, 80));

        approveIDpanel.add(approveIDtext);
        approveIDpanel.add(approveIDinput);
        approveIDpanel.add(approvebackButton);
        approveIDpanel.add(approvegoButton);

        add(approveIDpanel, BorderLayout.SOUTH);

        approvegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IDInputText = approveIDinput.getText();

                int loanID = Integer.parseInt(IDInputText);

                Loan loan = loanSystem.findLoanByID(loanID);
                if (loan != null) {
                    approvalForm(loan);
                    revalidate();
                    repaint();
                } else {
                    System.out.println("Pujčka nenalezena");
                }
            }
        });
        approvebackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(aboveLoanPanel);
                remove(approveIDpanel);
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
    private void approvalForm(Loan loan) {

    }
    private void reviewForm(Loan loan) {
        remove(reviewIDpanel);
        remove(aboveLoanPanel);
        if (loanListPanel != null) {
            loanListPanel.setVisible(false);
        }

        reviewPanel = new JPanel();
        reviewPanel.setLayout(new GridLayout(6,2));

        JLabel reviewIDText = new JLabel("ID pujčky:", SwingConstants.CENTER);
        JLabel reviewAmountText = new JLabel("Částka (CZK):", SwingConstants.CENTER);
        JLabel reviewPayText = new JLabel("měsiční splátka:", SwingConstants.CENTER);
        JLabel reviewMonthsText = new JLabel("Po dobu měsíců:", SwingConstants.CENTER);
        JLabel reviewInterestText = new JLabel("S úrokem:", SwingConstants.CENTER);
        JLabel reviewTypeText = new JLabel("Typ půjčky:", SwingConstants.CENTER);


        JLabel reviewID = new JLabel(String.valueOf(loan.getID()), SwingConstants.CENTER);
        JLabel reviewAmount = new JLabel(String.valueOf(loan.getAmount()), SwingConstants.CENTER);
        JLabel reviewPay = new JLabel(String.valueOf(loan.getCanPay()), SwingConstants.CENTER);
        JLabel reviewMonths = new JLabel(String.valueOf(loan.getLoanDuration()), SwingConstants.CENTER);
        JLabel reviewInterest = new JLabel(String.valueOf(loan.getInterest()), SwingConstants.CENTER);
        JLabel reviewType = new JLabel(String.valueOf(loan.getType()), SwingConstants.CENTER);

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

        add(reviewPanel, BorderLayout.NORTH);

        reviewButtonPanel = new JPanel();
        reviewButtonPanel.setLayout(new GridLayout(1,3));

        JButton returnButton = new JButton("Vrátit se");
        JButton denyButton = new JButton("Odmítnout");
        JButton confirmButton = new JButton("Povolit");

        reviewButtonPanel.add(returnButton);
        reviewButtonPanel.add(denyButton);
        reviewButtonPanel.add(confirmButton);

        returnButton.setFont(new Font("Arial", Font.BOLD, 15));
        denyButton.setFont(new Font("Arial", Font.BOLD, 15));
        confirmButton.setFont(new Font("Arial", Font.BOLD, 15));

        add(reviewButtonPanel, BorderLayout.SOUTH);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(reviewPanel);
                remove(reviewButtonPanel);
                showMenu();
                displayLoans();
                loanListPanel.setVisible(true);
                revalidate();
                repaint();
            }
        });
        denyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loan.setStatus(LoanStatus.DENIED);
                remove(reviewPanel);
                remove(reviewButtonPanel);
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
                loan.setStatus(LoanStatus.REVIEWED);
                remove(reviewPanel);
                remove(reviewButtonPanel);
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
    private void showForm() {
        remove(buttonPanel);
        remove(aboveLoanPanel);
        if (loanListPanel != null) {
            loanListPanel.setVisible(false);
        }

        loanForm = new JPanel();
        loanForm.setLayout(new GridLayout(4,2));

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

