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

import refactoring.Menu;
import refactoring.constants.ButtonConstants;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;

public class EditCustomerButtonListener implements ActionListener{
	
	Menu parent;

	public EditCustomerButtonListener(Menu parent) {
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean loop = true;

		if (parent.getCustomerService().isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame, MessageConstants.NO_CUSTOMER_MESSAGE, Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();
		} else {
			// TODO Extract in method that prompts and retrieves Customer
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(parent.frame, "Enter Customer ID:");
				parent.customer = parent.getCustomerService().getCustomer(customerID);

				if (parent.customer == null) {
					int reply = JOptionPane.showConfirmDialog(null, null, MessageConstants.USER_NOT_FOUND,
							JOptionPane.YES_NO_OPTION);
					
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						parent.frame.dispose();
						loop = false;
						parent.admin();
					}
				} else {
					loop = false;
				}

			}
			
			parent.frame.dispose();

			parent.frame.dispose();
			parent.frame = new JFrame("Administrator Menu");
			parent.frame.setSize(400, 300);
			parent.frame.setLocation(200, 200);
			parent.frame.addWindowListener(new WindowAdapter() {
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


			JButton saveButton = new JButton("Save");
			JButton cancelButton = new JButton(ButtonConstants.EXIT);

			cancelPanel.add(cancelButton, BorderLayout.SOUTH);
			cancelPanel.add(saveButton, BorderLayout.SOUTH);
			Container content = parent.frame.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(textPanel, BorderLayout.NORTH);
			content.add(cancelPanel, BorderLayout.SOUTH);

			parent.frame.setContentPane(content);
			parent.frame.setSize(340, 350);
			parent.frame.setLocation(200, 200);
			parent.frame.setVisible(true);
			parent.frame.setResizable(false);

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
					parent.frame.dispose();

					parent.admin();
				}
			});
		}
	}
}
