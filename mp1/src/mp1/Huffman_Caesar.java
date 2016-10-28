/*
 * Huffman Caesar is a file encryptor/decryptor and compression/decompression utility
 * written for JHU EN.605.421 Foundations of Algorithms
 * Fall 2016 by Shane Rogers
 * Code for Huffman Caesar was adapted from 
 * Java Tutorials Code Sample – FileChooserDemo.java
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
 * 
 * The following classes were adapted from www.cs.armstrong:
 * Heap.java http://www.cs.armstrong.edu/liang/intro9e/html/Heap.html
 * Huffman.java http://www.cs.armstrong.edu/liang/intro9e/html/HuffmanCode.html
 * 
 * 
 * How can you tell the difference between a good cryptography joke and a random string of words?
 * You can't. They're indistinguishable.
 * 
 */

package mp1;
 
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 
/*
 * Huffman Caesar.java uses these files:
 *   ../images/open_file.gif
 *   ../images/save_file.gif
 *   ../images/lock_file.png
 *   ../images/unlock_file.png
 */
public class Huffman_Caesar extends JPanel
                             implements ActionListener {
	
	//This was added to get rid of a warning.
	private static final long serialVersionUID = 1L; 
	
	static private final String newline = "\n";
    JButton open_button, save_button, encrypt_button, decrypt_button;
    JTextArea text_area;
    JFileChooser file_chooser;
    
    /*
     * Create open file and buffered reader variables for opening and reading file.
     * Also Array list of strings to hold file contents
     */
    
    File open_file = null;
    BufferedReader reader = null;
    ArrayList<String> file_contents = new ArrayList<String>();
    String longstring = "";
    ArrayList<String> compressed_contents;
    ArrayList<String> decompressed_contents;
    int file_size;

    
    public Huffman_Caesar() {
        super(new BorderLayout());
 
        /*
         * Create the text area first-the action listeners
         * are going to use it.
         */
        
        text_area = new JTextArea(20,60);
        text_area.setFont(new Font("Calibri", Font.PLAIN, 13));
        text_area.setMargin(new Insets(5,5,5,5));
        text_area.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(text_area);
        text_area.append("Welcome to Huffman Caesar!" + newline);
        text_area.append("Please open the file you'd like to encrypt or decrypt." + newline);

 
        // JFileChooser allows user to choose a file.
        file_chooser = new JFileChooser();
 
 
        /*
         * Create the open, save, encrypt and decrypt buttons.
         * Open png files from ../images directory as icons.
         * Then create action listeners for each.  
         */
        
        open_button = new JButton("Open",
                                 createImageIcon("../images/open_file.png"));
        save_button = new JButton("Save",
                                 createImageIcon("../images/save_file.png"));
        encrypt_button = new JButton("Compress/Encrypt",
        							createImageIcon("../images/lock_file.png"));
        decrypt_button = new JButton("Decrypt/Decompress",
				 					createImageIcon("../images/unlock_file.png"));
        
        open_button.addActionListener(this);
        save_button.addActionListener(this);
        encrypt_button.addActionListener(this);
        decrypt_button.addActionListener(this);        
 
        /*
         *  Using FlowLayout, create a panel for the buttons to separate from text
         */
        
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(open_button);
        buttonPanel.add(encrypt_button);
        buttonPanel.add(decrypt_button);
        //buttonPanel.add(save_button);
        
        /*
         * Add the buttons and the text section to the panel.
         */
        
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) {
 
    	File save_file, encrypt_file, decrypt_file;
    	FileSaver saver;
    	String line = "";
    	
        /*
         * Handle open button action.
         * Attempts to open file, if it fails it throws an exception.
         * If it succeeds, it writes file contents into an arraylist 
         * where each arraylist element is a line of file contents.
         * To show it works, it will display the first line of the file. 
         */
        if (e.getSource() == open_button) {
            int returnVal = file_chooser.showOpenDialog(Huffman_Caesar.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                this.open_file = file_chooser.getSelectedFile();
                file_contents.clear();
                
                try{
                	this.reader = new BufferedReader(new FileReader(this.open_file));
                    while ((line = this.reader.readLine()) != null){
                    	file_contents.add(line);
                    }
                    this.reader.close();
                    for (int i = 0; i < file_contents.size(); i++)
                    {
                    	longstring = longstring  + file_contents.get(i)+ "\n";
                    }
                    compressed_contents = new ArrayList<String>(file_contents);
                }
                catch (Exception x){
                	   System.err.format("Exception occurred trying to read '%s'.", this.open_file);
                	    x.printStackTrace();
                }
                
                text_area.append("Opening: " + open_file.getName() + "." + newline);
                file_size = (int)open_file.length();
                text_area.append("The file size is " + file_size + newline);
            } else {
                text_area.append("Open cancelled." + newline);
            }
            text_area.setCaretPosition(text_area.getDocument().getLength());
        
        }         
        /*
         * Handle save button action.
         * Attempts to save file, if it fails it throws an exception.
         */
        
        /*else if ((e.getSource() == save_button) & (this.open_file != null)) 
        {
            int returnVal = file_chooser.showSaveDialog(Huffman_Caesar.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                save_file = file_chooser.getSelectedFile();
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
                
                
                //save the file.
                
                
                text_area.append("Saving: " + save_file.getName() + "." + newline);
                text_area.append("Compression ratio is " + (100 * save_file.length()/8)/file_size + "%"+ newline);
            } else {
                text_area.append("Save cancelled." + newline);
            }
            text_area.setCaretPosition(text_area.getDocument().getLength());
            
        } */
        
        /*
         * Handle the encrypt button action
         * Right now this does nothing, but it will encrypt the file
         * and hold it for eventual saving. 
         */
        
        else if ((e.getSource() == encrypt_button) & (this.open_file != null)) {

        	encrypt_file = this.open_file;
                       
                //compress the file.
                           
                text_area.append("Compressing: " + encrypt_file.getName() + "." + newline);
                //text_area.append(longstring);
                Compressor this_compressor = new Compressor(this.longstring, compressed_contents);
                compressed_contents = this_compressor.compress_contents();
                text_area.setCaretPosition(text_area.getDocument().getLength());
                
                //save compressed file. 
        		int returnVal = file_chooser.showSaveDialog(Huffman_Caesar.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) 
                {
                    save_file = file_chooser.getSelectedFile();
                    saver = new FileSaver(save_file, returnVal, compressed_contents, text_area, file_size);
                    saver.save_file();
                    text_area.append("Saving: " + save_file.getName() + "." + newline);
                    text_area.append("Compression ratio is " + (100 * save_file.length()/8)/file_size + "%"+ newline);
                }    
                else 
                {
                    text_area.append("Save cancelled." + newline);
                }
                
                text_area.setCaretPosition(text_area.getDocument().getLength());
                    
                //encrypt the file
                
                       
        } 
        
        /*
         * Handle the decrypt button action
         * Right now this does nothing, but it will decrypt the file
         * and hold save it. 
         */
        
        else if ((e.getSource() == decrypt_button) & (this.open_file != null)) {
                decrypt_file = this.open_file;
                
                //Decompress the file.
                
                text_area.append("Decrypting: " + decrypt_file.getName() + "." + newline);
                Compressor this_compressor = new Compressor(this.longstring, file_contents);
                decompressed_contents = this_compressor.decompress_contents();
                
                //Save decompressed file.
        		int returnVal = file_chooser.showSaveDialog(Huffman_Caesar.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) 
                {
                    save_file = file_chooser.getSelectedFile();
                    saver = new FileSaver(save_file, returnVal, decompressed_contents, text_area, file_size);
                    saver.save_file();
                }    
                else 
                {
                    text_area.append("Save cancelled." + newline);
                }
                
                text_area.setCaretPosition(text_area.getDocument().getLength());
                
        }
        
        /*
         * Handle the case where an encrypt, decrypt or save button is chosen
         * but no file has yet been open. Prompts user to open a file first.
         */
        
        else if (((e.getSource() == encrypt_button) 
        		| (e.getSource() == decrypt_button) 
        		| (e.getSource() == save_button)) 
        		& (this.open_file == null)) {
        	text_area.append("Please open a file first." + newline);
        }
            
    }
 
    /*
     *  Returns an Image icon, or null if the path was invalid. 
     *  These icons are used in the buttons
     */
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Huffman_Caesar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /*
     * This method instantiates the graphical interface.
     */
    
    private static void make_interface() {
 
        // This creates the window and gives it a label.
        
    	JFrame frame = new JFrame("Huffman Caesar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //This populates the frame.
        
        frame.getContentPane().add(new Huffman_Caesar());
 
        //Show the GUI.
        
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        /*
         * Schedule the job for the event dispatch to run,
         * create, and display this application's window.
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                make_interface();
            }
        });
    }
}
/* Gunter 
*                      .NNNNmmmmmmmmmNNNN                     
*                    NNmmmmmmmmmmmmmmmmmmmmmNN                
*                Nhs+//://+oshdmmmmmmmmmmmmmmmmN              
*              N+              ./smmmmmmmmmmmmmmmN            
*             h.                  `+dmmmmmmmmmmmmmm           
*            y                      `smmmmmmmmmmmmmm       WENK WENK!   
*           y                        `smmmmmmmmmmmmmm         /
*        Nyshhs:          .oyddhy+    `dmmmmmmmmmmmmmN       / 
*       o   +NNNy        ++.  sNNNm/  `ommmmmmmmmmmmmmN       
*       o+ohNNNNN-      `y` `/mNNNNm.  -mmmmmmmmmmmmmmm       
*       NNNNNmddho++o/  `dNmNNNNNNNd    dmmmmmmmmmmmmmmN      
*    Ndhyso/:::::::::o-  .yNNNNNNms    `smmmmmmmmmmmmmmm      
*   Nmddds+++oooooooo/      ://:-      `+mmmmmmmmmmmmmmmN     
*        s                              /mmmmmmmmmmmmmmmm     
*        o                              /mmmmmmmmmmmmmmmm     
*        +                              /mmmmmmmmmmmmmmmmN    
*        /                              ommmmmmmmmmmmmmmmN    
*        :                              smmmmmmmmmmmmmmmmm    
*       N-                              hmmmdmmmmmmmmmmmmm    
*       N.                              mmddmmmmmmmmmmmmmmN   
*      mm                              -mdmmmmmmmmmmmmmmmmN   
*     mmm                              odmmmmmmmmmmdmmmmmmN   
*    mmmm                            `smmmmmmmmmmmddmmmmmmN   
*   Nmmmm                          `-dmmmmmmmmmmmmdmmmmmmmN   
*   mmmmm                          /mmmmmmmmmmmmmdmmmmmmmmN   
*  Nmmmmm                        `ommmmmmmmmmmmmmdmmmmmmmmm   
*  Nmmmmm                        ymmmmmmmmmmmmmddmmmmmmmmmm   
*  mmmmmm                      `ymmmmmmmmmmmmmddmmmmmmmmmmm   
*  mmmmNN                      smmmmmmmmmmmmddmmmmmmmmmmmmN   
*  NmNN N                    `-NmmmmmmmmmmddmmmmmmmmmmmmmmN   
*       N.                    `shdddhyddmmmmmmmmmmmmmmmmmmN   
*       N-                            ymmmmmmmmmmmmmmmmmmmN   
*        :                            ymmmmmmmmmmmmmmmmmmmN   
*        /                            ommmmmmmmmmmmmmmmmmmN   
*        o                            /Nmmmmmmmmmmmmmmmmmm    
*        h                            `dmmmmmmmmmmmmmmmmmm    
*        N.                            +mmmmmmmmmmmmmmmmmN    
*         y                            `hmmmmmmmmmmmmmmmm     
*          y.                          `-mmmmmmmmmmmmmmN      
*           Nho+:-.                      -dmmmmmmmmNN         
*                  Nmdhyysoo++/////////++osNNN
*/   