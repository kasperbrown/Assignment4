package resources.fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import se.lu.ics.model.BankAccount;
import se.lu.ics.model.Person;
import se.lu.ics.model.PersonRegister;

public class HomePageController {
	// Instantiate classes
	PersonRegister pReg = new PersonRegister();

	// observable list
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	@FXML
	private TableView<Person> tableViewPerson;
	@FXML
	private TableColumn<Person, String> columnPersonId;
	@FXML
	private TableColumn<Person, String> columnName;
	@FXML
	private TableColumn<Person, Integer> columnAge;
	@FXML
	private Button btnManagePeople;
	@FXML
	private Button btnAddPerson;
	@FXML
	private AnchorPane managePersonPane;
	@FXML
	private TextField tfPersonId;
	@FXML
	private TextField tfPersonName;
	@FXML
	private TextField tfPersonAge;
	@FXML
	private Label personSuccess;
	@FXML
	private Label personFail;
	@FXML
	private Label personIdError;
	@FXML
	private Label personNameError;
	@FXML
	private Label personAgeError;

	public HomePageController() {
	}

	// Initialize Account and Person
	public void initialize() {
		managePersonPane.disableProperty().set(true);
		managePersonPane.opacityProperty().set(0);
		manageAccountPane.disableProperty().set(true);
		manageAccountPane.opacityProperty().set(0);
		anchorPaneShowTotal.disableProperty().set(true);
		anchorPaneShowTotal.opacityProperty().set(0);
		anchorPaneInformation.disableProperty().set(true);
		anchorPaneInformation.opacityProperty().set(0);
		anchorPaneMoney.disableProperty().set(true);
		anchorPaneMoney.opacityProperty().set(0);
	}

	public void btnManagePeopleClicked(ActionEvent event) {
		initialize();
		managePersonPane.disableProperty().set(false);
		managePersonPane.opacityProperty().set(1);
		columnPersonId.setCellValueFactory(new PropertyValueFactory<Person, String>("personId"));
		columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		columnAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
	}

	// Add person with the add button
	public void btnAddPersonClicked(ActionEvent event) {
		personIdError.setText(" ");
		personNameError.setText(" ");
		personAgeError.setText(" ");
		if (tfPersonId.getText().isEmpty() == true || tfPersonName.getText().isEmpty() == true
				|| tfPersonAge.getText().isEmpty() == true) {
			personSuccess.setText(" ");
			personFail.setText("Please fill out all the information fields.");
			if (tfPersonId.getText().isEmpty() == true) {
				personIdError.setText("*");
			}
			if (tfPersonName.getText().isEmpty() == true) {
				personNameError.setText("*");
			}
			if (tfPersonAge.getText().isEmpty() == true) {
				personAgeError.setText("*");
			}
		} else {
			if (pReg.findPerson(tfPersonId.getText()) == null) {
					pReg.findPerson(tfPersonId.getText()); 
					String[] numbers = tfPersonName.getText().split("\\D+");
					int sum = 0;
					for (String number : numbers) {
						try {
							sum += Integer.parseInt(number);
						} catch (Exception exception) {
						}
					}
					if (sum > 0) {
						personSuccess.setText(" ");
						personFail.setText("Only enter letters.");
						personNameError.setText("*");
					} else {
						try {
							Person person = new Person();
							personFail.setText(" ");
							personSuccess.setText("Person Added");
							person.setPersonId(tfPersonId.getText());
							person.setName(tfPersonName.getText());
							person.setAge(Integer.parseInt(tfPersonAge.getText()));
							pReg.addPerson(person);
							personData.add(person);
							tableViewPerson.setItems(personData);
							System.out.println(person.getPersonId() + " " + person.getName() + person.getAge());
	
						} catch (Exception exception) {
							personSuccess.setText(" ");
							personFail.setText("Only enter digits.");
							personAgeError.setText("*");
						}
					}
					
				
			}else {
				personSuccess.setText(" ");
				personFail.setText("Please enter new ID");
			}
		}
	}


	public void btnRemovePersonClicked(ActionEvent event) {
		String personID = tfPersonId.getText();
		for (Person person : pReg.getRegister()) {
			if (personID.equals(pReg.findPerson(personID).getPersonId())) {
				pReg.findPerson(personID);
				Person tempPerson = pReg.findPerson(personID);
				int index = pReg.getRegister().indexOf(tempPerson);
				pReg.getRegister().remove(index);
				personData.remove(index);
				tableViewPerson.setItems(personData);
				personFail.setText(" ");
				personSuccess.setText("Person removed successfully!");
			} else {
				personSuccess.setText(" ");
				personFail.setText("Person with ID: " + personID + " is not in the system.");
			}
		}

	}

	public void btnFindPersonClicked(ActionEvent event) {
		String personID = tfPersonId.getText();
		for (Person person : pReg.getRegister()) {
			if (personID.equals(pReg.findPerson(personID).getPersonId())) {
				personFail.setText(" ");
				personSuccess.setText("Person with ID: " + personID + " Is in the system!");
			} else {
				personSuccess.setText(" ");
				personFail.setText("Person with ID: " + personID + " is not in the system.");
			}
		}
		pReg.findPerson(personID);
		System.out.println(pReg.findPerson(personID).getName());
	}

	
	//-------------PRINT INFORMATION--------------
	private ObservableList<Person> printAllPersons = FXCollections.observableArrayList();
	private ObservableList<BankAccount> printAllAccounts = FXCollections.observableArrayList();
	
	@FXML
	private AnchorPane anchorPaneInformation;
	@FXML
	private TableView<Person> tableViewPrintInformation;
	@FXML
	private TableColumn<Person, String> columnPersonIdPrint;
	@FXML
	private TableColumn<Person, String> columnPersonNamePrint;
	@FXML
	private TableColumn<Person, Integer> columnPersonAgePrint;
	@FXML
	private TableView<BankAccount> tableViewPrintAccountInfo;
	@FXML
	private TableColumn<BankAccount, String> columnPersonAccountIdPrint;
	@FXML
	private TableColumn<BankAccount, Double> columnPersonAccountBalancePrint;

	public void menuPrintInformationClicked(ActionEvent event) {
		initialize(); 
		anchorPaneInformation.disableProperty().set(false);
		anchorPaneInformation.opacityProperty().set(1);
		columnPersonIdPrint.setCellValueFactory(new PropertyValueFactory<Person, String>("personId"));
		columnPersonNamePrint.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		columnPersonAgePrint.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		columnPersonAccountIdPrint.setCellValueFactory(new PropertyValueFactory<BankAccount, String>("accountNumber"));
		columnPersonAccountBalancePrint.setCellValueFactory(new PropertyValueFactory<BankAccount, Double>("balance"));

	}

	public void btnPrintAllInformationClicked(ActionEvent event) {
		printAllPersons.addAll(personData);
		printAllAccounts.addAll(account);
		tableViewPrintInformation.setItems(printAllPersons);
		tableViewPrintAccountInfo.setItems(account);
	}


	//-------------MANAGE ACCOUNTS--------------

	// Observable List BankAccount
	private ObservableList<BankAccount> account = FXCollections.observableArrayList();

	@FXML
	private TableView<BankAccount> tableViewAccount;
	@FXML
	private TableColumn<BankAccount, String> columnAccountNumber;
	@FXML
	private TableColumn<BankAccount, Double> columnBalance;
	@FXML
	private TableColumn<BankAccount, String> columnOwner;
	
	// Values Manage Accounts
	@FXML
	private AnchorPane manageAccountPane;
	@FXML
	private TextField tfPersonIdAccount;
	@FXML
	private TextField tfAccountNumber;
	@FXML
	private Label personIdAccountError;
	@FXML
	private Label personAccountNumberError;
	@FXML
	private Label accountSuccess;
	@FXML
	private Label accountFail;

	// MANAGE ACCOUNT CLICKED (MENU)
	public void btnManageAccountClicked(ActionEvent event) {
		initialize();
		manageAccountPane.disableProperty().set(false);
		manageAccountPane.opacityProperty().set(1);
		columnAccountNumber.setCellValueFactory(new PropertyValueFactory<BankAccount, String>("accountNumber"));
		columnBalance.setCellValueFactory(new PropertyValueFactory<BankAccount, Double>("balance"));
		columnOwner.setCellValueFactory(new PropertyValueFactory<BankAccount, String>("ownerName"));

	}
	
	// ADD ACCOUNT
		public void btnAddAccountClicked() {
			//either are empty
			if(tfPersonIdAccount.getText().equals("") || tfAccountNumber.getText()=="") {
				accountSuccess.setText("");
				accountFail.setText("Please fill out all the information fields");
				//both empty
				if (tfPersonIdAccount.getText().equals("") && tfAccountNumber.getText().equals("") ){
					personAccountNumberError.setText("*");
					personIdAccountError.setText("*");
				}
				//person ID empty
				else if(tfPersonIdAccount.getText().equals("")) {
					personAccountNumberError.setText("");
					personIdAccountError.setText("*");
				}
				//account number empty
				else{
					personAccountNumberError.setText("*");
					personIdAccountError.setText("");
				}
			}
			//both filled out
			else {
				accountSuccess.setText("");
				accountFail.setText("");
				personAccountNumberError.setText("");
				personIdAccountError.setText("");
				Person tempPerson;
				BankAccount tempAccount = new BankAccount();
				String accountID = tfAccountNumber.getText();

				String personID = tfPersonIdAccount.getText();
				tempPerson = pReg.findPerson(personID);

				tempAccount.setAccountNumber(accountID);
				tempAccount.setBalance(0);
				tempAccount.setOwner(tempPerson);
				//check if account number is occupied
				if (pReg.findAccount(tfAccountNumber.getText()) == null) {
					//check if person id exists
					if (pReg.findPerson(tfPersonIdAccount.getText()) != null) {
						//check if person is over 18
						if(pReg.findPerson(tfPersonIdAccount.getText()).getAge()>=18) {
							if (tempPerson.getAccount().size() >= 3) {
								accountSuccess.setText(" ");
								accountFail.setText("A person can only have a maximum of 3 accounts at a time");
							} else {
								account.add(tempAccount);
								tempPerson.getAccount().add(tempAccount);
								tableViewAccount.setItems(account);
								accountFail.setText(" ");
								accountSuccess.setText("Account was added successfully");
								System.out.println(tempPerson.getName() + " " + tempPerson.getAccount() + " "
										+ tempPerson.findAccount(accountID).getBalance());
							}
							System.out.println(tempPerson.getAccount().size());
						}else {
							accountFail.setText("You're too young to have an account.");
						}
					}
					else {
						accountSuccess.setText(" ");
						accountFail.setText("Incorrect ID, please try again");
						personIdAccountError.setText("*");
					}
				} else {
					accountSuccess.setText(" ");
					accountFail.setText("Please enter new account number");
					personAccountNumberError.setText("*");
				}
			}
		}
	
	// FIND ACCOUNT
	public void btnFindAccountClicked(ActionEvent event) {
		personAccountNumberError.setText("");
		personIdAccountError.setText("");
		accountSuccess.setText("");
		accountFail.setText("");
		//account number empty
		if(tfAccountNumber.getText().equals("")) {
			personAccountNumberError.setText("*");
			accountSuccess.setText("");
			accountFail.setText("Please fill out all the information fields");
			}
		else {
			try {
				personAccountNumberError.setText("");
				personIdAccountError.setText("");
				String accID = tfAccountNumber.getText();
				for (Person person : pReg.getRegister()) {
					for (BankAccount account : person.getAccount()) {
						if (accID.equals(pReg.findAccount(accID).getAccountNumber())) {
							accountFail.setText(" ");
							accountSuccess
									.setText("Account number: " + pReg.findAccount(accID).getAccountNumber() + " was found");
						} else {
							accountSuccess.setText(" ");
							accountFail
									.setText("Account number: " + pReg.findAccount(accID).getAccountNumber() + "was not found");
		
						}
					}
				}
			pReg.findAccount(accID);
			System.out.println(pReg.findAccount(accID).getOwner());
			}
			catch(NullPointerException exception){
				personAccountNumberError.setText("*");
				accountSuccess.setText("");
				accountFail.setText("Incorrect account number");
			}
		}
	}


	//--------------------------------MANAGE MONEY-----------------------------
	
	@FXML 
	private AnchorPane anchorPaneMoney;
	@FXML
	private TextField tfAccountNumberMoney;
	@FXML
	private TextField tfAmount;
	@FXML
	private Label moneySuccess;
	@FXML
	private Label moneyFail;
	@FXML
	private Label accountNumberMoneyError;
	@FXML
	private Label amountError;
	
	
	public void manageMoneyClicked(ActionEvent event) {
		initialize();
		anchorPaneMoney.disableProperty().set(false);
		anchorPaneMoney.opacityProperty().set(1);
	}
	
	// WITHDRAW
	public void btnWithdrawClicked() {
		try {
			//either empty
			if(tfAccountNumberMoney.getText() == "" || tfAmount.getText() == "") {
				moneyFail.setText("Please enter all information fields");
				//account number empty
				if(tfAccountNumberMoney.getText() != "") {
					accountNumberMoneyError.setText("*");
					amountError.setText("");
				}
				//amount empty
				else if (tfAmount.getText() != "") {
					accountNumberMoneyError.setText("");
					amountError.setText("*");
				}
				//both empty
				else {
					accountNumberMoneyError.setText("*");
					amountError.setText("*");
				}
			}
			//both filled in
			else {
				amountError.setText("");
				accountNumberMoneyError.setText("");
				moneyFail.setText("");
				moneySuccess.setText("");
			try {
				String accountNumber = tfAccountNumber.getText();
	
				double tempAmount = Double.parseDouble(tfAmount.getText());
				pReg.findAccount(accountNumber).withdraw(tempAmount);
	
				System.out.println(pReg.findAccount(accountNumber).getAccountNumber() + " "+ pReg.findAccount(accountNumber).getBalance());
	
				accountSuccess.setText("Withdraw Successful");
				tableViewAccount.setItems(account);
			}
	
			catch (NullPointerException exception) {
				accountFail.setText("Unable to make withdraw");
			}
		}
		}catch(Exception exception) {
			System.out.println("Nope");
		}
	}

	public void btnDepositClicked() {
		//either empty
		if(tfAccountNumberMoney.getText() == "" || tfAmount.getText() == "") {
			moneyFail.setText("Please enter all information fields");
			//account number empty
			if(tfAccountNumberMoney.getText() != "") {
				accountNumberMoneyError.setText("*");
				amountError.setText("");
			}
			//amount empty
			else if (tfAmount.getText() != "") {
				accountNumberMoneyError.setText("");
				amountError.setText("*");
			}
			//both empty
			else {
				accountNumberMoneyError.setText("*");
				amountError.setText("*");
			}
		}
		//both filled in
		else {
			accountNumberMoneyError.setText("");
			amountError.setText("");
			moneyFail.setText("");
			moneySuccess.setText("");
			
			String accountNumber = tfAccountNumber.getText();
			double tempAmount = Double.parseDouble(tfAmount.getText());
			pReg.findAccount(accountNumber).deposit(tempAmount);
			tableViewAccount.setItems(account);
	
			System.out.println(pReg.findAccount(accountNumber).getAccountNumber() + " " + pReg.findAccount(accountNumber).getBalance());
		}
	}
	
	
	
	//-----------------------------SHOW TOTALS-----------------------------
	
	@FXML
	private AnchorPane anchorPaneShowTotal;
	@FXML
	private RadioButton rbtnPersonAccounts;
	@FXML
	private RadioButton rbtnAllAccounts;
	@FXML
	private Button btnShowTotalBalance;
	@FXML 
	private TextField tfPersonIdTotals;
	@FXML
	private Label totalsSuccess;
	@FXML
	private Label totalsFail;
	@FXML
	private Label amountSuccess;
	@FXML
	private Label amountFail;
	@FXML
	
	// Observable List BankAccount
	private ObservableList<BankAccount> totals = FXCollections.observableArrayList();

	@FXML
	private TableView<BankAccount> tableViewTotals;
	@FXML
	private TableColumn<BankAccount, String> columnAccountNumberTotals;
	@FXML
	private TableColumn<BankAccount, Double> columnBalanceTotals;
	
	public void btnMenuShowTotalClicked(ActionEvent event) {
		initialize();
		anchorPaneShowTotal.opacityProperty().set(1);
		anchorPaneShowTotal.disableProperty().set(false);
		btnShowTotalBalance.disableProperty().set(true);
		tfPersonIdTotals.disableProperty().set(true);
		columnAccountNumberTotals.setCellValueFactory(new PropertyValueFactory<BankAccount, String>("accountNumber"));
		columnBalanceTotals.setCellValueFactory(new PropertyValueFactory<BankAccount, Double>("balance"));
		
	}
	
	public void rbtnPersonAccountsClicked(ActionEvent event) {
		if(rbtnPersonAccounts.isSelected() == true || rbtnAllAccounts.isSelected() == true) {
			btnShowTotalBalance.disableProperty().set(false);
		}
		else {
			btnShowTotalBalance.disableProperty().set(true);

		}
		rbtnAllAccounts.setSelected(false);
		tfPersonIdTotals.disableProperty().set(false);
	}
	
	public void rbtnAllAccountsClicked(ActionEvent event) {
		if(rbtnPersonAccounts.isSelected() == true || rbtnAllAccounts.isSelected() == true) {
			btnShowTotalBalance.disableProperty().set(false);
		}
		else {
			btnShowTotalBalance.disableProperty().set(true);
		}
		rbtnPersonAccounts.setSelected(false);
		tfPersonIdTotals.disableProperty().set(true);
	}
	
	public void btnShowTotalClicked(ActionEvent event) {
		if(rbtnAllAccounts.isSelected() == true) {
			tableViewTotals.setItems(totals);
			tableViewTotals.setItems(account);
		}
		else {
			System.out.println("Person");
		}
}
	
}
