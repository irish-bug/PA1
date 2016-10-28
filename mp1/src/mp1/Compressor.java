package mp1;

import java.util.ArrayList;

public class Compressor 
{
	public ArrayList<String> compressed_contents;
	public String longstring;
	public Huffman huff; 
	
	public Compressor(String lstring, ArrayList<String> compressed)
	{
		compressed_contents = compressed;
        longstring = lstring;
        huff = new Huffman(longstring);

        System.out.println(compressed_contents);
        System.out.println(longstring);
        
	}
	
	public ArrayList<String> compress_contents()
	{
		huff.execute_huffman();
        this.compressed_contents.clear();
        for (int i = 0; i < huff.codes.length; i++)
        {
        	if (huff.counts[i] == 0)
        	{
        		this.compressed_contents.add("NULL");
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
	

}
