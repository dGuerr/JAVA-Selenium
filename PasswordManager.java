package Selenium;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public abstract class PasswordManager {

	/**
	 * Encrypt a password
	 * 
	 * @param password
	 * @param key
	 * @return
	 */
	public static String encrypt(String password, String key) {
		try {
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Decrypt a password
	 * 
	 * @param password
	 * @param key
	 * @return
	 */
	public static String decrypt(String password, String key) {
		try {
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
