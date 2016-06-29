import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

class MyCustomizableGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6574305728106595108L;
	JTextField txtField = new JTextField(30);
	JButton bPrefs = new JButton("Preferences");
	
	MyCustomizableGUI() {
		FlowLayout fl = new FlowLayout();
		setLayout(fl);
		add(txtField);
		add(bPrefs);
		
		getContentPane().setBackground(Color.CYAN);
		Font font = new Font("Calibri", Font.BOLD, 12);
		bPrefs.setFont(font);
		
		//Preferences button processing using lambda expression
		bPrefs.addActionListener(event-> {
			PreferencesDialog prefs = new PreferencesDialog(new JFrame());
			prefs.setSize(400, 150);
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
	
	public static void main(String args[]) {
		MyCustomizableGUI customFrame = new MyCustomizableGUI();
		customFrame.setSize(400, 150);
		customFrame.setVisible(true);
	}
}