package refactoring.listeners.admin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import refactoring.Menu;

public class NavigationListener implements ActionListener {
	
	Menu parent;
	
	public NavigationListener(Menu parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.f.dispose();
		
		if (parent.customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			parent.admin();
		} else {

			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			Container content = parent.getContentPane();

			content.setLayout(new BorderLayout());

			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();
			
			// TODO Redundant with summaryButton actionListener
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

			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");

			parent.firstNameTextField.setText(parent.customerList.get(0).getFirstName());
			parent.surnameTextField.setText(parent.customerList.get(0).getSurname());
			parent.pPSTextField.setText(parent.customerList.get(0).getPPS());
			parent.dOBTextField.setText(parent.customerList.get(0).getDOB());
			parent.customerIDTextField.setText(parent.customerList.get(0).getCustomerID());
			parent.passwordTextField.setText(parent.customerList.get(0).getPassword());

			parent.firstNameTextField.setEditable(false);
			parent.surnameTextField.setEditable(false);
			parent.pPSTextField.setEditable(false);
			parent.dOBTextField.setEditable(false);
			parent.customerIDTextField.setEditable(false);
			parent.passwordTextField.setEditable(false);

			gridPanel.add(parent.firstNameLabel);
			gridPanel.add(parent.firstNameTextField);
			gridPanel.add(parent.surnameLabel);
			gridPanel.add(parent.surnameTextField);
			gridPanel.add(parent.pPPSLabel);
			gridPanel.add(parent.pPSTextField);
			gridPanel.add(parent.dOBLabel);
			gridPanel.add(parent.dOBTextField);
			gridPanel.add(parent.customerIDLabel);
			gridPanel.add(parent.customerIDTextField);
			gridPanel.add(parent.passwordLabel);
			gridPanel.add(parent.passwordTextField);

			buttonPanel.add(first);
			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(last);

			cancelPanel.add(cancel);

			content.add(gridPanel, BorderLayout.NORTH);
			content.add(buttonPanel, BorderLayout.CENTER);
			content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					parent.position = 0;
					parent.firstNameTextField.setText(parent.customerList.get(0).getFirstName());
					parent.surnameTextField.setText(parent.customerList.get(0).getSurname());
					parent.pPSTextField.setText(parent.customerList.get(0).getPPS());
					parent.dOBTextField.setText(parent.customerList.get(0).getDOB());
					parent.customerIDTextField.setText(parent.customerList.get(0).getCustomerID());
					parent.passwordTextField.setText(parent.customerList.get(0).getPassword());
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					if (parent.position < 1) {
						// don't do anything
					} else {
						parent.position = parent.position - 1;

						parent.firstNameTextField.setText(parent.customerList.get(parent.position).getFirstName());
						parent.surnameTextField.setText(parent.customerList.get(parent.position).getSurname());
						parent.pPSTextField.setText(parent.customerList.get(parent.position).getPPS());
						parent.dOBTextField.setText(parent.customerList.get(parent.position).getDOB());
						parent.customerIDTextField.setText(parent.customerList.get(parent.position).getCustomerID());
						parent.passwordTextField.setText(parent.customerList.get(parent.position).getPassword());
					}
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					if (parent.position == parent.customerList.size() - 1) {
						// don't do anything
					} else {
						parent.position = parent.position + 1;

						parent.firstNameTextField.setText(parent.customerList.get(parent.position).getFirstName());
						parent.surnameTextField.setText(parent.customerList.get(parent.position).getSurname());
						parent.pPSTextField.setText(parent.customerList.get(parent.position).getPPS());
						parent.dOBTextField.setText(parent.customerList.get(parent.position).getDOB());
						parent.customerIDTextField.setText(parent.customerList.get(parent.position).getCustomerID());
						parent.passwordTextField.setText(parent.customerList.get(parent.position).getPassword());
					}

				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					parent.position = parent.customerList.size() - 1;

					parent.firstNameTextField.setText(parent.customerList.get(parent.position).getFirstName());
					parent.surnameTextField.setText(parent.customerList.get(parent.position).getSurname());
					parent.pPSTextField.setText(parent.customerList.get(parent.position).getPPS());
					parent.dOBTextField.setText(parent.customerList.get(parent.position).getDOB());
					parent.customerIDTextField.setText(parent.customerList.get(parent.position).getCustomerID());
					parent.passwordTextField.setText(parent.customerList.get(parent.position).getPassword());
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					parent.dispose();
					parent.admin();
				}
			});
			parent.setContentPane(content);
			parent.setSize(400, 300);
			parent.setVisible(true);
		}
	}

}
// Replaces in Menu class
//new ActionListener() {
//	public void actionPerformed(ActionEvent ae) {
//		f.dispose();
//		
//		if (customerList.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
//			admin();
//		} else {
//
//			JButton first, previous, next, last, cancel;
//			JPanel gridPanel, buttonPanel, cancelPanel;
//
//			Container content = getContentPane();
//
//			content.setLayout(new BorderLayout());
//
//			buttonPanel = new JPanel();
//			gridPanel = new JPanel(new GridLayout(8, 2));
//			cancelPanel = new JPanel();
//			
//			// TODO Redundant with summaryButton actionListener
//			firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
//			surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
//			pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
//			dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
//			customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
//			passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
//			firstNameTextField = new JTextField(20);
//			surnameTextField = new JTextField(20);
//			pPSTextField = new JTextField(20);
//			dOBTextField = new JTextField(20);
//			customerIDTextField = new JTextField(20);
//			passwordTextField = new JTextField(20);
//
//			first = new JButton("First");
//			previous = new JButton("Previous");
//			next = new JButton("Next");
//			last = new JButton("Last");
//			cancel = new JButton("Cancel");
//
//			firstNameTextField.setText(customerList.get(0).getFirstName());
//			surnameTextField.setText(customerList.get(0).getSurname());
//			pPSTextField.setText(customerList.get(0).getPPS());
//			dOBTextField.setText(customerList.get(0).getDOB());
//			customerIDTextField.setText(customerList.get(0).getCustomerID());
//			passwordTextField.setText(customerList.get(0).getPassword());
//
//			firstNameTextField.setEditable(false);
//			surnameTextField.setEditable(false);
//			pPSTextField.setEditable(false);
//			dOBTextField.setEditable(false);
//			customerIDTextField.setEditable(false);
//			passwordTextField.setEditable(false);
//
//			gridPanel.add(firstNameLabel);
//			gridPanel.add(firstNameTextField);
//			gridPanel.add(surnameLabel);
//			gridPanel.add(surnameTextField);
//			gridPanel.add(pPPSLabel);
//			gridPanel.add(pPSTextField);
//			gridPanel.add(dOBLabel);
//			gridPanel.add(dOBTextField);
//			gridPanel.add(customerIDLabel);
//			gridPanel.add(customerIDTextField);
//			gridPanel.add(passwordLabel);
//			gridPanel.add(passwordTextField);
//
//			buttonPanel.add(first);
//			buttonPanel.add(previous);
//			buttonPanel.add(next);
//			buttonPanel.add(last);
//
//			cancelPanel.add(cancel);
//
//			content.add(gridPanel, BorderLayout.NORTH);
//			content.add(buttonPanel, BorderLayout.CENTER);
//			content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
//			first.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//					position = 0;
//					firstNameTextField.setText(customerList.get(0).getFirstName());
//					surnameTextField.setText(customerList.get(0).getSurname());
//					pPSTextField.setText(customerList.get(0).getPPS());
//					dOBTextField.setText(customerList.get(0).getDOB());
//					customerIDTextField.setText(customerList.get(0).getCustomerID());
//					passwordTextField.setText(customerList.get(0).getPassword());
//				}
//			});
//
//			previous.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//
//					if (position < 1) {
//						// don't do anything
//					} else {
//						position = position - 1;
//
//						firstNameTextField.setText(customerList.get(position).getFirstName());
//						surnameTextField.setText(customerList.get(position).getSurname());
//						pPSTextField.setText(customerList.get(position).getPPS());
//						dOBTextField.setText(customerList.get(position).getDOB());
//						customerIDTextField.setText(customerList.get(position).getCustomerID());
//						passwordTextField.setText(customerList.get(position).getPassword());
//					}
//				}
//			});
//
//			next.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//
//					if (position == customerList.size() - 1) {
//						// don't do anything
//					} else {
//						position = position + 1;
//
//						firstNameTextField.setText(customerList.get(position).getFirstName());
//						surnameTextField.setText(customerList.get(position).getSurname());
//						pPSTextField.setText(customerList.get(position).getPPS());
//						dOBTextField.setText(customerList.get(position).getDOB());
//						customerIDTextField.setText(customerList.get(position).getCustomerID());
//						passwordTextField.setText(customerList.get(position).getPassword());
//					}
//
//				}
//			});
//
//			last.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//
//					position = customerList.size() - 1;
//
//					firstNameTextField.setText(customerList.get(position).getFirstName());
//					surnameTextField.setText(customerList.get(position).getSurname());
//					pPSTextField.setText(customerList.get(position).getPPS());
//					dOBTextField.setText(customerList.get(position).getDOB());
//					customerIDTextField.setText(customerList.get(position).getCustomerID());
//					passwordTextField.setText(customerList.get(position).getPassword());
//				}
//			});
//
//			cancel.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent ae) {
//					dispose();
//					admin();
//				}
//			});
//			setContentPane(content);
//			setSize(400, 300);
//			setVisible(true);
//		}
//	}
//}