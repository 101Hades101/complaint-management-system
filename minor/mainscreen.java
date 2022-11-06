import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class mainscreen implements ActionListener, WindowListener {
	private JFrame win;
	private complaintSoln cfile;
	private JButton menuBtns[];
	//admin password 
	private final String password = "admin";
	//folderpath
	String folderPath = "C:\\ProjectData\\";
	String fileName = "ComplData.txt";
	//data file apth
	String about = "About.txt";
	String logo = "banner.jpg";
	String banner = "logo.jpg";
	//all image path


	public mainscreen() {
		win = new JFrame();
		
		cfile = new complaintSoln(folderPath + fileName);
			//main screen gui.
		win.setTitle("UPES Hostel Complaint managment");
		win.setSize(800, 1000);
		win.addWindowListener(this);
		win.setLayout(new GridLayout(6, 1));
		
			//for image
		Image icon = Toolkit.getDefaultToolkit().getImage(folderPath + logo);  
		win.setIconImage(icon);  
		
		Container c = win.getContentPane(); // Gets the content layer
		JLabel label = new JLabel(); // JLabel Creation
		label.setIcon(new ImageIcon(folderPath + banner)); // Sets the image to be displayed ass an icon
		 // Gets the size of the image
		label.setBounds(20 , 0, 0, 0); // Sets the location of the image
		label.setBorder(new LineBorder(Color.BLACK));

		c.add(label); // Adds objects to the container
		win.setVisible(true); // Exhibit the frame
			//all the buttons
		menuBtns = new JButton[5];
		int font =22;
		Font myFont = new Font("Courier", Font.ITALIC,font);
		for (int i = 0; i < menuBtns.length; ++i) {
			menuBtns[i] = new JButton();
			menuBtns[i].setFont(myFont);
			win.add(menuBtns[i]);
			menuBtns[i].addActionListener(this);
		}
		menuBtns[0].setText("Register a Complaint");
		menuBtns[1].setText("Status of Complaint");
		menuBtns[2].setText("Check Complaint Filed");
		menuBtns[3].setText("Report");
		menuBtns[4].setText("About");

		//menuBtns[0].setEnabled(false);
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			//ahout button function
			String aboutData = new String(Files.readAllBytes(Paths.get(folderPath + about)));
		
		if (menuBtns[0] == e.getSource()) {
			new registerComplaint(cfile);
		} else if (menuBtns[1] == e.getSource()) {
			new check_compstatus(cfile);
		} else if (menuBtns[2] == e.getSource()) {
			String pwdEntered = JOptionPane.showInputDialog(win, "Enter the Password : ");
			if (pwdEntered == null) {
				// if pass is  blank do nothing
			} else if (pwdEntered.equals(password)) {
				new updatesoln(cfile);
			} else {
				JOptionPane.showMessageDialog(win, "Wrong password Entered");
			}
		} else if (menuBtns[3] == e.getSource()) {
			new report(cfile);
		}
		else if (menuBtns[4] == e.getSource()) {
			JOptionPane.showMessageDialog(win, aboutData);
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		win.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		cfile.exit();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

}
