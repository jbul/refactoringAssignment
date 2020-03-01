package refactoring.listeners.customer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import refactoring.Menu;

public class StatementListener implements ActionListener {
	
	Menu parent;
	
	public StatementListener(Menu menu) {
		parent = menu;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		parent.frame.dispose();
		parent.frame = new JFrame("Customer Menu");
		parent.frame.setSize(400, 600);
		parent.frame.setLocation(200, 200);
		parent.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		parent.frame.setVisible(true);

		JLabel label1 = new JLabel("Summary of account transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int i = 0; i < parent.customerAccount.getTransactionList().size(); i++) {
			textArea.append(parent.customerAccount.getTransactionList().get(i).toString());

		}

		textPanel.add(textArea);
		parent.content.removeAll();

		Container content = parent.frame.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		// content.add(label1);
		content.add(textPanel);
		// content.add(returnPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				parent.frame.dispose();
				parent.customer(parent.cust);
			}
		});
	
		
	}	
}
