package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorGUI {

	private String message;
	private JPanel errorPanel;
	private JFrame errorFrame;
	private JButton okButton;
	
	public ErrorGUI(String message){
		this.message = message;
		createErrorFrame();
	}
	
	private JFrame createErrorFrame(){
		errorFrame = new JFrame();
		BorderLayout border = new BorderLayout();
		errorFrame.setLayout(border);
		errorFrame.setSize(300, 100);
		errorFrame.add(createErrorPanel(), BorderLayout.CENTER);
		errorFrame.setVisible(true);
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return errorFrame;
	}
	
	private JPanel createErrorPanel(){
		errorPanel = new JPanel();
		JLabel errorMessage = new JLabel(message);
		BorderLayout border = new BorderLayout();
		errorPanel.setLayout(border);
		errorPanel.add(errorMessage, BorderLayout.CENTER);
		okButton = new JButton("Close");
		okButton.setActionCommand("close");
		errorPanel.add(okButton,BorderLayout.SOUTH);
		return errorPanel;
	}
	
	public static void main(String[] args){
		ErrorGUI error = new ErrorGUI("Hello");
	}
}
