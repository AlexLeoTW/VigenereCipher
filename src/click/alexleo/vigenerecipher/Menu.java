package click.alexleo.vigenerecipher;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Menu {
	
	private String plainTextLocation = VigenereOptions.DEFAULT_PLAIN_TEXT;
	private String cipherTextLocation = VigenereOptions.DEFAULT_CIPHER_TEXT;
	private String password = VigenereOptions.DEFAULT_PASSWORD;
	private Scanner keyboard = new Scanner(System.in);
	
	// TODO: use Enum!!! 
	private Options buildCliOptions() {
		Options cliOptions = new Options();
		
		cliOptions.addOption("e", "encrypt", false, "encrypt a file (default)");
		cliOptions.addOption("d", "decrypt", false, "decrypt a file");
		cliOptions.addOption("f", "from", true, "sepcify location of input file");
		cliOptions.addOption("t", "to", true, "sepcify location of output file");
		cliOptions.addRequiredOption("p", "password", true, "password to be used");
		cliOptions.addOption("v", "verbose", false, "show more message");
		
		return cliOptions;
	}
	
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
	
	public VigenereOptions textMenu(String[] cliArgs) {
		
		Options cliOptions = buildCliOptions(); 
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		
		try {
			cmd = parser.parse( cliOptions, cliArgs);
		} catch (ParseException e) {
			System.out.println("Oops! Looks like there are some issues with your command line options, "
					+ "please check the help with -h or --help option, "
					+ "or continue by using internative menu below");
		}
		
		VigenereOptions vigenereOptions;
		try {
			vigenereOptions = new VigenereOptions(cmd);
		} catch (Exception e) {
			return textMenu();
		}
		
		return vigenereOptions;
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
