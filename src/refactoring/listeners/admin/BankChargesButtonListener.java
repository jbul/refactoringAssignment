package refactoring.listeners.admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import refactoring.Menu;
import refactoring.constants.ButtonConstants;
import refactoring.constants.Constants;
import refactoring.constants.MessageConstants;
import refactoring.listeners.general.ReturnButtonListener;
import refactoring.service.AccountService;

public class BankChargesButtonListener extends AdminActionListener {

	AccountService accountService = AccountService.getInstance();

	public BankChargesButtonListener(Menu parent) {
		super(parent);
	}

	@Override
	void performAdminAction() {
		parent.frame.dispose();
		parent.frame = new JFrame("Administrator Menu");
		parent.frame.setSize(400, 300);
		parent.frame.setLocation(200, 200);
		parent.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		parent.frame.setVisible(true);

		JComboBox<String> box = new JComboBox<String>();
		for (int i = 0; i < parent.customer.getAccounts().size(); i++) {

			box.addItem(parent.customer.getAccounts().get(i).getNumber());
		}

		box.getSelectedItem();

		JPanel boxPanel = new JPanel();
		boxPanel.add(box);

		JPanel buttonPanel = new JPanel();
		JButton continueButton = new JButton(ButtonConstants.APPLY_CHARGE);
		JButton returnButton = new JButton(ButtonConstants.RETURN);
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
		Container content = parent.frame.getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);

		if (parent.customer.getAccounts().isEmpty()) {
			JOptionPane.showMessageDialog(parent.frame,
					"This customer has no accounts! \n The admin must add acounts to this customer.", Constants.OOPS,
					JOptionPane.INFORMATION_MESSAGE);
			parent.frame.dispose();
			parent.admin();
		} else {

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					// TODO Fixed bug, account was not selected properly
					for (int i = 0; i < parent.customer.getAccounts().size(); i++) {
						if (parent.customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
							parent.customerAccount = parent.customer.getAccounts().get(i);
						}
					}

					if (parent.customerAccount.isDeposit()) {
						JOptionPane.showMessageDialog(parent.frame,
								"25" + Constants.EURO + " deposit account fee aplied.", "",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(parent.frame,
								"15" + Constants.EURO + " current account fee aplied.", "",
								JOptionPane.INFORMATION_MESSAGE);

					}

					// TODO Duplicated code in if statement, moved out. Only keeping message about
					// number applied in if
					accountService.applyFee(parent.customerAccount);
					JOptionPane.showMessageDialog(parent.frame, "New balance = " + parent.customerAccount.getBalance(),
							Constants.SUCCESS, JOptionPane.INFORMATION_MESSAGE);

					parent.frame.dispose();
					parent.admin();
				}
			});

			returnButton.addActionListener(new ReturnButtonListener(parent));
		}
	}

	@Override
	String getMessageString() {
		return MessageConstants.CUSTOMER_ID_ADD_CHARGES_MESSAGE;
	}

}
