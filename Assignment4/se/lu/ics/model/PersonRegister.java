package se.lu.ics.model;

import java.util.ArrayList;

public class PersonRegister {

	private ArrayList<Person> keeps;

	ArrayList<Person> register = new ArrayList<Person>();

	public void addPerson(Person person) {
		this.register.add(person);
	}

	public Person findPerson(String personId ) {
		for (Person p : register) {
			if (p.getPersonId ().equals(personId)) {
				return p;
			}
		}
		return null;
	}

	public BankAccount findAccount(String accountNumber) {
		for (Person p : register) {
			for (BankAccount konto : p.getAccount()) {
				if (konto.getAccountNumber().equals(accountNumber)) {
					return konto;
				}
			}
		}
		return null;
	}

	public double calculateTotalBalance() {
		double tmpBalance = 0;
		for (Person person : register) {
			tmpBalance += person.calculateTotalBalance();
		}
		return tmpBalance;

	}
	
	// Getters and Setters
	public ArrayList<Person> getKeeps() {
		return keeps;
	}

	public void setKeeps(ArrayList<Person> keeps) {
		this.keeps = keeps;
	}

	public ArrayList<Person> getRegister() {
		return register;
	}

	public void setRegister(ArrayList<Person> register) {
		this.register = register;
	}

}
