import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

class PreferencesDialog extends JDialog {
	private static final long serialVersionUID = -7316049960822654751L;
	private final String BG_COLOR_LOCATION = "bgcolor.txt";
	private final String FONT_FAMILY_LOCATION = "fontfamily.txt";
	private final String FONT_SIZE_LOCATION = "fontsize.txt";
	private JComboBox<String> chBgColor = new JComboBox<String>();
	private JComboBox<String> chFontFamily = new JComboBox<String>();
	private JComboBox<Integer> chFontSize = new JComboBox<Integer>();
	private JButton bSave = new JButton("Save");
	private JButton bCancel = new JButton("Cancel");
	
	PreferencesDialog(JFrame parent) {
		super(parent, "Preferences");
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		getContentPane().setBackground(parent.getContentPane().getBackground());
		setAllFonts(parent.getFont());
		populateBgColor();
		populateFontFamily();
		populateFontSize();
		add(chBgColor);
		add(chFontFamily);
		add(chFontSize);
		add(bSave);
		add(bCancel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
		//Save button processing using lambda expression
		bSave.addActionListener(event-> {
			UserPreferences userPrefs = new UserPreferences(chBgColor.getSelectedItem().toString(), 
					chFontFamily.getSelectedItem().toString(), 
					Integer.parseInt(chFontSize.getSelectedItem().toString()));
			userPrefs.serialize();
			dispose();
		});
		
		//Cancel button processing using lambda expression
		bCancel.addActionListener(event-> {
			dispose();
		});
	}
	
	/**
	 * Reads in all the values for the background colors from a file.
	 */
	private void populateBgColor() {
		try (FileInputStream myFile = 
				new FileInputStream(BG_COLOR_LOCATION);
			InputStreamReader inputStreamReader = 
				new InputStreamReader(myFile, "UTF8");
			BufferedReader reader = 
				new BufferedReader(inputStreamReader);) {
			
				String colorName;
				while((colorName = reader.readLine()) != null) {
					chBgColor.addItem(colorName);
				}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Reads in all the values for the font family from a file.
	 */
	private void populateFontFamily() {
		try (FileInputStream myFile = 
				new FileInputStream(FONT_FAMILY_LOCATION);
			InputStreamReader inputStreamReader = 
				new InputStreamReader(myFile, "UTF8");
			BufferedReader reader = 
				new BufferedReader(inputStreamReader);) {
			
				String fontFamilyName;
				while((fontFamilyName = reader.readLine()) != null) {
					chFontFamily.addItem(fontFamilyName);
				}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Reads in all the values for the font size from a file.
	 */
	private void populateFontSize() {
		try (FileInputStream myFile = 
				new FileInputStream(FONT_SIZE_LOCATION);
			InputStreamReader inputStreamReader = 
				new InputStreamReader(myFile, "UTF8");
			BufferedReader reader = 
				new BufferedReader(inputStreamReader);) {
			
				String fontSizeVal;
				while((fontSizeVal = reader.readLine()) != null) {
					chFontSize.addItem(Integer.parseInt(fontSizeVal));
				}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Sets the fonts of everything in the Dialog box to a given font.
	 * @param font
	 * 		Some font.
	 */
	private void setAllFonts(Font font) {
		chBgColor.setFont(font);
		chFontFamily.setFont(font);
		chFontSize.setFont(font);
		bSave.setFont(font);
		bCancel.setFont(font);
	}
}