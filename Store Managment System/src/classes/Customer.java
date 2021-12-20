package classes;

import java.io.Serializable;

import observer.Observable;
import observer.Observer;

public class Customer implements Serializable,Observer {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phoneNumber;
	private boolean subscribe;

	public Customer(String name, String phoneNumber, boolean subscribe) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		setSubscribe(subscribe);
	}

	public Customer(Customer other) {
		this.name = other.name;
		this.phoneNumber = other.phoneNumber;
		setSubscribe(other.subscribe);
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(
				" Customer:\nname: " + name + " Phone Number: 05" + phoneNumber + " Subscribe: " + subscribe);
		return sb.toString();
	}

	@Override
	public void updateSending(Observable o, String commercial) {
		
	}
	@Override
	public void updateRecieveing(Observable o) {
		
	}
	
}
