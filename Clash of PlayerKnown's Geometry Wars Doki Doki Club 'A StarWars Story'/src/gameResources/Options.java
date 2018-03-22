package gameResources;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Options implements Observer{

	private BufferedImage image;
	private BufferedImage background;
	private Game game;
	private Rectangle play = new Rectangle(Game.WIDTH/3, 450, 266, 75);
	private Rectangle rounds = new Rectangle(Game.WIDTH*3/5, 300, 50, 50);
	private enum optionState {
		PLAY, ROUNDS
	}
	private optionState mState;
	private boolean change = false;

	private String round = "";
	private Integer roundnum = 5;
	TextField txtInput = new TextField("");

	public Options(Game game) {
		this.game = game;
		this.image = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
		this.mState = optionState.ROUNDS;
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		try {
		    background = ImageIO.read(getClass().getResource("../spaceOP.jpg"));
		} catch (IOException e) {
			
		}
		g.drawImage(image,0,0, Game.WIDTH, Game.HEIGHT, game);
		g.drawImage(background,0,0, game);
		
		Font fnt0 = new Font("Comic Sans MS", Font.BOLD, 50);
		Font fnt1 = new Font("Comic Sans MS", Font.PLAIN, 40);

		g.setFont(fnt0);
		g.setColor(Color.YELLOW);
		g.drawString("OPTIONS ", Game.WIDTH/3,90);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Play", play.x + 75,play.y + 50);
		g.setFont(fnt1);
		
		g2d.draw(rounds);
		g2d.draw(play);
		
		g2d.setColor(Color.YELLOW);
		g.drawString("ROUNDS ", Game.WIDTH/3,345);
		g2d.setColor(Color.WHITE);
		
		
		if(this.mState == optionState.ROUNDS){
			
			if(roundnum > 9){
				
				rounds = new Rectangle(Game.WIDTH*3/5, 300, 75, 50);
			}
			if(roundnum == 69){
				try {
					background = ImageIO.read(getClass().getResource("../options.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(background,0,0, game);
			}
			round = roundnum.toString();
			g2d.drawString(round, rounds.x + 15, rounds.y + 40);
			
		}
		else if(this.mState == optionState.PLAY){
			
			g2d.setColor(Color.YELLOW);
			g2d.drawString("Play", play.x + 75,play.y + 50);
			g.setFont(fnt1);
			g2d.draw(play);
			g2d.setColor(Color.WHITE);
			g.drawString("ROUNDS ", Game.WIDTH/3,345);			
		}
		
		
	}

	@Override
	public void update(Observable o, Object e) {
		if (this.game.getState() == Game.State.OPTIONS) {
			int key = ((KeyEvent) e).getKeyCode();
			int keyAction = ((KeyEvent) e).getID();
			
			// TODO temporary, original stuff is commented below
			if (key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED) {
				//TODO change to VK_ENTER after p2 shoot problem fixed
				game.setState(Game.State.GAME);
			}
			
//			if (key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED && this.mState == optionState.PLAY) {
//				//TODO change to VK_ENTER after p2 shoot problem fixed
//				game.setState(Game.State.GAME);
//			}
			
			else if(key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED && this.mState == optionState.ROUNDS && this.change){
				//TODO change to VK_ENTER after p2 shoot problem fixed
				this.change = false;
				System.out.println(this.change);
			}
			else if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& keyAction == KeyEvent.KEY_PRESSED && this.mState == optionState.ROUNDS && !this.change) {
				roundnum = roundnum + 2;
			}
			else if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)&& keyAction == KeyEvent.KEY_PRESSED && this.mState == optionState.ROUNDS && !this.change) {
				if(roundnum > 1)
					roundnum = roundnum - 2;
				
			}
			else if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& keyAction == KeyEvent.KEY_PRESSED && !this.change) {
				this.mState = optionState.PLAY;
				System.out.println("VALERIE");
			}
		}
	}
	
}

