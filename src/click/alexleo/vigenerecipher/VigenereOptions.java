package click.alexleo.vigenerecipher;

import org.apache.commons.cli.CommandLine;

public class VigenereOptions {
	
	public static final String DEFAULT_PLAIN_TEXT = "plaintext.txt";
	public static final String DEFAULT_CIPHER_TEXT = "ciphertext.txt";
	public static final String DEFAULT_PASSWORD = "0000";
	public static final boolean DECRYPT = true;
	public static final boolean ENCRYPT = false;
	public static final boolean HELP = false;
	
	public final String plainTextLocation;
	public final String password;
	public final String cipherTextLocation;
	public final boolean direction;
	public final boolean verbose;
	public final boolean help;
	
	public VigenereOptions(String plainTextLocation, String password, String cipherTextLocation, boolean direction) {
		super();
		this.plainTextLocation = plainTextLocation;
		this.password = password;
		this.cipherTextLocation = cipherTextLocation;
		this.direction = direction;
		this.verbose = false;
		this.help = false;
	}
	
	public VigenereOptions(String plainTextLocation, String password, String cipherTextLocation, boolean direction, boolean verbose, boolean help) {
		super();
		this.plainTextLocation = plainTextLocation;
		this.password = password;
		this.cipherTextLocation = cipherTextLocation;
		this.direction = direction;
		this.verbose = verbose;
		this.help = help;
	}
	
	public VigenereOptions(CommandLine cmd) throws Exception {
		if (cmd.hasOption("d")) {
			this.direction = VigenereOptions.DECRYPT;
			this.plainTextLocation = cmd.getOptionValue("t", VigenereOptions.DEFAULT_PLAIN_TEXT);
			this.cipherTextLocation = cmd.getOptionValue("f", VigenereOptions.DEFAULT_CIPHER_TEXT);
			
		} else /* Default to Encrypt */ {
			this.direction = VigenereOptions.ENCRYPT;
			this.plainTextLocation = cmd.getOptionValue("f", VigenereOptions.DEFAULT_PLAIN_TEXT);
			this.cipherTextLocation = cmd.getOptionValue("t", VigenereOptions.DEFAULT_CIPHER_TEXT);
		}
		
		this.password = cmd.getOptionValue("p");
		this.verbose = cmd.hasOption("v");
		this.help = cmd.hasOption("h");
	}
}
