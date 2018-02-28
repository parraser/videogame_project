import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class MainMenu{
	
	public Rectangle play = new Rectangle(Game.WIDTH/3, 250, 266, 75);
	public Rectangle option = new Rectangle(Game.WIDTH/3, 350, 266, 75);
	public Rectangle quit = new Rectangle(Game.WIDTH/3, 450, 266, 75);
	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
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
	}
}
