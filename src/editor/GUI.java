package editor;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.border.Border;
import javax.swing.text.DefaultEditorKit;

public class GUI extends JFrame implements ActionListener{
	
	 private static final long serialVersionUID = 1L;
	    private final JTextArea textArea;
	    private final JMenuBar menuBar;
	    private final JComboBox fontSize, fontType;
	    private final JMenu menuFile, menuEdit, menuSearch,menuAbout;
	    private final JMenuItem newFile, openFile, saveFile, close, cut, copy, paste, selectAll, quickFind,
	             wordWrap,about;
	    private final JToolBar mainToolbar;
	   
	    private final Action selectAllAction;
	    private boolean hasListener = false;
	    
	    public GUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
	    	 
	    	getContentPane();
	    	setSize(700,500);
	    	setTitle("Untitled | " + TextEditor.NAME);
	    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	    	textArea = new JTextArea("",0,0);
	    	textArea.setFont(new Font("Arial",Font.BOLD,10));
	    	textArea.setTabSize(2);
	    	textArea.setFont(new Font("Arial",Font.BOLD,10));
	    	textArea.setTabSize(2);
	    	
	    	textArea.setLineWrap(true);
	    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	getContentPane().setLayout(new BorderLayout());
	    	getContentPane().add(textArea);
	    	centreWindow(this);
	    	menuFile = new JMenu("File");
	    	menuEdit = new JMenu("Edit");
	    	menuSearch = new JMenu("Search");
	    	menuAbout = new JMenu("About");
	    	
	    	newFile = new JMenuItem("New");
	        openFile = new JMenuItem("Open");
	        saveFile = new JMenuItem("Save");
	        close = new JMenuItem("Quit");
	        quickFind = new JMenuItem("Find");
	        
	     
	        about = new JMenuItem("About");
	        
	        menuBar = new JMenuBar();
	        menuBar.add(menuFile);
	        menuBar.add(menuEdit);
	        menuBar.add(menuSearch);
	        menuBar.add(saveFile);
	        menuBar.add(close);
	        menuBar.add(quickFind);
	        menuBar.add(menuAbout);

	       


	        this.setJMenuBar(menuBar);
	        try {
	            this.setIconImage(ImageIO.read(new File("res/doc-icon.png")));
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Set Actions:
	      selectAllAction = new SelectAllAction("Select All",  "Select all text", new Integer(KeyEvent.VK_A),
	                textArea);

	      
	        newFile.addActionListener(this);
	        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
	        menuFile.add(newFile);
	        
	        openFile.addActionListener(this);
	        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
	        menuFile.add(openFile);
	        
	        saveFile.addActionListener(this);
	        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	        menuFile.add(saveFile);

	        // Close File
	       
	        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
	        close.addActionListener(this);
	        menuFile.add(close);
	        
	        about.addActionListener(this);
	        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	        menuAbout.add(about);
	        // Select All Text
	        selectAll = new JMenuItem(selectAllAction);
	        selectAll.setText("Select All");
	        selectAll.setToolTipText("Select All");
	        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
	        menuEdit.add(selectAll);

	        

	        // Cut Text
	        cut = new JMenuItem(new DefaultEditorKit.CutAction());
	        cut.setText("Cut");
	       // cut.setIcon(cutIcon);
	        cut.setToolTipText("Cut");
	        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
	        menuEdit.add(cut);

	        // WordWrap
	        wordWrap = new JMenuItem();
	        wordWrap.setText("Word Wrap");
	        wordWrap.setToolTipText("Word Wrap");
	        wordWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
	        menuEdit.add(wordWrap);

	       
	        wordWrap.addActionListener(new ActionListener()
	        {
	                public void actionPerformed(ActionEvent ev) {
	                    // If wrapping is false then after clicking on menuitem the word wrapping will be enabled
	                    if(textArea.getLineWrap()==false) {
	                        /* Setting word wrapping to true */
	                        textArea.setLineWrap(true);
	                    } else {
	                        // else  if wrapping is true then after clicking on menuitem the word wrapping will be disabled
	                        /* Setting word wrapping to false */
	                        textArea.setLineWrap(false);
	                }
	            }
	        });

	        // Copy Text
	        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
	        copy.setText("Copy");
	        copy.setToolTipText("Copy");
	        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
	        menuEdit.add(copy);

	        // Paste Text
	        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
	        paste.setText("Paste");
	        paste.setToolTipText("Paste");
	        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
	        menuEdit.add(paste);

	        // Find Word
	        quickFind.addActionListener(this);
	        quickFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
	        menuSearch.add(quickFind);

	   
	        mainToolbar = new JToolBar();
	        this.add(mainToolbar, BorderLayout.NORTH);
	        // used to create space between button groups
	        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 50);



	        //FONT FAMILY SETTINGS SECTION 

	        fontType = new JComboBox();

	          //GETTING ALL AVAILABLE FONT FOMILY NAMES
	        String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	        for (int i = 0; i < fonts.length; i++)
	        {
	            //Adding font family names to font[] array
	             fontType.addItem ( fonts [i] );
	        }
	        //Setting maximize size of the fontType ComboBox
	        fontType.setMaximumSize( new Dimension ( 170, 30 ));
	        mainToolbar.add( fontType );
	        mainToolbar.addSeparator();

	        //Adding Action Listener on fontType JComboBox

	        fontType.addActionListener(new ActionListener()
	        {
	                public void actionPerformed(ActionEvent ev)
	                {
	                    //Getting the selected fontType value from ComboBox
	                    String p = fontType.getSelectedItem().toString();
	                    //Getting size of the current font or text
	                    int s = textArea.getFont().getSize();
	                    textArea.setFont( new Font( p, Font.PLAIN, s));
	                }
	        });

	       


	        //FONT SIZE SETTINGS START

	        fontSize = new JComboBox();

	            for( int i = 8 ; i <= 100 ; i+=2)
	            {
	                fontSize.addItem( i );
	            }
	        fontSize.setMaximumSize( new Dimension( 70,30 ));
	        mainToolbar.add( fontSize );

	        fontSize.addActionListener(new ActionListener()
	        {
	                public void actionPerformed(ActionEvent ev)
	                {
	                   String sizeValue = fontSize.getSelectedItem().toString();
	                    int sizeOfFont = Integer.parseInt( sizeValue );
	                    String fontFamily = textArea.getFont().getFamily();

	                    Font font1 = new Font( fontFamily , Font.PLAIN , sizeOfFont );
	                    textArea.setFont( font1 );

	                }
	        });
	       
	    }



	  

	    public void actionPerformed (ActionEvent e) {
	        // If the source of the event was our "close" option
	        if (e.getSource() == close ) {
	            this.dispose(); // dispose all resources and close the application
	        }
	        // If the source was the "new" file option
	        else if (e.getSource() == newFile) {
	            NewFile.clear(textArea);
	        }

	        // If the source was the "open" option
	        else if (e.getSource() == openFile) {
	            JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to  browse files to open)
	            int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)

	            /*
	             * NOTE: because we are OPENing a file, we call showOpenDialog~ if
	             * the user clicked OK, we have "APPROVE_OPTION" so we want to open
	             * the file
	             */
	            if (option == JFileChooser.APPROVE_OPTION) {
	                NewFile.clear(textArea); // clear the TextArea before applying the file contents
	                try {
	                    // create a scanner to read the file (getSelectedFile().getPath() will get the path to the file)
	                    Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
	                    while (scan.hasNext()) // while there's still something to
	                                            // read
	                        textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
	                } catch (Exception ex) { // catch any exceptions, and...
	                    // ...write to the debug console
	                    System.out.println(ex.getMessage());
	                }
	            }
	        }
	        // If the source of the event was the "save" option
	        else if (e.getSource() == saveFile ) {
	            // Open a file chooser
	            JFileChooser fileChoose = new JFileChooser();
	            // Open the file, only this time we call
	            int option = fileChoose.showSaveDialog(this);

	            /*
	             * ShowSaveDialog instead of showOpenDialog if the user clicked OK
	             * (and not cancel)
	             */
	            if (option == JFileChooser.APPROVE_OPTION) {
	                try {
	                    File file = fileChoose.getSelectedFile();
	                    // Set the new title of the window
	                    setTitle(file.getName() + " | " + TextEditor.NAME);
	                    // Create a buffered writer to write to a file
	                    BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
	                    // Write the contents of the TextArea to the file
	                    out.write(textArea.getText());
	                    // Close the file stream
	                    out.close();

	          
	                            
	                        
	                    
	                } catch (Exception ex) { // again, catch any exceptions and...
	                    // ...write to the debug console
	                    System.out.println(ex.getMessage());
	                }
	                // Clear File (Code)
	    	       
	           
	        }
	      //  }

	       

	        // About Me
	     

	    

	  
	                }
	        if (e.getSource() == newFile ) {
 	           NewFile.clear(textArea);
 	        }
 	        // Find
 	        if (e.getSource() == quickFind) {
 	            new Search(textArea);
 	        }
 	        if (e.getSource() == about ) {
 	            new About().program();
 	        }
}
	    class SelectAllAction extends AbstractAction {
  	        /**
  	         * Used for Select All function
  	         */
  	        private static final long serialVersionUID = 1L;

  	        public SelectAllAction(String text, String desc, Integer mnemonic, final JTextArea textArea) {
  	            super(text);
  	            putValue(SHORT_DESCRIPTION, desc);
  	            putValue(MNEMONIC_KEY, mnemonic);
  	        }

  	        public void actionPerformed(ActionEvent e) {
  	            textArea.selectAll();
  	        }
  	    }
	    public  static void centreWindow(Window frame) {
	        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	        frame.setLocation(x, y);
	    }
}
