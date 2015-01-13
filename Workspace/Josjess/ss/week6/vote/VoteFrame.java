package ss.week6.vote;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VoteFrame extends java.awt.Frame implements ActionListener, ItemListener{
	JFrame choiceFrame;
	Choice voteChoice;
	JLabel voteLabel;
	JButton voteButton;
	
	public VoteFrame(){
		choiceFrame();
	}
	
	public void choiceFrame(){
		choiceFrame = new JFrame();
		choiceFrame.setLayout(new FlowLayout());
		
		voteChoice = new Choice();
		voteLabel = new JLabel("Make your choice:");
		
		voteChoice.add("Choose a party");
		voteChoice.add("party1");
		voteChoice.add("party2");
		voteChoice.add("party3");
		
		voteChoice.addItemListener(this);
		
		voteButton = new JButton("Vote");
		voteButton.setEnabled(false);
		voteButton.addActionListener(this);
		
		choiceFrame.add(voteLabel);
		choiceFrame.add(voteChoice);
		choiceFrame.add(voteButton);
		
		choiceFrame.setSize(230, 110);
		choiceFrame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == voteButton){
			voteButton.setEnabled(false);
			voteChoice.select("Choose a party");
			voteLabel.setText("Make your choice");
		}
	}
	
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getItem().equals("party1") || arg0.getItem().equals("party2") || arg0.getItem().equals("party3")){
			voteLabel.setText("Change selection or press Vote");
			voteButton.setEnabled(true);
		}
	}
	
	static public void main(String[] args) {
	    new VoteFrame();
	}

}
