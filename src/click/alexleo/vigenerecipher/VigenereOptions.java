package click.alexleo.vigenerecipher;

public class VigenereOptions {
	
	public static boolean DECRYPT = true;
	public static boolean ENCRYPT = false;
	
	public final String plainTextLocation;
	public final String password;
	public final String cipherTextLocation;
	public final boolean direction;
	
	public VigenereOptions(String plainTextLocation, String password, String cipherTextLocation, boolean direction) {
		super();
		this.plainTextLocation = plainTextLocation;
		this.password = password;
		this.cipherTextLocation = cipherTextLocation;
		this.direction = direction;
	} 
}
