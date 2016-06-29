import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

class PreferencesDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7316049960822654751L;
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
		bSave.setFont(parent.getFont());
		bCancel.setFont(parent.getFont());
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
	
	private void populateBgColor() {
		try (FileInputStream myFile = 
				new FileInputStream("bgcolor.txt");
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
	
	private void populateFontFamily() {
		try (FileInputStream myFile = 
				new FileInputStream("fontfamily.txt");
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
	
	private void populateFontSize() {
		try (FileInputStream myFile = 
				new FileInputStream("fontsize.txt");
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
}