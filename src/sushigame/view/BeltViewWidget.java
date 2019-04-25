package sushigame.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import comp401sushi.Plate.Color;

public class BeltViewWidget extends JPanel{
	String name, chef, color, age;


	BeltViewWidget(Plate p){
		name = p.getContents().getName();
		color = p.getColor().toString();
		chef = p.getChef().getName();
		
		add(new JLabel("Name: " + name + " Color: " + color + " Chef: " + chef));
	}
}
