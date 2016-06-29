import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SerializeUserPreferences {
	UserPreferences userPrefs = new UserPreferences("", "", 0);
	
	SerializeUserPreferences(UserPreferences prefs) {
		userPrefs.setBackgroundColor(prefs.getBackgroundColor());
		userPrefs.setFontFamily(prefs.getFontFamily());
		userPrefs.setFontSize(prefs.getFontSize());
		System.out.println("SerializeUserPreferences created.");
	}
	
	public static void main(String args[]) {
		UserPreferences userPrefs = new UserPreferences("test color", "test family", 100);
		System.out.println("In SerializeUserPreferences main method.");
		
		try (FileOutputStream fOut = new FileOutputStream("UserPrefs.ser");
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);){
			oOut.writeObject(userPrefs); //serializing preferences
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("UserPreference object has been serialized into UserPrefs.ser");
	}
}