package editor;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Search extends JFrame implements ActionListener {


		 private static final long serialVersionUID = 1L;
		    int startIndex=0;
		    int select_start=-1;
		    JLabel label1, label2;
		    JTextField textFind, textReplace;
		    JButton findButton, findNext, replace, replaceAll, cancel;
		    private JTextArea textArea;
		    
		    public Search(JTextArea text){
		    	textArea = text;
		    	label1 = new JLabel("Find: ");
		    	label2 = new JLabel("Replace: ");
		    	textFind = new JTextField(30);
		    	textReplace = new JTextField(30);
		    	findButton = new JButton("Find");
		    	findNext = new JButton("Find Next");
		    	replace = new JButton("Replace");
		    	replaceAll = new JButton("Replace All");
		    	cancel = new JButton("Cancel");
		    	
		    	setLayout(null);
		    	
		    	int labelWidth = 80;
		    	int labelHeight =20;
		    	label1.setBounds(10,10,labelWidth,labelHeight);
		    	add(label1);
		    	textFind.setBounds(10+labelWidth,10,120,20);
		    	add(textFind);
		    	label2.setBounds(10,10+labelHeight,labelWidth,labelHeight);
		    	add(label2);
		    	textReplace.setBounds(10+labelWidth,10+labelHeight,120,20);
		    	add(textReplace);
		    	
		    	findButton.setBounds(225,6,115,20);
		    	add(findButton);
		    	findButton.addActionListener(this);
		    	
		    	findNext.setBounds(225,28,115,20);
		    	add(findNext);
		    	findNext.addActionListener(this);
		    	
		    	replace.setBounds(225,50,115,20);
		    	add(replace);
		    	replace.addActionListener(this);
		    	
		    	replaceAll.setBounds(225,72,115,20);
		    	add(replaceAll);
		    	replaceAll.addActionListener(this);
		    	
		    	cancel.setBounds(225,94,115,20);
		    	add(cancel);
		    	cancel.addActionListener(this);
		    	
		    	
		    	int width = 360;
		    	int height = 160;
		    	
		    	setSize(width,height);
		    	
		    	Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		    	setLocation(center.x-width/2, center.y-height/2);
		        setVisible(true);
		        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	 }
		    public void find() {
		        select_start = textArea.getText().toLowerCase().indexOf(textFind.getText().toLowerCase());
		        if(select_start == -1)
		        {
		            startIndex = 0;
		            JOptionPane.showMessageDialog(null, "Could not find \"" + textFind.getText() + "\"!");
		            return;
		        }
		        if(select_start == textArea.getText().toLowerCase().lastIndexOf(textFind.getText().toLowerCase()))
		        {
		            startIndex = 0;
		        }
		        int select_end = select_start + textFind.getText().length();
		        textArea.select(select_start, select_end);
		    }
		    public void findNext() {
		        String selection = textArea.getSelectedText();
		        try
		        {
		            selection.equals("");
		        } catch(NullPointerException e1){
		            selection = textFind.getText();
		            try{
		                selection.equals("");
		            }catch(NullPointerException e2){
		                selection = JOptionPane.showInputDialog("Find:");
		                textFind.setText(selection);
		            }
		        } try
		        {
		            int select_start = textArea.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
		            int select_end = select_start+selection.length();
		            textArea.select(select_start, select_end);
		            startIndex = select_end+1;

		            if(select_start == textArea.getText().toLowerCase().lastIndexOf(selection.toLowerCase()))
		            {
		                startIndex = 0;
		            }
		        }
		        catch(NullPointerException e1){
		        	e1.printStackTrace();
		        }
		    }
		    public void replace() {
		        try
		        {
		            find();
		            if (select_start != -1)
		            textArea.replaceSelection(textReplace.getText());
		        }
		        catch(NullPointerException e)
		        {
		            System.out.print("Null Pointer Exception: "+e);
		        }
		    }

		    public void replaceAll() {
		        textArea.setText(textArea.getText().toLowerCase().replaceAll(textFind.getText().toLowerCase(), textReplace.getText()));
		    }
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				   if(e.getSource() == findButton)
			        {
			           find();
			        }
			        else if(e.getSource() == findNext)
			        {
			           findNext();
			        }
			        else if(e.getSource() == replace)
			        {
			            replace();
			        }
			        else if(e.getSource() == replaceAll)
			        {
			           replaceAll();
			        }
			        else if(e.getSource() == cancel)
			        {
			           this.setVisible(false);
			        }
			}

	}


