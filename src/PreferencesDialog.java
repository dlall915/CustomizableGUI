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
	JComboBox<String> chBgColor = new JComboBox<String>();
	JComboBox<String> chFontFamily = new JComboBox<String>();
	JComboBox<Integer> chFontSize = new JComboBox<Integer>();
	JButton bSave = new JButton("Save");
	JButton bCancel = new JButton("Cancel");
	
	PreferencesDialog(JFrame parent) {
		super(parent, "Preferences");
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
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
			
			//UserPreferences test = new UserPreferences("GREEN", "Arial", 12);
			//System.out.println("Test UserPreferences created.");
			UserPreferences test = new UserPreferences(chBgColor.getSelectedItem().toString(), 
					chFontFamily.getSelectedItem().toString(), 
					Integer.parseInt(chFontSize.getSelectedItem().toString()));
			test.serialize();
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