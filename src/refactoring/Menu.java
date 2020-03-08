package refactoring;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import refactoring.constants.ButtonConstants;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;
import refactoring.listeners.admin.AccountButtonListener;
import refactoring.listeners.admin.BankChargesButtonListener;
import refactoring.listeners.admin.DeleteAccountListener;
import refactoring.listeners.admin.DeleteCustomerListener;
import refactoring.listeners.admin.EditCustomerButtonListener;
import refactoring.listeners.admin.InterestButtonListener;
import refactoring.listeners.admin.NavigationListener;
import refactoring.listeners.admin.SummaryButtonListener;
import refactoring.listeners.customer.LodgementButtonListener;
import refactoring.listeners.customer.StatementListener;
import refactoring.listeners.customer.WithdrawButtonListener;
import refactoring.listeners.general.ReturnButtonListener;
import refactoring.service.CustomerService;

public class Menu extends JFrame {

	public int position = 0;
	public String password;
	public Customer customer = null;
	public CustomerAccount customerAccount;
	public JFrame frame, adminFrame;
	public JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	public JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	public JLabel customerIDLabel, passwordLabel;
	public JTextField customerIDTextField, passwordTextField;
	public Container content;
	public Customer cust;

	JPanel panel2;
	JButton addButton;
	String PPS, firstName, surname, DOB, CustomerID;

	private CustomerService customerService = CustomerService.getInstance();

	public static void main(String[] args) {
		Menu driver = new Menu();
		driver.menuStart();
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void menuStart() {
		/*
		 * The menuStart method asks the user if they are a new customer, an existing
		 * customer or an admin. It will then start the create customer process if they
		 * are a new customer, or will ask them to log in if they are an existing
		 * customer or admin.
		 */

		frame = new JFrame("User Type");
		frame.setSize(400, 300);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();

		// TODO Method to add button
		addToPanel(userTypePanel, userType, "Existing Customer", "Customer");
		addToPanel(userTypePanel, userType, Constants.ADMIN, Constants.ADMIN);
		addToPanel(userTypePanel, userType, Constants.NEW_CUSTOMER, Constants.NEW_CUSTOMER);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = frame.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();

				// if user selects NEW CUSTOMER
				switch (user) {
				case Constants.NEW_CUSTOMER:
					newCustomerSwitch();
					break;
				// if user select ADMIN
				case Constants.ADMIN:
					adminSwitch();
					break;
				// if user selects CUSTOMER
				case "Customer":
					customerSwitch();
					break;
				}

			}

			private void customerSwitch() {
				boolean loop = true, loop2 = true;
				boolean cont = false;
				Customer customer = null;

				// Loop to find username
				while (loop) {

					Object customerID = JOptionPane.showInputDialog(frame, "Enter Customer ID:");
					customer = customerService.getCustomer(customerID);

					// TODO Replace found by customer null or not
					if (customer == null) {

						int reply = JOptionPane.showConfirmDialog(null, null, MessageConstants.USER_NOT_FOUND,
								JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {

							loop = true;

						} else if (reply == JOptionPane.NO_OPTION) {

							frame.dispose();
							loop = false;
							loop2 = false;
							menuStart();
						}
					} else {
						loop = false;
					}
				}

				// Loop to check password
				while (loop2) {
					Object customerPassword = JOptionPane.showInputDialog(frame, "Enter Customer Password;");

					if (!customer.getPassword().equals(customerPassword))// check if customer password is correct
					{
						int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
								JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {

							loop2 = true;

						} else if (reply == JOptionPane.NO_OPTION) {

							frame.dispose();
							loop2 = false;
							menuStart();
						}
					} else {
						loop2 = false;
						cont = true;
					}
				}

				if (cont) {
					frame.dispose();
					loop = false;
					customer(customer);
				}
			}

			private void adminSwitch() {
				boolean loop = true, loop2 = true;
				boolean cont = false;

				while (loop) {
					Object adminUsername = JOptionPane.showInputDialog(frame, "Enter Administrator Username:");

					if (!adminUsername.equals("admin"))// search admin list for admin with matching admin username
					{
						int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							loop = true;
						} else if (reply == JOptionPane.NO_OPTION) {
							adminFrame.dispose();
							loop = false;
							loop2 = false;
							menuStart();
						}
					} else {
						loop = false;
					}
				}

				while (loop2) {
					Object adminPassword = JOptionPane.showInputDialog(frame, "Enter Administrator Password;");

					if (!adminPassword.equals("admin11"))// search admin list for admin with matching admin password
					{
						int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {

						} else if (reply == JOptionPane.NO_OPTION) {
							adminFrame.dispose();
							loop2 = false;
							menuStart();
						}
					} else {
						loop2 = false;
						cont = true;
					}
				}

				if (cont) {
					// TODO is null at this stage
					if (adminFrame != null) {
						adminFrame.dispose();
					}
					loop = false;
					admin();
				}
			}

			private void newCustomerSwitch() {
				frame.dispose();
				adminFrame = new JFrame("Create New Customer");
				adminFrame.setSize(400, 300);
				adminFrame.setLocation(200, 200);
				adminFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}
				});
				Container content = adminFrame.getContentPane();
				content.setLayout(new BorderLayout());

				firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
				surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
				pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
				dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
				firstNameTextField = new JTextField(20);
				surnameTextField = new JTextField(20);
				pPSTextField = new JTextField(20);
				dOBTextField = new JTextField(20);
				JPanel panel = new JPanel(new GridLayout(6, 2));
				panel.add(firstNameLabel);
				panel.add(firstNameTextField);
				panel.add(surnameLabel);
				panel.add(surnameTextField);
				panel.add(pPPSLabel);
				panel.add(pPSTextField);
				panel.add(dOBLabel);
				panel.add(dOBTextField);

				panel2 = new JPanel();
				addButton = new JButton("Add");

				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						PPS = pPSTextField.getText();
						firstName = firstNameTextField.getText();
						surname = surnameTextField.getText();
						DOB = dOBTextField.getText();
						password = "";

						CustomerID = "ID" + PPS;

						addButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								adminFrame.dispose();

								boolean loop = true;
								while (loop) {
									password = JOptionPane.showInputDialog(frame, "Enter 7 character Password;");

									if (password.length() != 7)// Making sure password is 7 characters
									{
										JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long",
												JOptionPane.OK_OPTION);
									} else {
										loop = false;
									}
								}

								ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
								Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password,
										accounts);

								getCustomerService().addCustomer(customer);

								JOptionPane.showMessageDialog(frame,
										"CustomerID = " + CustomerID + "\n Password = " + password, "Customer created.",
										JOptionPane.INFORMATION_MESSAGE);
								menuStart();
								frame.dispose();
							}
						});
					}
				});
				JButton cancel = new JButton("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						adminFrame.dispose();
						menuStart();
					}
				});

				panel2.add(addButton);
				panel2.add(cancel);

				content.add(panel, BorderLayout.CENTER);
				content.add(panel2, BorderLayout.SOUTH);

				adminFrame.setVisible(true);
			}
		});
		frame.setVisible(true);
	}

	private void addToPanel(JPanel userTypePanel, final ButtonGroup userType, String buttonTitle, String actionName) {
		JRadioButton radioButton = new JRadioButton(buttonTitle);
		userTypePanel.add(radioButton);
		radioButton.setActionCommand(actionName);
		userType.add(radioButton);
	}

	public void admin() {
		dispose();

		frame = new JFrame("Administrator Menu");
		frame.setSize(400, 400);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		frame.setVisible(true);

		// TODO Same code everywhere, candidate for refactoring
		List<JPanel> panels = new ArrayList<>();
		panels.add(generatePanel("Delete Customer", new DeleteCustomerListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Delete Account", new DeleteAccountListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Apply Bank Charges", new BankChargesButtonListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Apply Interest", new InterestButtonListener(this), null, FlowLayout.LEFT));
		panels.add(
				generatePanel("Edit existing Customer", new EditCustomerButtonListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Navigate Customer Collection", new NavigationListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Display Summary Of All Accounts", new SummaryButtonListener(this), null,
				FlowLayout.LEFT));
		panels.add(
				generatePanel("Add an Account to a Customer", new AccountButtonListener(this), null, FlowLayout.LEFT));
		panels.add(generatePanel("Exit Admin Menu", new ReturnButtonListener(this), null, FlowLayout.RIGHT));
		// End refactoring panels

		addPanels(new GridLayout(10, 1), panels, "Please select an option");

	}

	private JPanel generatePanel(String title, ActionListener listener, Dimension size, int align) {
		JPanel returnPanel = new JPanel(new FlowLayout(align));
		JButton returnButton = new JButton(title);
		returnPanel.add(returnButton);
		returnButton.addActionListener(listener);

		if (size != null) {
			returnPanel.setPreferredSize(size);
		}

		return returnPanel;
	}

	public void customer(Customer e1) {
		frame = new JFrame("Customer Menu");
		// TODO Looks like a bug...
		// e1 = e;
		cust = e1;
		frame.setSize(400, 300);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		frame.setVisible(true);

		// To be used in actionListeners
		Menu context = this;

		if (cust.getAccounts().size() == 0) {
			JOptionPane.showMessageDialog(frame,
					"This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ",
					Constants.OOPS, JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton(ButtonConstants.RETURN);
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			JComboBox<String> box = new JComboBox<String>();
			for (int i = 0; i < cust.getAccounts().size(); i++) {
				box.addItem(cust.getAccounts().get(i).getNumber());
			}

			// TODO Fix bug if continue is clicked without choosing anything. Account should
			// be set to the first from list of accounts.
			if (customerAccount != null) {
				box.setSelectedItem(customerAccount);
			} else {
				customerAccount = cust.getAccounts().get(0);
			}

			box.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < cust.getAccounts().size(); i++) {
						if (cust.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
							customerAccount = cust.getAccounts().get(i);
						}
					}
				}
			});

			boxPanel.add(box);
			content = frame.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ReturnButtonListener(this));

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					frame.dispose();

					frame = new JFrame("Customer Menu");
					frame.setSize(400, 300);
					frame.setLocation(200, 200);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					frame.setVisible(true);

					List<JPanel> panels = new ArrayList<>();

					panels.add(generatePanel("Display Bank Statement", new StatementListener(context),
							new Dimension(250, 20), FlowLayout.LEFT));
					panels.add(generatePanel("Lodge money into account", new LodgementButtonListener(context),
							new Dimension(250, 20), FlowLayout.LEFT));
					panels.add(generatePanel("Withdraw money from account", new WithdrawButtonListener(context),
							new Dimension(250, 20), FlowLayout.LEFT));
					panels.add(generatePanel("Exit CustomerMenu", new ReturnButtonListener(context), null,
							FlowLayout.RIGHT));

					addPanels(new GridLayout(5, 1), panels, "Please select an option");

				}
			});
		}
	}

	private void addPanels(GridLayout layout, List<JPanel> panels, String title) {
		JLabel label1 = new JLabel(title);

		content = frame.getContentPane();
		content.setLayout(layout);
		content.add(label1);

		for (JPanel jp : panels) {
			content.add(jp);
		}
	}

	/**
	 * A method that tests if a string is numeric
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}