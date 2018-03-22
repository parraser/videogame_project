package gameResources;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import gameResources.Game.State;

public class MainMenu implements Observer {
	private BufferedImage image;
	private BufferedImage background;
	private Rectangle play = new Rectangle(Game.WIDTH/3, 250, 266, 75);
	private Rectangle option = new Rectangle(Game.WIDTH/3, 350, 266, 75);
	private Rectangle quit = new Rectangle(Game.WIDTH/3, 450, 266, 75);
	private Game game;
	private enum menuState {
		PLAY, OPTIONS, QUIT
	}
	private menuState mState;
	
	public MainMenu(Game game) {
		this.game = game;
		this.image = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
		this.mState = menuState.PLAY;
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		try {
		    background = ImageIO.read(getClass().getResource("../space.jpg"));
		} catch (IOException e) {
			
		}
		g.drawImage(image,0,0, Game.WIDTH, Game.HEIGHT, game);
		g.drawImage(background,0,0, game);
		
		Font fnt0 = new Font("Comic Sans MS", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.YELLOW);
		g.drawString("Clash of PlayerKnown's ", Game.WIDTH/8,80);
		g.drawString("Geometry Wars Doki Doki Club", Game.WIDTH/50, 130);
		g.drawString("'A StarWars Story'", Game.WIDTH/8, 180);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Play", play.x + 75,play.y + 50);
		g2d.drawString("Options", option.x + 50,option.y + 50);
		g2d.drawString("Quit", quit.x + 75,quit.y + 50);
		
		g2d.draw(play);
		g2d.draw(option);
		g2d.draw(quit);
		
		g2d.setColor(Color.YELLOW);
		if (this.mState == menuState.PLAY) {
			g2d.draw(play);
			g2d.drawString("Play", play.x + 75,play.y + 50);
		} else if (this.mState == menuState.OPTIONS) {
			g2d.draw(option);
			g2d.drawString("Options", option.x + 50,option.y + 50);
		} else {
			g2d.draw(quit);
			g2d.drawString("Quit", quit.x + 75,quit.y + 50);
		}
	}

	@Override
	public void update(Observable o, Object e) {
		if (this.game.getState() == Game.State.MAIN_MENU) {
			int key = ((KeyEvent) e).getKeyCode();
			int keyAction = ((KeyEvent) e).getID();
			
			//TODO change VK_BACK_SPACE to VK_ENTER after p2 shoot problem fixed
			if (key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.PLAY) {
				game.setState(Game.State.GAME);
			} else if (key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.QUIT) {
				System.exit(0);
			} else if (key == KeyEvent.VK_BACK_SPACE && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.OPTIONS) {
				game.setState(Game.State.OPTIONS);
			}else if (key == KeyEvent.VK_W && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAY) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.PLAY;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.OPTIONS;
				}
			} else if (key == KeyEvent.VK_S && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAY) {
					this.mState = menuState.OPTIONS;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.PLAY;
				}
			}
		}
	}
}
