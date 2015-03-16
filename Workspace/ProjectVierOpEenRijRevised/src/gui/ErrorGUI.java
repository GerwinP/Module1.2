package gui;

import javax.swing.JPanel;
import javax.swing.*;

public class ErrorGUI {

	private String message;
	private JPanel errorPanel;
	
	public ErrorGUI(String message){
		this.message = message;
	}
	
	private JPanel errorPanel(){
		
		return errorPanel;
	}
}
