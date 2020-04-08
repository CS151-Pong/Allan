package simStation;

import java.awt.Color;
import java.awt.Graphics;

import MVC.Model;
import MVC.View;

public class simView extends View {

	public simView(Model model) {
		super(model);
	}
	public void paintComponent(Graphics gc) {
		Agent agent=(Agent)model;
		Color oldColor=gc.getColor();
		gc.setColor(Color.RED);
		gc.fillOval(100, 100, 20, 20);// fills the circle to a new color then sets it back
		gc.setColor(oldColor);
	}

}
