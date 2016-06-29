import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserPreferences implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8083477229353468L;
	private String backgroundColor;
	private String fontFamily;
	private double fontSize;
	
	UserPreferences (String backgroundColor, String fontFamily, double fontSize) {
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
	
	Double getFontSize() {
		return fontSize;
	}
	
	void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	
	void serialize() {
		try (FileOutputStream fOut = new FileOutputStream("UserPrefs.ser");
				ObjectOutputStream oOut = new ObjectOutputStream(fOut);){
				oOut.writeObject(this); //serializing preferences
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}