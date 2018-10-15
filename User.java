package Selenium;

public class User extends PasswordManager {

	private String id;
	private String password;
	private String key;

	public User(String id, String password, String key) {
		super();
		this.id = id;
		this.password = encrypt(password, key);
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		System.out.println(this.key);
		return decrypt(this.password, this.key);
	}

	public String getKey() {
		return key;
	}
}
