package click.alexleo.vigenerecipher;

import java.util.Scanner;

public class Menu {
	
	public static final String PLAIN_TEXT = "plaintext.txt";
	public static final String PASSWORD = "0000";
	public static final String CIPHER_TEXT = "ciphertext.txt";
	
	private String plainTextLocation = PLAIN_TEXT;
	private String password = PASSWORD;
	private String cipherTextLocation = CIPHER_TEXT;
	private Scanner keyboard = new Scanner(System.in);
	
	
	public VigenereOptions textMenu() {
		
		int userSelect = 0;
		
		while ( userSelect != 4 && userSelect != 5 ) {
			printMenu();
			userSelect = userSelect();
			
			switch (userSelect) {
			case 1:
				System.out.print("New name for plaintext file: ");
				plainTextLocation = keyboard.nextLine();
				break;
			case 2:
				System.out.print("New password: ");
				password = keyboard.nextLine();
				break;
			case 3:
				System.out.print("New name for ciphertext file: ");
				cipherTextLocation = keyboard.nextLine();
				break;
			}
		}
		
		if ( userSelect == 4 ) {
			return new VigenereOptions(plainTextLocation, password, cipherTextLocation, VigenereOptions.ENCRYPT);
		} else /* if ( userSelect == 5 ) */ {
			return new VigenereOptions(plainTextLocation, password, cipherTextLocation, VigenereOptions.DECRYPT);
		}
		
	}
	
	
	public void printMenu() {
		
		System.out.print(
			"Wellcome to use VigenereCipher! \n" +
			"======================================== \n" +
			"	1. set palintext file name (current: " + plainTextLocation + ")\n" + 
			"	2. set password (current: " + password + ")\n" + 
			"	3. set ciphertext file name (current: " + cipherTextLocation + ")\n" +
			"	4. encrypt\n" +
			"	5. decrypt\n" + 
			"======================================== \n" +
			" > "
		);
	}
	
	private int userSelect() {
		
		String temp = keyboard.nextLine();
		
		try {
			return Integer.parseInt(temp);
			
		} catch (NumberFormatException e) {
			
			System.out.println("Sorry, try again");
			System.out.print(" > ");
			return userSelect();
					
		}
	} 
}
