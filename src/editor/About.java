package editor;

import javax.swing.*;
import java.awt.FlowLayout;
public class About {
	private final JFrame frame;
	private final JPanel panel;
	private String contentText;
	private final JLabel text;
	
	public About(){
		panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
		frame = new JFrame();
	    frame.setVisible(true);
	    frame.setSize(200,100);
	    text = new JLabel();	
	}

	
	public void program(){
		frame.setTitle("About");
		
		contentText=
				"<html><body><p>Name: " + TextEditor.NAME + "<br/>"+
				"Version: " + TextEditor.VERSION + "</p></body></html>";
		text.setText(contentText);
		panel.add(text);
		frame.add(panel);
	}

}
