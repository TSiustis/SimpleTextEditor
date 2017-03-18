package editor;

import javax.swing.JTextPane;
import javax.swing.UnsupportedLookAndFeelException;

public class TextEditor extends JTextPane{
	private static final long serialVersionUID = 1L;
    public final static String NAME = "SimpleEditor";
    public final static double VERSION = 1.0;

   
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new GUI().setVisible(true);
    }
}
