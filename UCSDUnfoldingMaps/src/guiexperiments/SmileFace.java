package guiexperiments;

import processing.core.PApplet;

public class SmileFace  extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setup() {
		background(200,200,200);
		size(400,400);
	}
	
	public void draw() {
		fill(0,255,255);
		ellipse(width/2,height/2,width/4,height/5);
		
		//First Eye
		fill(0,0,0);
		ellipse((float) (width/2.3),height/2,width/30,height/20);
		
		//Second Eye
		fill(0,0,0);
		ellipse((float) (width/1.9),height/2,width/30,height/20);
		
		//Mouth
		fill(0,0,0);
		arc(width/2,(float) (height/1.8),width/10,10,PI, frameRate);
	}

}
