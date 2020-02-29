package refactoring.listeners.admin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import refactoring.Customer;
import refactoring.Menu;

public class EditCustomerButtonListener implements ActionListener{
	
	Menu parent;

	public EditCustomerButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent ae) {

		boolean loop = true;

		boolean found = false;

		if (parent.customerList.isEmpty()) {
			JOptionPane.showMessageDialog(parent.f, "There are no customers yet!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			parent.f.dispose();
			parent.admin();

		} else {
			// TODO Extract in method that prompts and retrieves Customer
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.f, "Enter Customer ID:");

				for (Customer aCustomer : parent.customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						parent.customer = aCustomer;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						parent.f.dispose();
						loop = false;

						parent.admin();
					}
				} else {
					loop = false;
				}

			}
			// End of refactoring
			
			parent.f.dispose();

			parent.f.dispose();
			parent.f = new JFrame("Administrator Menu");
			parent.f.setSize(400, 300);
			parent.f.setLocation(200, 200);
			parent.f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

			parent.firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			parent.surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			parent.pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			parent.dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			parent.customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			parent.passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			parent.firstNameTextField = new JTextField(20);
			parent.surnameTextField = new JTextField(20);
			parent.pPSTextField = new JTextField(20);
			parent.dOBTextField = new JTextField(20);
			parent.customerIDTextField = new JTextField(20);
			parent.passwordTextField = new JTextField(20);

			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(parent.firstNameLabel);
			textPanel.add(parent.firstNameTextField);
			textPanel.add(parent.surnameLabel);
			textPanel.add(parent.surnameTextField);
			textPanel.add(parent.pPPSLabel);
			textPanel.add(parent.pPSTextField);
			textPanel.add(parent.dOBLabel);
			textPanel.add(parent.dOBTextField);
			textPanel.add(parent.customerIDLabel);
			textPanel.add(parent.customerIDTextField);
			textPanel.add(parent.passwordLabel);
			textPanel.add(parent.passwordTextField);

			parent.firstNameTextField.setText(parent.customer.getFirstName());
			parent.surnameTextField.setText(parent.customer.getSurname());
			parent.pPSTextField.setText(parent.customer.getPPS());
			parent.dOBTextField.setText(parent.customer.getDOB());
			parent.customerIDTextField.setText(parent.customer.getCustomerID());
			parent.passwordTextField.setText(parent.customer.getPassword());

			// JLabel label1 = new JLabel("Edit customer details below. The save");

			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton("Exit");

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);
			// content.removeAll();
			Container content = parent.f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);

			parent.f.setContentPane(content);
			parent.f.setSize(340, 350);
			parent.f.setLocation(200, 200);
			parent.f.setVisible(true);
			parent.f.setResizable(false);

			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					parent.customer.setFirstName(parent.firstNameTextField.getText());
					parent.customer.setSurname(parent.surnameTextField.getText());
					parent.customer.setPPS(parent.pPSTextField.getText());
					parent.customer.setDOB(parent.dOBTextField.getText());
					parent.customer.setCustomerID(parent.customerIDTextField.getText());
					parent.customer.setPassword(parent.passwordTextField.getText());

					JOptionPane.showMessageDialog(null, "Changes Saved.");
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					parent.f.dispose();

					parent.admin();
				}
			});
		}
	}
	

}
/*
 * 
 * public void actionPerformed(ActionEvent ae) {
 * 
 * boolean loop = true;
 * 
 * boolean found = false;
 * 
 * if (customerList.isEmpty()) { JOptionPane.showMessageDialog(f,
 * "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
 * f.dispose(); admin();
 * 
 * } else { // TODO Extract in method that prompts and retrieves Customer while
 * (loop) { Object customerID = JOptionPane.showInputDialog(f,
 * "Enter Customer ID:");
 * 
 * for (Customer aCustomer : customerList) {
 * 
 * if (aCustomer.getCustomerID().equals(customerID)) { found = true; customer =
 * aCustomer; } }
 * 
 * if (found == false) { int reply = JOptionPane.showConfirmDialog(null, null,
 * "User not found. Try again?", JOptionPane.YES_NO_OPTION); if (reply ==
 * JOptionPane.YES_OPTION) { loop = true; } else if (reply ==
 * JOptionPane.NO_OPTION) { f.dispose(); loop = false;
 * 
 * admin(); } } else { loop = false; }
 * 
 * } // End of refactoring
 * 
 * f.dispose();
 * 
 * f.dispose(); f = new JFrame("Administrator Menu"); f.setSize(400, 300);
 * f.setLocation(200, 200); f.addWindowListener(new WindowAdapter() { public
 * void windowClosing(WindowEvent we) { System.exit(0); } });
 * 
 * firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT); surnameLabel
 * = new JLabel("Surname:", SwingConstants.LEFT); pPPSLabel = new
 * JLabel("PPS Number:", SwingConstants.LEFT); dOBLabel = new
 * JLabel("Date of birth", SwingConstants.LEFT); customerIDLabel = new
 * JLabel("CustomerID:", SwingConstants.LEFT); passwordLabel = new
 * JLabel("Password:", SwingConstants.LEFT); firstNameTextField = new
 * JTextField(20); surnameTextField = new JTextField(20); pPSTextField = new
 * JTextField(20); dOBTextField = new JTextField(20); customerIDTextField = new
 * JTextField(20); passwordTextField = new JTextField(20);
 * 
 * JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
 * 
 * JPanel cancelPanel = new JPanel();
 * 
 * textPanel.add(firstNameLabel); textPanel.add(firstNameTextField);
 * textPanel.add(surnameLabel); textPanel.add(surnameTextField);
 * textPanel.add(pPPSLabel); textPanel.add(pPSTextField);
 * textPanel.add(dOBLabel); textPanel.add(dOBTextField);
 * textPanel.add(customerIDLabel); textPanel.add(customerIDTextField);
 * textPanel.add(passwordLabel); textPanel.add(passwordTextField);
 * 
 * firstNameTextField.setText(customer.getFirstName());
 * surnameTextField.setText(customer.getSurname());
 * pPSTextField.setText(customer.getPPS());
 * dOBTextField.setText(customer.getDOB());
 * customerIDTextField.setText(customer.getCustomerID());
 * passwordTextField.setText(customer.getPassword());
 * 
 * // JLabel label1 = new JLabel("Edit customer details below. The save");
 * 
 * JButton saveButton = new JButton("Save"); JButton cancelButton = new
 * JButton("Exit");
 * 
 * cancelPanel.add(cancelButton, BorderLayout.SOUTH);
 * cancelPanel.add(saveButton, BorderLayout.SOUTH); // content.removeAll();
 * Container content = f.getContentPane(); content.setLayout(new GridLayout(2,
 * 1)); content.add(textPanel, BorderLayout.NORTH); content.add(cancelPanel,
 * BorderLayout.SOUTH);
 * 
 * f.setContentPane(content); f.setSize(340, 350); f.setLocation(200, 200);
 * f.setVisible(true); f.setResizable(false);
 * 
 * saveButton.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent ae) {
 * 
 * customer.setFirstName(firstNameTextField.getText());
 * customer.setSurname(surnameTextField.getText());
 * customer.setPPS(pPSTextField.getText());
 * customer.setDOB(dOBTextField.getText());
 * customer.setCustomerID(customerIDTextField.getText());
 * customer.setPassword(passwordTextField.getText());
 * 
 * JOptionPane.showMessageDialog(null, "Changes Saved."); } });
 * 
 * cancelButton.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent ae) { f.dispose();
 * 
 * admin(); } }); } }
 */
