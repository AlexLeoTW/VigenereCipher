package click.alexleo.vigenerecipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VigenereCipher {
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		VigenereOptions options = null;
		
		options = menu.textMenu(args);
		
		try {
			process(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO inplement verbose option
		System.out.println("Complete!");
		if (options.direction == VigenereOptions.ENCRYPT) {
			System.out.printf("checkout [%s] for encrypted file", options.cipherTextLocation);
		} else {
			System.out.printf("checkout [%s] for decrypted file", options.plainTextLocation);
		}
		
		
	}
	
	public static void process(VigenereOptions options) throws IOException {
		
		VigenereCipher cipher =  new VigenereCipher();
		Path plainText = Paths.get(options.plainTextLocation);
		Path cipherText = Paths.get(options.cipherTextLocation);
		BufferedReader in = null;
		BufferedWriter out = null;
		if (options.direction == VigenereOptions.ENCRYPT) {
			// in = Files.newBufferedReader(plainText, StandardCharsets.UTF_8);
			in = Files.newBufferedReader(plainText);
			out = Files.newBufferedWriter(cipherText);
		} else {
			in = Files.newBufferedReader(cipherText);
			out = Files.newBufferedWriter(plainText);
		}
		
		for (String fileLine = in.readLine(); fileLine != null; fileLine = in.readLine()) {
			
			fileLine = fileLine.toUpperCase().replaceAll("\\s+","");
			
			if (options.direction == VigenereOptions.ENCRYPT) {
				fileLine = cipher.encrypt(fileLine, options);
			} else /* (options.direction == VigenereOptions.DECRYPT) */ {
				fileLine = cipher.decrypt(fileLine, options);
			}
			
			fileLine += "\n";
			
			out.write(fileLine, 0, fileLine.length()); 
		}
		
		in.close();
		out.flush();
		out.close();
	}
	
	private String encrypt(String input, VigenereOptions options) {
		
		char[] buffer = new char[input.length()];
		int plainPos;
		int passPos;
		
		for (int i = 0; i< input.length(); i++) {
			plainPos = (int)input.charAt(i) - (int)'A';
			passPos = (int)options.password.charAt( i%options.password.length() ) - (int)'0';
			buffer[i] = (char) ( (plainPos + passPos) % 26 + (int)'A' ) ;
//			System.out.printf(" %c + %c = %c \n", input.charAt(i), options.password.charAt( i%options.password.length() ), buffer[i]);
		}
		
		return new String(buffer);
	}
	
	private String decrypt(String input, VigenereOptions options) {

		char[] buffer = new char[input.length()];
		int cipherPos;
		int passPos;
		
		for (int i = 0; i< input.length(); i++) {
			cipherPos = (int)input.charAt(i) - (int)'A';
			passPos = (int)options.password.charAt( i%options.password.length() ) - (int)'0';
			buffer[i] = (char) ( (cipherPos - passPos + 26) % 26 + (int)'A' ) ;
//			System.out.printf(" %c - %c = %c \n", input.charAt(i), options.password.charAt( i%options.password.length() ), buffer[i]);
		}
		
		return new String(buffer);
	}
}