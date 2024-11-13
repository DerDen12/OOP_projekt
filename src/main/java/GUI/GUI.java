package GUI;

import Logic.Person;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JButton createLoan;
    private JButton reviewLoan;
    private JButton approveLoan;
    private JButton denyLoan;
    private Person currentUser;

    public GUI() {

    setTitle("Bankovnictvý-Úvěry");
    setSize(800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    createLoan = new JButton("Create");
    createLoan.setFont(new Font("Arial", Font.BOLD, 30));


    JPanel Panel = new JPanel();
    Panel.setLayout(new FlowLayout());
    Panel.add(createLoan);

    add(Panel,  BorderLayout.SOUTH);


    setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
