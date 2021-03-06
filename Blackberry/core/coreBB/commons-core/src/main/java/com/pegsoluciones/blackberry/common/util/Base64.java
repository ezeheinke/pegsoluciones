package com.pegsoluciones.blackberry.common.util;

/**
 * 
 * 
 * 
 * 
 * Base64
 **/
public class Base64 {
	
	private static final byte[] BASE64CHARS  = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '='};
	
	
	/**  Constructor for the Base64 object */
	public Base64() { }


	/**
	 *  Description of the Method
	 *
	 * @param  input  String
	 * @return        String base64
	 */
	public String encode(String input) {
		String output  = new String();
	
		int pos        = 0;
		while (pos + 3 <= input.length()) {
			byte[] outbyte  = new byte[4];
			int buffer      = (input.charAt(pos++) & 0xff);
			buffer = (buffer << 8) | (input.charAt(pos++) & 0xff);
			buffer = (buffer << 8) | (input.charAt(pos++) & 0xff);
			outbyte[3] = BASE64CHARS[buffer & 0x3F];
			buffer >>= 6;
			outbyte[2] = BASE64CHARS[buffer & 0x3F];
			buffer >>= 6;
			outbyte[1] = BASE64CHARS[buffer & 0x3F];
			buffer >>= 6;
			outbyte[0] = BASE64CHARS[buffer & 0x3F];
	
			output += new String(outbyte);
		}
	
		int remaining  = input.length() - pos;
		if (remaining > 0) {
			byte outbyte[]  = new byte[4];
			outbyte[3] = '=';
	
			int buffer    = (input.charAt(pos) & 0xff);
			if (remaining == 1) {
				outbyte[2] = '=';
				buffer <<= 4;
				outbyte[1] = BASE64CHARS[buffer & 0x3F];
				buffer >>= 6;
				outbyte[0] = BASE64CHARS[buffer & 0x3F];
			} else {
				buffer = (buffer << 8) | (input.charAt(pos + 1) & 0xff);
				buffer <<= 2;
				outbyte[2] = BASE64CHARS[buffer & 0x3F];
				buffer >>= 6;
				outbyte[1] = BASE64CHARS[buffer & 0x3F];
				buffer >>= 6;
				outbyte[0] = BASE64CHARS[buffer & 0x3F];
			}
			output += new String(outbyte);
		}
		return (output);
	}
}