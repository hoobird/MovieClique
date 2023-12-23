package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public class Account {

	static ArrayList<Account> accountList = new ArrayList<>();
	boolean active; // Check whether suspended
	String username;
	String password;
	int type; // 0 = customer , 1 = staff , 2 = manager , 3=admin
	Profile userProfile;

	// For jackson
	public Account() {
		super();
	}

	public Account(String username, String password, int type)
			throws StreamWriteException, DatabindException, IOException {
		active = true;
		this.username = username;
		this.password = password;
		this.type = type;

		accountList.add(this); // Adding account to arrayList -> accountList
		// Try saving accountList every time it is added to
		writeAccountFile();
	}

	public static ArrayList<Account> getAccountList() {
		return accountList;
	}

	public static void setAccountList(ArrayList<Account> accountList) {
		Account.accountList = accountList;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) throws IOException {
		this.userProfile = userProfile;
	}

	public static Account getAccount(String uname) {
		for (Account acc : accountList) {
			if (acc.username.equals(uname)) {
				return acc;
			}
		}
		return null;
	}

	public static void readAccountFile() throws StreamReadException, DatabindException, IOException {
		File file = new File("accountList.json");
		System.out.println("accountFile found!");
		PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
				.allowIfSubType("java.util.ArrayList")
				.allowIfBaseType("Entity.Account")
				.allowIfBaseType("Entity.Profile")
				.allowIfSubType("Entity.Profile")
				.build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
		Account.setAccountList(objectMapper.readValue(file, new TypeReference<ArrayList<Account>>() {
		}));
		//System.out.println("accountFile has been read!");
	}

	public static void writeAccountFile() throws IOException {
		File file = new File("accountList.json");
		if (!file.exists()) {
			file.createNewFile();
		}
		PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
				.allowIfSubType("java.util.ArrayList")
				/*.allowIfBaseType("Entity.Account")*/
				.allowIfBaseType("Entity.Profile")
				.allowIfSubType("Entity.Profile")
				.build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, accountList);
		//System.out.println(file.getAbsolutePath());
	}

	@Override
	public String toString() {
		return "Account [active=" + active + ", username=" + username + ", password=" + password + ", type=" + type
				+ ", userProfile=" + userProfile + "]";
	}

}