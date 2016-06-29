import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Simple customizable GUI. Preferences dialog saves the user's choices
 * and reloads them the next time the window is opened.
 * 
 * @author David Lall
 */

class CustomizableGUI extends JFrame {
	private static final long serialVersionUID = -6574305728106595108L;
	private final String USER_PREFS_LOCATION = "UserPrefs.ser";
	private Color bgColor;
	private Font font;
	private JTextField txtField = new JTextField(30);
	private JButton bPrefs = new JButton("Preferences");
	
	/**
	 * Standard setup for a JFrame. Gets the background color, font family, 
	 * and font size from the UserPrefs.ser file.
	 */
	CustomizableGUI() {
		setTitle("Customizable GUI");
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		UserPreferences userPrefs = new UserPreferences(USER_PREFS_LOCATION);
		try {
		    Field field = Class.forName("java.awt.Color").getField(userPrefs.getBackgroundColor());
		    bgColor = (Color)field.get(null);
		} catch (Exception e) {
		    bgColor = null; //Not defined
		}
		getContentPane().setBackground(bgColor);
		font = new Font(userPrefs.getFontFamily(), Font.BOLD, userPrefs.getFontSize());
		bPrefs.setFont(font);
		add(txtField);
		add(bPrefs);
		
		//Preferences button processing using lambda expression
		bPrefs.addActionListener(event-> {
			PreferencesDialog prefs = new PreferencesDialog(this);
			prefs.setSize(350, 125);
			prefs.setVisible(true);
		});
		
		//Define, instantiate and register a WindowAdapter
		//to process windowClosing Event of this frame
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * Getter for the font family.
	 * @return
	 * 		Font family.
	 */
	public Font getFont() {
		return font;
	}
	
	public static void main(String args[]) {
		CustomizableGUI customFrame = new CustomizableGUI();
		customFrame.setSize(400, 150);
		customFrame.setVisible(true);
	}
}