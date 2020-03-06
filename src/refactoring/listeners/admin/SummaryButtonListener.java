package refactoring.listeners.admin;

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
import refactoring.constants.ButtonConstants;

public class SummaryButtonListener implements ActionListener {
	
	Menu parent;

	public SummaryButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent ae) {
		parent.frame.dispose();

		parent.frame = new JFrame("Summary of Transactions");
		parent.frame.setSize(400, 700);
		parent.frame.setLocation(200, 200);
		parent.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		parent.frame.setVisible(true);

		JLabel label1 = new JLabel("Summary of all transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton(ButtonConstants.RETURN);
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

		for (int a = 0; a < parent.getCustomerService().size(); a++)
		{
			for (int b = 0; b < parent.getCustomerService().get(a).getAccounts().size(); b++) {
				parent.customerAccount = parent.getCustomerService().get(a).getAccounts().get(b);
				for (int c = 0; c < parent.getCustomerService().get(a).getAccounts().get(b).getTransactionList().size(); c++) {

					textArea.append(parent.customerAccount.getTransactionList().get(c).toString());
					// Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use
					// this to keep a running total but I couldnt get it working.

				}
			}
		}

		textPanel.add(textArea);
		parent.content.removeAll();

		Container content = parent.frame.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(textPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				parent.frame.dispose();
				parent.admin();
			}
		});
	}
		
}
