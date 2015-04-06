package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		errorFrame.setVisible(true);
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return errorFrame;
	}
	
	public static void main(String[] args){
		ErrorGUI error = new ErrorGUI("Hello");
	}
}
