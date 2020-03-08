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
		parent.frame.dispose();

		if (parent.getCustomerService().isEmpty()) {
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

			setDetails(0);

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
					setDetails(0);
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					if (parent.position > 0) {
						parent.position = parent.position - 1;
						setDetails(parent.position);
					}
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					
					if (parent.position < parent.getCustomerService().size()) {
						
						parent.position = parent.position + 1;
						setDetails(parent.position);
					}	

				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					parent.position = parent.getCustomerService().size() - 1;
					setDetails(parent.position);
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

	private void setDetails(int position) {
		parent.firstNameTextField.setText(parent.getCustomerService().get(position).getFirstName());
		parent.surnameTextField.setText(parent.getCustomerService().get(position).getSurname());
		parent.pPSTextField.setText(parent.getCustomerService().get(position).getPPS());
		parent.dOBTextField.setText(parent.getCustomerService().get(position).getDOB());
		parent.customerIDTextField.setText(parent.getCustomerService().get(position).getCustomerID());
		parent.passwordTextField.setText(parent.getCustomerService().get(position).getPassword());
	}

}
