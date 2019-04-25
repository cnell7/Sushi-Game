package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401sushi.Ingredient;
import comp401sushi.IngredientPortion;
import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.view.PlayerChefView;

public class BeltView extends JPanel implements BeltObserver, ActionListener {

	private Belt belt;
	private JButton[] belt_buttons;
	

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_buttons = new JButton[belt.getSize()];
		
		
		for(int i = 0; i< belt.getSize(); i++) {
			JButton pbutton = new JButton("");
			pbutton.setMinimumSize(new Dimension(300, 20));
			pbutton.setPreferredSize(new Dimension(300, 20));
			pbutton.setOpaque(true);
			pbutton.setVisible(true);
			pbutton.setActionCommand("button" + i);
			pbutton.addActionListener(this);
			add(pbutton);
			belt_buttons[i] = pbutton;
			
		}
		
		
		
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}
	
	public JButton[] getButtons() {
		return belt_buttons;
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JButton plabel = belt_buttons[i];

			if (p == null) {
				plabel.setText("");
				plabel.setBackground(Color.GRAY);
			} else {
				plabel.setText(p.getChef().getName().toString());
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED); break;
				case GREEN:
					plabel.setBackground(Color.GREEN); break;
				case BLUE:
					plabel.setBackground(Color.BLUE); break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<belt.getSize(); i++) {
			if (e.getActionCommand().equals("button" + i)) {
				Plate p = belt.getPlateAtPosition(i);
				if(p != null) {					
					JOptionPane pane = new JOptionPane();					
					
					if(p.getContents().getName().contains("sashimi")) {
						pane.showMessageDialog(null, "This is a " + p.getColor().toString().toLowerCase() + " plate" + ".\n" + 
								"This is " + p.getContents().getName().replace("sashimi", "") + "sashimi" + ".\n" +
								"This is " + p.getChef().getName().toString() + "'s plate" + ".\n" +
								"This plate is " + belt.getAgeOfPlateAtPosition(i) + " rotations old");
								
					} else if(p.getContents().getName().contains("nigiri")) {
						pane.showMessageDialog(null, "This is a " + p.getColor().toString().toLowerCase() + " plate" + ".\n" + 
								"This is " + p.getContents().getName().replace("nigiri", "") + "nigiri" + ".\n" +
								"This is " + p.getChef().getName().toString() + "'s plate" + ".\n" +
								"This plate is " + belt.getAgeOfPlateAtPosition(i) + " rotations old");
					} else {
						IngredientPortion[] ingredient = p.getContents().getIngredients();
						String ingredients = ".\n" + "A " + p.getContents().getName() + " has:";
						for (int j = 0; j<ingredient.length; j++) {
							if(ingredient[j] != null) {
								ingredients += "\n" + ((int) ((ingredient[j].getAmount() * 100.0)+0.5))/100.0 + " ounces of " + ingredient[j].getName();
							}
						}
						pane.showMessageDialog(null, "This is a " + p.getColor().toString().toLowerCase() + " plate" + ".\n" + 
								"This is a " + p.getContents().getName() +
								ingredients + ".\n" +
								"This is " + p.getChef().getName().toString() + "'s plate" + ".\n" +
								"This plate is " + belt.getAgeOfPlateAtPosition(i) + " rotations old");
					}
				
				}
			}
		}
		
	}
}
