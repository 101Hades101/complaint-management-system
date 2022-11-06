import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class check_compstatus implements ActionListener {
	private JDialog win;
	private complaintSoln cfile;
	private int compNum;
	private JTextField tfCompNum;
	private JTextArea taStatus;

	public check_compstatus(complaintSoln cfile) {
		win = new JDialog();
		win.setModalityType(ModalityType.APPLICATION_MODAL);
		win.setTitle("Complaint Status Box");
		win.setSize(540, 400);
		win.setLayout(new GridLayout(2, 1));
		this.cfile = cfile;


		tfCompNum = new JTextField(20);
		tfCompNum.addActionListener(this);
		
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Enter Complaint No. : "));
		panel1.add(tfCompNum);
			// for the enter the complaint box
		
		taStatus = new JTextArea(6, 50);
		taStatus.setEditable(false);

		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Status \n "));
		panel2.add(new JScrollPane(taStatus));
			// for status diplay box
		win.add(panel1);
		win.add(panel2);

		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.compNum = Integer.parseInt(tfCompNum.getText());
			String status = cfile.getSoln(compNum);
			if (status == null) {
				status = "Invalid Complaint No.";
			} else if (status.isEmpty()) {
				status = "No Solution found for given complaint number";
			}
			taStatus.setText(status);
		} catch (Exception exc) {
			taStatus.setText("Invalid Complaint No.");
		}
	}

}
