package mp1;

import java.util.ArrayList;

public class Encryptor {
	
	ArrayList<String> coded_contents = new ArrayList<String>();
	ArrayList<String> recoded_contents = new ArrayList<String>();
	
	public Encryptor ( ArrayList<String> contents)
	{
		coded_contents = contents;
	}
	
	/*
	 * This method takes the ArrayList of compressed strings and flips the zeros and ones.
	 * It will work forward or reverse to encrypt or decrypt.
	 */
	public ArrayList<String> do_cipher()
	{
		for (int i = 0; i < coded_contents.size(); i++)
		{
			String str = coded_contents.get(i);
			System.out.println(str);
			String swapped_str = str.replace("0", "*");
			swapped_str = swapped_str.replace("1", "0");
			swapped_str = swapped_str.replace("*", "1");
			System.out.println(swapped_str);
			System.out.println("____________");
			recoded_contents.add(swapped_str);
		}
		
		return recoded_contents;
	}

} 
