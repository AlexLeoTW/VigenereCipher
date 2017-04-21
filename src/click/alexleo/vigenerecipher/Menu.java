package click.alexleo.vigenerecipher;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class Menu {
	
	private String plainTextLocation = VigenereOptions.DEFAULT_PLAIN_TEXT;
	private String cipherTextLocation = VigenereOptions.DEFAULT_CIPHER_TEXT;
	private String password = VigenereOptions.DEFAULT_PASSWORD;
	private Scanner keyboard = new Scanner(System.in);
	private Options cliOptions = null;
	
	// TODO: use Enum!!! 
	// interactive menu only
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
	
	// entry point with CLI options support
	public VigenereOptions textMenu(String[] cliArgs) {
		
		cliOptions = buildCliOptions(); 
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		VigenereOptions vigenereOptions;
		
		try {
			cmd = parser.parse( cliOptions, cliArgs);
			vigenereOptions = new VigenereOptions(cmd);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("please check the help with -h or --help option, "
					+ "or continue by using internative menu below");
			return textMenu();
		}
		
		if ( (cmd.hasOption("e") || cmd.hasOption("d")) && !(cmd.hasOption("p")) ) {
			System.out.println("Missing required option: p");
			System.out.println("please check the help with -h or --help option, "
					+ "or continue by using internative menu below");
			return textMenu();
		}
		
		return vigenereOptions;
	}
	
	private Options buildCliOptions() {
		
		Option encrypt = Option.builder("e")
				.longOpt("encrypt")
				.desc("encrypt a file")
				.build();
		
		Option decrypt = Option.builder("d")
				.longOpt("decrypt")
				.desc("decrypt a file")
				.build();
		
		Option from = Option.builder("f")
				.longOpt("from")
				.hasArg(true)
				.numberOfArgs(1)
				.desc("sepcify location of input file")
				.build();
		
		Option to = Option.builder("t")
				.longOpt("to")
				.hasArg(true)
				.numberOfArgs(1)
				.desc("sepcify location of output file")
				.build();
		
		Option password = Option.builder("p")
				.longOpt("password")
				.hasArg(true)
				.numberOfArgs(1)
				.desc("password to be used")
				.build();
		
		Option verbose = Option.builder("v")
				.longOpt("verbose")
				.desc("show more message")
				.build();
		
		Option help = Option.builder("h")
				.longOpt("help")
				.desc("print this message")
				.build();
		
		OptionGroup operation = new OptionGroup();
		operation.addOption(encrypt);
		operation.addOption(decrypt);
		operation.addOption(help);
		operation.setRequired(true);
		
		Options cliOptions = new Options();
		cliOptions.addOption(from);
		cliOptions.addOption(to);
		cliOptions.addOption(password);
		cliOptions.addOption(verbose);
		cliOptions.addOptionGroup(operation);
		
		return cliOptions;
	}
	
	public void printCliHelp() {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "vegenere", buildCliOptions() );
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
