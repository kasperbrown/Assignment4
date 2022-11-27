package se.lu.ics.model;

import java.util.ArrayList;

public class Person {
	private String personId;
	private String name;
	private int age;

	ArrayList<BankAccount> konton = new ArrayList<BankAccount>();

	public Person() {
		this.konton = new ArrayList<BankAccount>();
	}

	public Person(String personId, String name, int age) {
		this.personId = personId;
		this.name = name;
		this.age = age;
		this.konton = new ArrayList<BankAccount>();

	}

	// Methods
	public void addAccount(BankAccount konton) {

		if (this.konton.size() >= 3) {
			System.out.println("You have to many accounts");
		}
			if (this.getAge() < 18) {
				System.out.println("\nYou are under the age of 18 and therefore unable to add an account\n");
			}

			else {
				this.konton.add(konton);

			}
		}
	

	public BankAccount findAccount(String accountNumber) {
		for (BankAccount account : konton) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	public double calculateTotalBalance() {
		double totalBalance = 0;
		for (BankAccount account : konton) {
			totalBalance = totalBalance + account.getBalance();
		}
		return totalBalance;
	}

	public int countAccounts() {
		return konton.size();
	}

	// Getters and Setters
	public BankAccount getSpecifiedAccount(int index) {
		return this.konton.get(index);

	}

	public ArrayList<BankAccount> getAccount() {

		return konton;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
