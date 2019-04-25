package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private String[] comboLists = { "Account Balance", "Food Sold", "Food Spoiled" };
	private String currentLabel = "Account Balance";
	
	public ScoreboardWidget(SushiGameModel gm) {	
		
		JComboBox combo = new JComboBox(comboLists);
		combo.setSelectedIndex(0);
		combo.addActionListener((ActionListener) this);
		combo.isOpaque();
		combo.setSize(135, 40);
		combo.move(0,  42);
		
		add(combo);
		
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardBalance());
	
	}

	private String makeScoreboardBalance() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
		sb_html += "<br>";
		sb_html += "<br>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}
	
	private String makeScoreboardFoodSold() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
		sb_html += "<br>";
		sb_html += "<br>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowFoodComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getFoodConsumed()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}

	private String makeScoreboardFoodSpoiled() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
		sb_html += "<br>";
		sb_html += "<br>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new LowToHighSpoiledComparator());
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getFoodSpoiled()*100.0)/100.0 + ") <br>";
		}
		return sb_html;
	}
	
	public void refresh() {
		if (currentLabel.equals("Account Balance")) {
			display.setText(makeScoreboardBalance());		
		} else if (currentLabel.equals("Food Sold")) {
			display.setText(makeScoreboardFoodSold());		
		} else if (currentLabel.equals("Food Spoiled")) {
			display.setText(makeScoreboardFoodSpoiled());		
		} else {
			display.setText(makeScoreboardBalance());
		}
	}
	
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String lists = (String)cb.getSelectedItem();
		if(lists.equals("Account Balance")) {
			currentLabel = "Account Balance";
			display.setText(makeScoreboardBalance());		
		} else if (lists.equals("Food Sold")) {
			currentLabel = "Food Sold";
			display.setText(makeScoreboardFoodSold());		
		} else if (lists.equals("Food Spoiled")) {
			currentLabel = "Food Spoiled";
			display.setText(makeScoreboardFoodSpoiled());		
		} 
		
	}
}

