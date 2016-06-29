import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserPreferences implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083477229353468L;
	private String backgroundColor;
	private String fontFamily;
	private int fontSize;
	
	UserPreferences() {
		this.backgroundColor = null;
		this.fontFamily = null;
		this.fontSize = 0;
	}
	
	UserPreferences(String backgroundColor, String fontFamily, int fontSize) {
		this.backgroundColor = backgroundColor;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
	
	String getBackgroundColor() {
		return backgroundColor;
	}
	
	void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	String getFontFamily() {
		return fontFamily;
	}
	
	void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	int getFontSize() {
		return fontSize;
	}
	
	void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	void setEqualTo(UserPreferences userPrefs) {
		this.backgroundColor = userPrefs.getBackgroundColor();
		this.fontFamily = userPrefs.getFontFamily();
		this.fontSize = userPrefs.getFontSize();
	}
	
	void serialize() {
		try(FileOutputStream fOut = new FileOutputStream("UserPrefs.ser");
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);){
			oOut.writeObject(this); //serializing preferences
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
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