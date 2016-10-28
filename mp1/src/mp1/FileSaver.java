package mp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class FileSaver {
	
	File save_file;
	JFileChooser file_chooser;
    ArrayList<String> compressed_contents;
    String newline = "\n";
    JTextArea text_area;
    int returnVal;
    long file_size;
	
	public FileSaver(File file, int val, ArrayList<String> contents, JTextArea area, long length)
	{
		save_file = file;
		returnVal = val;
		compressed_contents = contents;
		text_area = area;
		file_size = length;
	}

	public void save_file()
	{


            try{
            	BufferedWriter out = new BufferedWriter(new FileWriter(save_file));
            	
            	for (String temp : compressed_contents){
            		out.write(temp);
            		out.write(newline);
            	}
            	out.close();
            	
            }
            catch(Exception z){
            	System.err.format("Exception occurred trying to save '%s'.", save_file);
        	    z.printStackTrace();
            }
   
        } 
	
}
