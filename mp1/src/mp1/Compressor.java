package mp1;

import java.util.ArrayList;

public class Compressor 
{
	public ArrayList<String> compressed_contents;
	public String longstring;
	public Huffman huff;
	public ArrayList<String> decompressed_contents = new ArrayList<String>();
	public ArrayList<String> decompressed_chars = new ArrayList<String>();
	public ArrayList<String> temp_contents = new ArrayList<String>();
    public ArrayList<String> codes = new ArrayList<String>(256);
	
	public Compressor(String lstring, ArrayList<String> compressed)
	{
		compressed_contents = compressed;
        longstring = lstring;
        huff = new Huffman(longstring);
 
	}
	
	public ArrayList<String> compress_contents()
	{
		huff.execute_huffman();
        this.compressed_contents.clear();
        for (int i = 0; i < huff.codes.length; i++)
        {
        	if (huff.counts[i] == 0)
        	{
        		this.compressed_contents.add("0");
        	}
        	else 
        	{
            	this.compressed_contents.add(huff.codes[i]);               		
        	}

        }
        this.compressed_contents.add("END DICTIONARY");
        for (int i = 0; i < longstring.length(); i++)
        {
        	this.compressed_contents.add(huff.get_code(longstring.charAt(i)));                	
        }
        
        return this.compressed_contents;
	}
	
	public ArrayList<String> decompress_contents()
	{
		/*
		 * This loop pulls just the first 256 items from compressed_contents
		 * and puts them in the arraylist codes.
		 * This is where the dictionary of codes is stored. 
		 * It will be used to translate the rest of the document.
		 */
		for (int i = 0; i < 256; i++)
		{
			codes.add(compressed_contents.get(i));
		}
		
		/*
		 * This loops moves the rest of the compressed_contents array 
		 * to a new array to make indexing easier 
		 */
	
		for (int i = 257; i < compressed_contents.size(); i ++)
		{
			temp_contents.add(compressed_contents.get(i));
		}
		
		/*
		 * This method parses temp_contents
		 * This looks at each one and searches for its location in codes array. 
		 * It uses the location to translate it back to a character.
		 * then it adds it to the decompressed_contents string_array
		 */
		
		for (int i = 0; i < temp_contents.size(); i++)
		{
			String temp_code = temp_contents.get(i);
			
			for (int j = 0; j < 256; j++)
			{
				String code = codes.get(j);
				if (code.equals(temp_code))
				{
					String character = Character.toString((char)j);
					decompressed_chars.add(character);
				}
			}
		}
		
		/*
		 * This method converts the array of String-characters and concatenates
		 * them into the array of strings needed, with each string equal to one line.
		 * 
		 */
		String temp_string = "";
		for (int i = 0; i < decompressed_chars.size(); i++)
		{
			String this_char = decompressed_chars.get(i);
			if(this_char.equals("\n"))
			{
				decompressed_contents.add(temp_string);
				temp_string = "";
			}
			else
			{
				temp_string = temp_string + this_char;
			}
			
		}
		return decompressed_contents;
	}
	

}
