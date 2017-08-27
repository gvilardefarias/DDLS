package Controle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Metods {
	protected static int menu(int resp) {
		switch (resp) {
		case 1: return 1;
		case 2: return 1;
		default: System.out.println("Nenhuma opção escolhida!");
				 return 0;
		}
	}
	
	protected static String encrypt(String input) {
		final StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());

			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}