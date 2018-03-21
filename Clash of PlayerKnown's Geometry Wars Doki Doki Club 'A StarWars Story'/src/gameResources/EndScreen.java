
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
//import gameResources.MainMenu.menuState;

public class EndScreen extends MainMenu implements Observer {
		private BufferedImage image;
		private BufferedImage background;
		private Rectangle playagain = new Rectangle(Game.WIDTH/3, 250, 266, 75);
		private Rectangle option = new Rectangle(Game.WIDTH/3, 350, 266, 75);
		private Rectangle quit = new Rectangle(Game.WIDTH/3, 450, 266, 75);
		private Game game;
		private enum menuState {
			PLAYAGAIN, OPTIONS, QUIT
		}
		private menuState mState;
		
		public EndScreen(Game game) {
			super(game);
			this.game = game;
			this.image = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
			this.mState = menuState.PLAYAGAIN;
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
			g.drawString("Match END ", Game.WIDTH/8,80);
			g.drawString("Player1: "+this.game.getGohv().getPlayers().get(0).getScore(), Game.WIDTH/8,160);
			g.drawString("Player2: "+this.game.getGohv().getPlayers().get(1).getScore(), Game.WIDTH-300,160);
			
			g2d.setColor(Color.WHITE);
			g2d.drawString("Play", playagain.x + 75,playagain.y + 50);
			g2d.drawString("Options", option.x + 50,option.y + 50);
			g2d.drawString("Quit", quit.x + 75,quit.y + 50);
			
			g2d.draw(playagain);
			g2d.draw(option);
			g2d.draw(quit);
			
			g2d.setColor(Color.YELLOW);
			if (this.mState == menuState.PLAYAGAIN) {
				g2d.draw(playagain);
				g2d.drawString("Play", playagain.x + 75,playagain.y + 50);
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
			System.out.println("Update");
			if(this.game.getState() == State.END) {
			int key = ((KeyEvent) e).getKeyCode();
			int keyAction = ((KeyEvent) e).getID();
			
			if (key == KeyEvent.VK_ENTER && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.PLAYAGAIN) {
				//this.game.getGohv().clear();
				//this.game.setGohv(new GameObjectHandlerView());
				//this.game.setMapMaker(new MapMaker(this.game.getGohv()));
		        //this.game.setMapReader(new MapReader(this.game.getMapMaker()));
		        //this.game.getMapReader().readDirectoryRandom("Maps");
				game.setState(Game.State.GAME);

			} else if (key == KeyEvent.VK_ENTER && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.QUIT) {
				System.exit(0);
			} else if (key == KeyEvent.VK_W && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAYAGAIN) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.PLAYAGAIN;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.OPTIONS;
				}
			} else if (key == KeyEvent.VK_S && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAYAGAIN) {
					this.mState = menuState.OPTIONS;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.PLAYAGAIN;
				}
			}
			
		}
		}
		@Override
		public void update(int key, int keyAction) {
			if (key == KeyEvent.VK_ENTER && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.PLAYAGAIN) {
				game.setState(Game.State.GAME);
			} else if (key == KeyEvent.VK_ENTER && keyAction == KeyEvent.KEY_PRESSED && this.mState == menuState.QUIT) {
				System.exit(0);
			} else if (key == KeyEvent.VK_W && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAYAGAIN) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.PLAYAGAIN;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.OPTIONS;
				}
			} else if (key == KeyEvent.VK_S && keyAction == KeyEvent.KEY_PRESSED) {
				if (this.mState == menuState.PLAYAGAIN) {
					this.mState = menuState.OPTIONS;
				} else if (this.mState == menuState.OPTIONS) {
					this.mState = menuState.QUIT;
				} else if (this.mState == menuState.QUIT) {
					this.mState = menuState.PLAYAGAIN;
				}
			}
		}
	}
