package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.Plate.Color;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import sushigame.view.BeltView;

public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {

	private List<ChefViewListener> listeners;
	private Sushi sushi;
	private int belt_size;
	private IngredientPortion[] ingredients;
	private String[] comboList = { "Red", "Green", "Blue",  "Gold" };
	private String currentColor = "Red";
	private String[] sushiList = { "Sashimi", "Nigiri", "Custom Roll"};
	private int currentPosition = 5;
	private JComboBox colorBox = new JComboBox(comboList);
	private JComboBox sushiBox = new JComboBox(sushiList);
	private JButton create1 = new JButton("Create");
	private String currentType = "Sashimi";
	private JSpinner position;
	private String[] sashimiCombo = {"Tuna", "Salmon", "Eel", "Crab", "Shrimp" };
	private String currentSashimi = "Tuna";
	private String currentNigiri = "Tuna";
	private JComboBox sashimiBox = new JComboBox(sashimiCombo);
	private JComboBox nigiriBox = new JComboBox(sashimiCombo);
	private JButton create2 = new JButton("Create");
	private JOptionPane opRollName;
	private JOptionPane opGoldPrice;
	private double goldPrice;
	private String rollName;
	private JSpinner avocado;
	private double cAvocado = 0;
	private JSpinner crab;
	private double cCrab = 0;
	private JSpinner eel;
	private double cEel = 0;
	private JSpinner rice;
	private double cRice = 0;
	private JSpinner seaweed;
	private double cSeaweed = 0;
	private JSpinner salmon;
	private JSpinner shrimp;
	private double cShrimp = 0;
	private JSpinner tuna;
	private double cTuna = 0;
	private JLabel avocadoTitle;
	private JLabel crabTitle;
	private JLabel colorTitle;
	private JLabel positionTitle;
	private JLabel rollTitle;
	private JLabel eelTitle;
	private JLabel riceTitle;
	private JLabel seaweedTitle;
	private JLabel shrimpTitle;
	private JLabel tunaTitle;
	private JLabel nigiriTitle;
	private JLabel sashimiTitle;
	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		this.avocado = avocado;
		
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
	    
	    colorTitle = new JLabel();
	    colorTitle.setText("Choose your plate color");
	    colorTitle.isOpaque();
		colorTitle.setVisible(true);
		add(colorTitle);
		
		colorBox.addActionListener(this);
		colorBox.isOpaque();
		colorBox.setSize(135, 40);
		colorBox.setActionCommand("color box");
		colorBox.setVisible(true);
		add(colorBox);
		
		positionTitle = new JLabel();
		positionTitle.setText("Choose your position on belt 0-19");
		positionTitle.isOpaque();
		positionTitle.setVisible(true);
		add(positionTitle);
		
		SpinnerNumberModel snm = new SpinnerNumberModel(5, 0, belt_size, 1);
		position = new JSpinner(snm);
		position.isOpaque();
		position.addChangeListener(this);
		position.setVisible(true);
		add(position);	
		
		rollTitle = new JLabel();
		rollTitle.setText("Choose your sushi type");
		rollTitle.isOpaque();
		rollTitle.setVisible(true);
		add(rollTitle);
		
		sushiBox.addActionListener(this);
		sushiBox.isOpaque();
		sushiBox.setSize(135, 40);
		sushiBox.setActionCommand("sushi box");
		sushiBox.setVisible(true);
		add(sushiBox);
		
		create1.addActionListener(this);
		create1.setActionCommand("create1");
		create1.setOpaque(true);
		create1.setVisible(true);
		add(create1);
		
		sashimiTitle = new JLabel();
		sashimiTitle.setText("Choose your sashimi type");
		sashimiTitle.isOpaque();
		sashimiTitle.setVisible(false);
		add(sashimiTitle);
		
		sashimiBox.addActionListener(this);
		sashimiBox.isOpaque();
		sashimiBox.setSize(135, 40);
		sashimiBox.setActionCommand("sashimi box");
		sashimiBox.setVisible(false);
		add(sashimiBox);
		
		nigiriTitle = new JLabel();
		nigiriTitle.setText("Choose your nigiri type");
		nigiriTitle.isOpaque();
		nigiriTitle.setVisible(false);
		add(nigiriTitle);
		
		nigiriBox.addActionListener(this);
		nigiriBox.isOpaque();
		nigiriBox.setSize(135, 40);
		nigiriBox.setActionCommand("nigiri box");
		nigiriBox.setVisible(false);
		add(nigiriBox);
		
		avocadoTitle = new JLabel();
	    avocadoTitle.setText("Choose your avocado amount 0.0 - 1.5 oz");
	    avocadoTitle.isOpaque();
		avocadoTitle.setVisible(false);
		add(avocadoTitle);
		
		SpinnerNumberModel snm2 = new SpinnerNumberModel(0, 0, 1.5, .5);
		avocado = new JSpinner(snm2);
		avocado.isOpaque();
		avocado.addChangeListener(this);
		avocado.setVisible(false);
		add(avocado);	
		
		crabTitle = new JLabel();
	    crabTitle.setText("Choose your crab amount 0.0 - 1.5 oz");
	    crabTitle.isOpaque();
		crabTitle.setVisible(false);
		add(crabTitle);
		
		SpinnerNumberModel snm3 = new SpinnerNumberModel(0, 0, 1.5, .5);
		crab = new JSpinner(snm3);
		crab.isOpaque();
		crab.addChangeListener(this);
		crab.setVisible(false);
		add(crab);	
		
		eelTitle = new JLabel();
	    eelTitle.setText("Choose your eel amount 0.0 - 1.5 oz");
	    eelTitle.isOpaque();
		eelTitle.setVisible(false);
		add(eelTitle);
		
		SpinnerNumberModel snm4 = new SpinnerNumberModel(0, 0, 1.5, .5);
		eel = new JSpinner(snm4);
		eel.isOpaque();
		eel.addChangeListener(this);
		eel.setVisible(false);
		add(eel);
		
		riceTitle = new JLabel();
	    riceTitle.setText("Choose your rice amount 0.0 - 1.5 oz");
	    riceTitle.isOpaque();
		riceTitle.setVisible(false);
		add(riceTitle);
		
		SpinnerNumberModel snm5 = new SpinnerNumberModel(0, 0, 1.5, .5);
		rice = new JSpinner(snm5);
		rice.isOpaque();
		rice.addChangeListener(this);
		rice.setVisible(false);
		add(rice);
		
		SpinnerNumberModel snm6 = new SpinnerNumberModel(0, 0, 1.5, .5);
		salmon = new JSpinner(snm6);
		salmon.isOpaque();
		salmon.addChangeListener(this);
		salmon.setVisible(false);
		add(salmon);
		
		seaweedTitle = new JLabel();
	    seaweedTitle.setText("Choose your seaweed amount 0.0 - 1.5 oz");
	    seaweedTitle.isOpaque();
		seaweedTitle.setVisible(false);
		add(seaweedTitle);
		
		SpinnerNumberModel snm7 = new SpinnerNumberModel(0, 0, 1.5, .5);
		seaweed = new JSpinner(snm7);
		seaweed.isOpaque();
		seaweed.addChangeListener(this);
		seaweed.setVisible(false);
		add(seaweed);
		
		shrimpTitle = new JLabel();
	    shrimpTitle.setText("Choose your shrimp amount 0.0 - 1.5 oz");
	    shrimpTitle.isOpaque();
		shrimpTitle.setVisible(false);
		add(shrimpTitle);
		
		SpinnerNumberModel snm8 = new SpinnerNumberModel(0, 0, 1.5, .5);
		shrimp = new JSpinner(snm8);
		shrimp.isOpaque();
		shrimp.addChangeListener(this);
		shrimp.setVisible(false);
		add(shrimp);

		tunaTitle = new JLabel();
	    tunaTitle.setText("Choose your tuna amount 0.0 - 1.5 oz");
	    tunaTitle.isOpaque();
		tunaTitle.setVisible(false);
		add(tunaTitle);
		
		SpinnerNumberModel snm9 = new SpinnerNumberModel(0, 0, 1.5, .5);
		tuna = new JSpinner(snm9);
		tuna.isOpaque();
		tuna.addChangeListener(this);
		tuna.setVisible(false);
		add(tuna);
		
		create2.addActionListener(this);
		create2.setActionCommand("create2");
		create2.setSize(200, 200);
		create2.setOpaque(true);
		create2.setVisible(false);
		add(create2);
		
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
	public int getPosition() {
		return currentPosition;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "color box":
			JComboBox cb = (JComboBox)e.getSource();
	        String lists = (String)cb.getSelectedItem();
	        if (lists.equals("Red")) {
				currentColor = "Red";
				break;
			} else if (lists.equals("Blue")) {
				currentColor = "Blue";
				break;
			} else if (lists.equals("Green")) {
				currentColor = "Green";
				break;
			} else {
				currentColor = "Gold";
				opGoldPrice = new JOptionPane("Select price for your roll");
				String goldWorks = "5.00";
				goldWorks = opGoldPrice.showInputDialog("Input plate price betwen $5.00 and $10.00");
				opGoldPrice.isOpaque();
				goldPrice = Double.parseDouble(goldWorks);
				break;
			}
		case "sushi box":
			JComboBox cb2 = (JComboBox)e.getSource();
	        String lists2 = (String)cb2.getSelectedItem();
	        if(lists2.equals("Sashimi")) {
	        	currentType = "Sashimi";
	        	break;
	        } else if (lists2.equals("Nigiri")) {
	        	currentType = "Nigiri";
	        	break;
	        } else {
	        	currentType = "Custom Roll";
	        	break;
	        }
		case "create1":
			if(currentType.equals("Sashimi")) {
				sashimiBox.setVisible(true);
				position.setVisible(false);
				sushiBox.setVisible(false);
				colorBox.setVisible(false);
				create1.setVisible(false);
				create2.setVisible(true);
				positionTitle.setVisible(false);
				colorTitle.setVisible(false);
				rollTitle.setVisible(false);
				sashimiTitle.setVisible(true);
			} else if (currentType.equals("Nigiri")) {
				nigiriBox.setVisible(true);
				position.setVisible(false);
				sushiBox.setVisible(false);
				colorBox.setVisible(false);
				create1.setVisible(false);
				create2.setVisible(true);
				positionTitle.setVisible(false);
				colorTitle.setVisible(false);
				rollTitle.setVisible(false);
				nigiriTitle.setVisible(true);
			} else {
				opRollName = new JOptionPane("Name your Roll");
				rollName = opRollName.showInputDialog("Name your Roll");
				opRollName.isOpaque();
				
				position.setVisible(false);
				sushiBox.setVisible(false);
				colorBox.setVisible(false);
				create1.setVisible(false);
				positionTitle.setVisible(false);
				colorTitle.setVisible(false);
				rollTitle.setVisible(false);
				create2.setVisible(true);
				avocado.setVisible(true);
				crab.setVisible(true);
				eel.setVisible(true);
				rice.setVisible(true);
				seaweed.setVisible(true);
				shrimp.setVisible(true);
				tuna.setVisible(true);
				
				avocadoTitle.setVisible(true);
				crabTitle.setVisible(true);
				eelTitle.setVisible(true);
				riceTitle.setVisible(true);
				seaweedTitle.setVisible(true);
				shrimpTitle.setVisible(true);
				tunaTitle.setVisible(true);
			}
			break;
		case "sashimi box":
			JComboBox cb3 = (JComboBox)e.getSource();
	        String lists3 = (String)cb3.getSelectedItem();
	        if(lists3.equals("Tuna")) {
	        	currentSashimi = "Tuna";
	        } else if(lists3.equals("Eel")) {
	        	currentSashimi = "Eel";
	        } else if(lists3.equals("Crab")) {
	        	currentSashimi = "Crab";
	        } else if(lists3.equals("Shrimp")) {
	        	currentSashimi = "Shrimp";
	        }
	        break;
		case "nigiri box":
			JComboBox cb4 = (JComboBox)e.getSource();
	        String lists4 = (String)cb4.getSelectedItem();
	        if(lists4.equals("Tuna")) {
	        	currentNigiri = "Tuna";
	        } else if(lists4.equals("Eel")) {
	        	currentNigiri = "Eel";
	        } else if(lists4.equals("Crab")) {
	        	currentNigiri = "Crab";
	        } else if(lists4.equals("Shrimp")) {
	        	currentNigiri = "Shrimp";
	        }
	        break;
		case "create2": {
			createSushi();
			nigiriBox.setVisible(false);
			sashimiBox.setVisible(false);
			position.setVisible(true);
			sushiBox.setVisible(true);
			colorBox.setVisible(true);
			create1.setVisible(true);
			create2.setVisible(false);
			avocado.setVisible(false);
			crab.setVisible(false);
			eel.setVisible(false);
			rice.setVisible(false);
			salmon.setVisible(false);
			seaweed.setVisible(false);
			shrimp.setVisible(false);
			tuna.setVisible(false);
			positionTitle.setVisible(true);
			colorTitle.setVisible(true);
			rollTitle.setVisible(true);
			nigiriTitle.setVisible(false);
			sashimiTitle.setVisible(false);
			avocadoTitle.setVisible(false);
			crabTitle.setVisible(false);
			eelTitle.setVisible(false);
			riceTitle.setVisible(false);
			seaweedTitle.setVisible(false);
			shrimpTitle.setVisible(false);
			tunaTitle.setVisible(false);
			break;
		}
		}
	        
	}

	public void createSushi() {
		if(currentType.equals("Sashimi")) {
			if(currentSashimi.equals("Tuna")) {
				sushi = new Sashimi(Sashimi.SashimiType.TUNA);
			} else if (currentSashimi.equals("Crab")) {
				sushi = new Sashimi(Sashimi.SashimiType.CRAB);
			} else if (currentSashimi.equals("Eel")) {
				sushi = new Sashimi(Sashimi.SashimiType.EEL);
			} else {
				sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			}
			if (currentColor.equals("Blue")) {
				makeBluePlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Red")) {
				makeRedPlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Green")) {
				makeGreenPlateRequest(sushi, currentPosition);
			} else {
				makeGoldPlateRequest(sushi, currentPosition, goldPrice);
			}
		} else if(currentType.equals("Nigiri")) {
			if(currentNigiri.equals("Tuna")) {
				sushi = new Nigiri(Nigiri.NigiriType.TUNA);
			} else if (currentNigiri.equals("Crab")) {
				sushi = new Nigiri(Nigiri.NigiriType.CRAB);
			} else if (currentNigiri.equals("Eel")) {
				sushi = new Nigiri(Nigiri.NigiriType.EEL);
			} else {
				sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
			}
			if (currentColor.equals("Blue")) {
				makeBluePlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Red")) {
				makeRedPlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Green")) {
				makeGreenPlateRequest(sushi, currentPosition);
			} else {
				makeGoldPlateRequest(sushi, currentPosition, goldPrice);
			}
		} else {
			int ingredientCount=0;
			
			if (cAvocado >0) {
				ingredientCount++;
			} if (cEel > 0) {
				ingredientCount++;
			} if (cRice > 0) {
				ingredientCount++;
			} if (cSeaweed > 0) {
				ingredientCount++;
			} if (cShrimp > 0) {
				ingredientCount++;
			} if (cTuna > 0) {
				ingredientCount++;
			} if (cCrab > 0) {
				ingredientCount++;
			}
			IngredientPortion[] ingredients = new IngredientPortion[ingredientCount];
			int countUp = 0;
			
			if (cAvocado >0) {
				ingredients[countUp] = new AvocadoPortion(cAvocado);
				countUp++;
			} if (cCrab > 0) {
				ingredients[countUp] = new CrabPortion(cCrab);
				countUp++;
			} if (cEel > 0) {
				ingredients[countUp] = new RicePortion(cEel);
				countUp++;
			} if (cRice > 0) {
				ingredients[countUp] = new RicePortion(cRice);
				countUp++;
			} if (cSeaweed > 0) {
				ingredients[countUp] = new SeaweedPortion(cSeaweed);
				countUp++;
			} if (cShrimp > 0) {
				ingredients[countUp] = new ShrimpPortion(cShrimp);
				countUp++;
			} if (cTuna > 0) {
				ingredients[countUp] = new TunaPortion(cTuna);
				countUp++;
			} 
			
			sushi = new Roll(rollName, ingredients);
			
			if (currentColor.equals("Blue")) {
				makeBluePlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Red")) {
				makeRedPlateRequest(sushi, currentPosition);
			} else if (currentColor.equals("Green")) {
				makeGreenPlateRequest(sushi, currentPosition);
			} else {
				makeGoldPlateRequest(sushi, currentPosition, goldPrice);
			}
		}
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		 if(e.getSource() == position) {
			 JSpinner s = (JSpinner) e.getSource();
	         currentPosition = (int) s.getValue();
		 }
		 if(e.getSource() == avocado) {
			 JSpinner s = (JSpinner) e.getSource();
	         cAvocado = (double) s.getValue();
		 } 
		 if (e.getSource() == crab) {
			 JSpinner s = (JSpinner) e.getSource();
	         cCrab = (double) s.getValue();
		 }
		 if(e.getSource() == eel) {
			 JSpinner s = (JSpinner) e.getSource();
	         cEel = (double) s.getValue();
		 }
		 if(e.getSource() == rice) {
			 JSpinner s = (JSpinner) e.getSource();
	         cRice = (double) s.getValue();
		 }
		 if(e.getSource() == seaweed) {
			 JSpinner s = (JSpinner) e.getSource();
	         cSeaweed = (double) s.getValue();
		 }
		 if(e.getSource() == shrimp) {
			 JSpinner s = (JSpinner) e.getSource();
	         cShrimp = (double) s.getValue();
		 }
		 if(e.getSource() == tuna) {
			 JSpinner s = (JSpinner) e.getSource();
	         cTuna = (double) s.getValue();
		 }
	}
}
