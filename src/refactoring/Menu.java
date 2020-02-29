package refactoring;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import refactoring.listeners.admin.AccountButtonListener;
import refactoring.listeners.admin.BankChargesButtonListener;
import refactoring.listeners.admin.DeleteAccountListener;
import refactoring.listeners.admin.DeleteCustomerListener;
import refactoring.listeners.admin.EditCustomerButtonListener;
import refactoring.listeners.admin.InterestButtonListener;
import refactoring.listeners.admin.NavigationListener;
import refactoring.listeners.admin.SummaryButtonListener;
import refactoring.listeners.general.ReturnButtonListener;

import java.util.Date;
import java.util.List;

public class Menu extends JFrame {

	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	public int position = 0;
	public String password;
	public Customer customer = null;
	public CustomerAccount acc = new CustomerAccount();
	public JFrame f, f1;
	public JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	public JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	public JLabel customerIDLabel, passwordLabel;
	public JTextField customerIDTextField, passwordTextField;
	public Container content;
	public Customer e;

	JPanel panel2;
	JButton add;
	String PPS, firstName, surname, DOB, CustomerID;

	public static void main(String[] args) {
		Menu driver = new Menu();
		
		List<CustomerAccount> ca = Arrays.asList(new CustomerDepositAccount(1.5, "ID1234", 2000.0, new ArrayList<AccountTransaction>()));
		driver.customerList.add(new Customer("1234", "Bob", "Joack", "12091989", "ID1234", "1234", ca));
		
		List<CustomerAccount> ca2 = Arrays.asList(new CustomerDepositAccount(1.5, "ID12345", 2000.0, new ArrayList<AccountTransaction>()));
		driver.customerList.add(new Customer("12345", "Sarah", "Croche", "12091989", "ID12345", "12345", ca2));
		
		List<CustomerAccount> ca3 = Arrays.asList(new CustomerDepositAccount(1.5, "ID12346", 2000.0, new ArrayList<AccountTransaction>()));
		driver.customerList.add(new Customer("12346", "Lara", "Clette", "12091989", "ID12346", "12346", ca3));
		
		List<CustomerAccount> ca4 = Arrays.asList(new CustomerDepositAccount(1.5, "ID12347", 2000.0, new ArrayList<AccountTransaction>()));
		driver.customerList.add(new Customer("12347", "Dede", "Lirant", "12091989", "ID12347", "12347", ca4));
		
		
		driver.menuStart();
	}

	public void menuStart() {
		/*
		 * The menuStart method asks the user if they are a new customer, an existing
		 * customer or an admin. It will then start the create customer process if they
		 * are a new customer, or will ask them to log in if they are an existing
		 * customer or admin.
		 */

		f = new JFrame("User Type");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();
		//JRadioButton radioButton;
		
		// TODO Method to add button
		addToPanel(userTypePanel, userType,"Existing Customer", "Customer");
		
		/*
		 * userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		 * radioButton.setActionCommand("Customer"); userType.add(radioButton);
		 */
		
		addToPanel(userTypePanel, userType,"Administrator", "Administrator");

		/*
		 * userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		 * radioButton.setActionCommand("Administrator"); userType.add(radioButton);
		 */
		
		addToPanel(userTypePanel, userType,"New Customer", "New Customer");
		/*
		 * userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		 * radioButton.setActionCommand("New Customer"); userType.add(radioButton);
		 */
		
		
		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();

				// if user selects NEW
				// CUSTOMER--------------------------------------------------------------------------------------
				if (user.equals("New Customer")) {
					f.dispose();
					f1 = new JFrame("Create New Customer");
					f1.setSize(400, 300);
					f1.setLocation(200, 200);
					f1.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					Container content = f1.getContentPane();
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
					add = new JButton("Add");

					add.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							PPS = pPSTextField.getText();
							firstName = firstNameTextField.getText();
							surname = surnameTextField.getText();
							DOB = dOBTextField.getText();
							password = "";

							CustomerID = "ID" + PPS;

							add.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									f1.dispose();

									boolean loop = true;
									while (loop) {
										password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

										if (password.length() != 7)// Making sure password is 7 characters
										{
											JOptionPane.showMessageDialog(null, null,
													"Password must be 7 charatcers long", JOptionPane.OK_OPTION);
										} else {
											loop = false;
										}
									}

									ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
									Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password,
											accounts);

									customerList.add(customer);

									JOptionPane.showMessageDialog(f,
											"CustomerID = " + CustomerID + "\n Password = " + password,
											"Customer created.", JOptionPane.INFORMATION_MESSAGE);
									menuStart();
									f.dispose();
								}
							});
						}
					});
					JButton cancel = new JButton("Cancel");
					cancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							f1.dispose();
							menuStart();
						}
					});

					panel2.add(add);
					panel2.add(cancel);

					content.add(panel, BorderLayout.CENTER);
					content.add(panel2, BorderLayout.SOUTH);

					f1.setVisible(true);

				}

				// ------------------------------------------------------------------------------------------------------------------

				// if user select
				// ADMIN----------------------------------------------------------------------------------------------
				if (user.equals("Administrator")) {
					boolean loop = true, loop2 = true;
					boolean cont = false;
					while (loop) {
						Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

						if (!adminUsername.equals("admin"))// search admin list for admin with matching admin username
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if (reply == JOptionPane.NO_OPTION) {
								f1.dispose();
								loop = false;
								loop2 = false;
								menuStart();
							}
						} else {
							loop = false;
						}
					}

					while (loop2) {
						Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

						if (!adminPassword.equals("admin11"))// search admin list for admin with matching admin password
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

							} else if (reply == JOptionPane.NO_OPTION) {
								f1.dispose();
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
						if (f1 != null) {
							f1.dispose();
						}
						loop = false;
						admin();
					}
				}
				// ----------------------------------------------------------------------------------------------------------------

				// if user selects CUSTOMER
				// ----------------------------------------------------------------------------------------
				if (user.equals("Customer")) {
					boolean loop = true, loop2 = true;
					boolean cont = false;
					boolean found = false;
					Customer customer = null;
					while (loop) {
						Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

						for (Customer aCustomer : customerList) {

							if (aCustomer.getCustomerID().equals(customerID))// search customer list for matching
																				// customer ID
							{
								found = true;
								customer = aCustomer;
								// TODO Added break, if found, no need to loop through everything...
								break;
							}
						}

						if (found == false) {
							int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {
								loop = true;
							} else if (reply == JOptionPane.NO_OPTION) {
								f.dispose();
								loop = false;
								loop2 = false;
								menuStart();
							}
						} else {
							loop = false;
						}

					}

					while (loop2) {
						Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

						if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
						{
							int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
									JOptionPane.YES_NO_OPTION);
							if (reply == JOptionPane.YES_OPTION) {

							} else if (reply == JOptionPane.NO_OPTION) {
								f.dispose();
								loop2 = false;
								menuStart();
							}
						} else {
							loop2 = false;
							cont = true;
						}
					}

					if (cont) {
						f.dispose();
						loop = false;
						customer(customer);
					}
				}
				// -----------------------------------------------------------------------------------------------------------------------
			}
		});
		f.setVisible(true);
	}

	private void addToPanel(JPanel userTypePanel, final ButtonGroup userType, String buttonTitle, String actionName) {
		JRadioButton radioButton = new JRadioButton(buttonTitle);
		userTypePanel.add(radioButton);
		radioButton.setActionCommand(actionName);
		userType.add(radioButton);
	}

	public void admin() {
		dispose();

		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);
		
		// TODO Same code everywhere, candidate for refactoring
		List<JPanel> panels = new ArrayList<>();
		panels.add(generatePanel("Delete Customer", new DeleteCustomerListener(this), FlowLayout.LEFT));
		/*
		 * JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 * JButton deleteCustomer = new JButton("Delete Customer");
		 * deleteCustomer.setPreferredSize(new Dimension(250, 20));
		 * deleteCustomerPanel.add(deleteCustomer); deleteCustomer.addActionListener(new
		 * DeleteCustomerListener(this));
		 */
		
		
		panels.add(generatePanel("Delete Account", new DeleteAccountListener(this), FlowLayout.LEFT));
		/*
		 * JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 * JButton deleteAccount = new JButton("Delete Account");
		 * deleteAccount.setPreferredSize(new Dimension(250, 20));
		 * deleteAccountPanel.add(deleteAccount); deleteAccount.addActionListener(new
		 * DeleteAccountListener(this));
		 */
		
		
		panels.add(generatePanel("Apply Bank Charges", new BankChargesButtonListener(this), FlowLayout.LEFT));
		/*
		 * JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 * JButton bankChargesButton = new JButton("Apply Bank Charges");
		 * bankChargesButton.setPreferredSize(new Dimension(250, 20));
		 * bankChargesPanel.add(bankChargesButton);
		 * bankChargesButton.addActionListener(new BankChargesButtonListener(this));
		 */
		
		
		panels.add(generatePanel("Apply Interest", new InterestButtonListener(this), FlowLayout.LEFT));
		/*
		 * JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); JButton
		 * interestButton = new JButton("Apply Interest");
		 * interestPanel.add(interestButton); interestButton.setPreferredSize(new
		 * Dimension(250, 20)); interestButton.addActionListener(new
		 * InterestButtonListener(this));
		 */
		
		panels.add(generatePanel("Edit existing Customer", new EditCustomerButtonListener(this), FlowLayout.LEFT));
		/*
		 * JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 * JButton editCustomerButton = new JButton("Edit existing Customer");
		 * editCustomerPanel.add(editCustomerButton);
		 * editCustomerButton.setPreferredSize(new Dimension(250, 20));
		 * editCustomerButton.addActionListener(new EditCustomerButtonListener(this));
		 */
		
		
		panels.add(generatePanel("Navigate Customer Collection", new NavigationListener(this), FlowLayout.LEFT));
		/*
		 * JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); JButton
		 * navigateButton = new JButton("Navigate Customer Collection");
		 * navigatePanel.add(navigateButton); navigateButton.setPreferredSize(new
		 * Dimension(250, 20)); navigateButton.addActionListener(new
		 * NavigationListener(this));
		 */
		
		
		panels.add(generatePanel("Display Summary Of All Accounts", new SummaryButtonListener(this), FlowLayout.LEFT));
		/*
		 * JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); JButton
		 * summaryButton = new JButton("Display Summary Of All Accounts");
		 * summaryPanel.add(summaryButton); summaryButton.setPreferredSize(new
		 * Dimension(250, 20)); summaryButton.addActionListener(new
		 * SummaryButtonListener(this));
		 */

		
		panels.add(generatePanel("Add an Account to a Customer", new AccountButtonListener(this), FlowLayout.LEFT));
		/*
		 * JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); JButton
		 * accountButton = new JButton("Add an Account to a Customer");
		 * accountPanel.add(accountButton); accountButton.setPreferredSize(new
		 * Dimension(250, 20)); accountButton.addActionListener(new
		 * AccountButtonListener(this));
		 */

		panels.add(generatePanel("Exit Admin Menu", new ReturnButtonListener(this), FlowLayout.RIGHT));
		// End refactoring panels
		
		
		JLabel label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		for(JPanel p: panels) {
			content.add(p);
		}
		/*
		 * content.add(label1); content.add(accountPanel);
		 * content.add(bankChargesPanel); content.add(interestPanel);
		 * content.add(editCustomerPanel); content.add(navigatePanel);
		 * content.add(summaryPanel); content.add(deleteCustomerPanel); //
		 * content.add(deleteAccountPanel); content.add(returnPanel);
		 */
	}

	private JPanel generatePanel(String title, ActionListener listener, int align) {
		JPanel returnPanel = new JPanel(new FlowLayout(align));
		JButton returnButton = new JButton(title);
		returnPanel.add(returnButton);
		returnButton.addActionListener(listener);
		return returnPanel;
	}

	public void customer(Customer e1) {
		f = new JFrame("Customer Menu");
		// TODO Looks like a bug...
		// e1 = e;
		e = e1;
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		if (e.getAccounts().size() == 0) {
			JOptionPane.showMessageDialog(f,
					"This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ",
					"Oops!", JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			JComboBox<String> box = new JComboBox<String>();
			for (int i = 0; i < e.getAccounts().size(); i++) {
				box.addItem(e.getAccounts().get(i).getNumber());
			}

			for (int i = 0; i < e.getAccounts().size(); i++) {
				if (e.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = e.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					menuStart();
				}
			});

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					f.dispose();

					f = new JFrame("Customer Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					f.setVisible(true);

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton statementButton = new JButton("Display Bank Statement");
					statementButton.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(statementButton);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton lodgementButton = new JButton("Lodge money into account");
					lodgementPanel.add(lodgementButton);
					lodgementButton.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton withdrawButton = new JButton("Withdraw money from account");
					withdrawalPanel.add(withdrawButton);
					withdrawButton.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					JButton returnButton = new JButton("Exit Customer Menu");
					returnPanel.add(returnButton);

					JLabel label1 = new JLabel("Please select an option");

					content = f.getContentPane();
					content.setLayout(new GridLayout(5, 1));
					content.add(label1);
					content.add(statementPanel);
					content.add(lodgementPanel);
					content.add(withdrawalPanel);
					content.add(returnPanel);

					statementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							f = new JFrame("Customer Menu");
							f.setSize(400, 600);
							f.setLocation(200, 200);
							f.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									System.exit(0);
								}
							});
							f.setVisible(true);

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

							for (int i = 0; i < acc.getTransactionList().size(); i++) {
								textArea.append(acc.getTransactionList().get(i).toString());

							}

							textPanel.add(textArea);
							content.removeAll();

							Container content = f.getContentPane();
							content.setLayout(new GridLayout(1, 1));
							// content.add(label1);
							content.add(textPanel);
							// content.add(returnPanel);

							returnButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									f.dispose();
									customer(e);
								}
							});
						}
					});

					lodgementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double balance = 0;

							if (acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while (loop) {
									if (count == 0) {
										JOptionPane.showMessageDialog(f,
												"Pin entered incorrectly 3 times. ATM card locked.", "Pin",
												JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(e);
										loop = false;
										on = false;
									}

									String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
									int i = Integer.parseInt(Pin);

									if (on) {
										if (checkPin == i) {
											loop = false;
											JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
													JOptionPane.INFORMATION_MESSAGE);

										} else {
											count--;
											JOptionPane.showMessageDialog(f,
													"Incorrect pin. " + count + " attempts remaining.", "Pin",
													JOptionPane.INFORMATION_MESSAGE);
										}

									}
								}

							}
							if (on == true) {
								String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");// the
																														// isNumeric
																														// method
																														// tests
																														// to
																														// see
																														// if
																														// the
																														// string
																														// entered
																														// was
																														// numeric.
								if (isNumeric(balanceTest)) {

									balance = Double.parseDouble(balanceTest);
									loop = false;

								} else {
									JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
											JOptionPane.INFORMATION_MESSAGE);
								}

								String euro = "\u20ac";
								acc.setBalance(acc.getBalance() + balance);
								// String date = new
								// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
								Date date = new Date();
								String date2 = date.toString();
								String type = "Lodgement";
								double amount = balance;

								AccountTransaction transaction = new AccountTransaction(date2, type, amount);
								acc.getTransactionList().add(transaction);

								JOptionPane.showMessageDialog(f, balance + euro + " added do you account!", "Lodgement",
										JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro,
										"Lodgement", JOptionPane.INFORMATION_MESSAGE);
							}

						}
					});

					withdrawButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							boolean loop = true;
							boolean on = true;
							double withdraw = 0;

							if (acc instanceof CustomerCurrentAccount) {
								int count = 3;
								int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
								loop = true;

								while (loop) {
									if (count == 0) {
										JOptionPane.showMessageDialog(f,
												"Pin entered incorrectly 3 times. ATM card locked.", "Pin",
												JOptionPane.INFORMATION_MESSAGE);
										((CustomerCurrentAccount) acc).getAtm().setValid(false);
										customer(e);
										loop = false;
										on = false;
									}

									String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
									int i = Integer.parseInt(Pin);

									if (on) {
										if (checkPin == i) {
											loop = false;
											JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
													JOptionPane.INFORMATION_MESSAGE);

										} else {
											count--;
											JOptionPane.showMessageDialog(f,
													"Incorrect pin. " + count + " attempts remaining.", "Pin",
													JOptionPane.INFORMATION_MESSAGE);

										}

									}
								}

							}
							if (on == true) {
								String balanceTest = JOptionPane.showInputDialog(f,
										"Enter amount you wish to withdraw (max 500):");// the isNumeric method tests to
																						// see if the string entered was
																						// numeric.
								if (isNumeric(balanceTest)) {

									withdraw = Double.parseDouble(balanceTest);
									loop = false;

								} else {
									JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
											JOptionPane.INFORMATION_MESSAGE);
								}
								if (withdraw > 500) {
									JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.",
											"Oops!", JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;
								}
								if (withdraw > acc.getBalance()) {
									JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!",
											JOptionPane.INFORMATION_MESSAGE);
									withdraw = 0;
								}

								String euro = "\u20ac";
								acc.setBalance(acc.getBalance() - withdraw);
								// recording transaction:
								// String date = new
								// SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
								Date date = new Date();
								String date2 = date.toString();

								String type = "Withdraw";
								double amount = withdraw;

								AccountTransaction transaction = new AccountTransaction(date2, type, amount);
								acc.getTransactionList().add(transaction);

								JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw",
										JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw",
										JOptionPane.INFORMATION_MESSAGE);
							}

						}
					});

					returnButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							menuStart();
						}
					});
				}
			});
		}
	}

	public static boolean isNumeric(String str) // a method that tests if a string is numeric
	{
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
