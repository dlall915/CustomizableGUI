import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserPreferences implements Serializable{	
	private static final long serialVersionUID = -8083477229353468L;
	private final String SERIALIZED_FILE_NAME = "UserPrefs.ser";
	private String backgroundColor;
	private String fontFamily;
	private int fontSize;
	
	UserPreferences() {
		this.backgroundColor = null;
		this.fontFamily = null;
		this.fontSize = 0;
	}
	
	UserPreferences(String fileName) {
		deserialize(fileName);
	}
	
	UserPreferences(String backgroundColor, String fontFamily, int fontSize) {
		this.backgroundColor = backgroundColor;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
	
	/**
	 * Getter for the background color.
	 * @return
	 * 		Background color.
	 */
	String getBackgroundColor() {
		return backgroundColor;
	}
	
	/**
	 * Setter for the background color.
	 * @param backgroundColor
	 * 		Background color.
	 */
	void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * Getter for the font family.
	 * @return
	 * 		Font family.
	 */
	String getFontFamily() {
		return fontFamily;
	}
	
	/**
	 * Setter for the font family.
	 * @param fontFamily
	 * 		Font family.
	 */
	void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	/**
	 * Getter for the font size.
	 * @return
	 * 		Font size.
	 */
	int getFontSize() {
		return fontSize;
	}
	
	/**
	 * Setter for the font size.
	 * @param fontSize
	 * 		Font size.
	 */
	void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	/**
	 * Sets this equal to the passed UserPreference object.
	 * @param userPrefs
	 * 		Some UserPreference.
	 */
	void setEqualTo(UserPreferences userPrefs) {
		this.backgroundColor = userPrefs.getBackgroundColor();
		this.fontFamily = userPrefs.getFontFamily();
		this.fontSize = userPrefs.getFontSize();
	}
	
	/**
	 * Serializes this UserPreference and stores it in a file named
	 * UserPrefs.ser.
	 */
	void serialize() {
		try(FileOutputStream fOut = new FileOutputStream(SERIALIZED_FILE_NAME);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);){
			oOut.writeObject(this);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Deserializes a UserPreference object and sets this UserPreference
	 * equal to it.
	 * 
	 * @param fileName
	 * 		Name of the file with the serialized UserPreferences object.
	 */
	void deserialize(String fileName) {
		UserPreferences userPrefs = new UserPreferences();
		
		try(FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream oIn = new ObjectInputStream(fIn);){
			userPrefs = (UserPreferences)oIn.readObject();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		this.setEqualTo(userPrefs);
	}
}