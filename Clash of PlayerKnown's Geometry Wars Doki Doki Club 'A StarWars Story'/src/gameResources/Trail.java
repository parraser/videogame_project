package gameResources;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	/* BloodTrail */
	
	/*
                                                    
                     .#@@@@@@@@@@@@@@@@@@@@@@@@@#                  
               `+@@@@@@@@+++'''''''''''''''''+#@@@@@               
            @@@@@@@+''+'''''''''''''''''''''''''''@@'              
          @@@@#'''''#  ''''''''''''''''''''''''''''@@              
         :@#'''''''''   '''''''''''''''''''''''''''@@              
         +@''''''''#`; `#''''''''''''''''''''''''''@@              
         ;@''''''''+ '  +''''''''''''''''''''''''''#@              
          @'''''''''''##'''''''''''''''''#@@@'''''''@              
          @'''''''+'''''''''''''''''''''@@@@@@@'''''@              
          @'''''@@@@@@'''''''''''''''''@@  @@@@'''''@  @@@@        
          @''''+@  @@@@''''''''''''''''@  @@@@@#''''@ +@+#@@       
          @''''@@ @@@@@''''''''''''''''@@@@@@@@@''''@ @'''+@       
          @#'''@@@@@@@@''''''''''''''''@@@@@@@@@''''@ @'''+@       
          @#'''@@@@+'+'#'''''''''''''''@@@@@@;@'''''@ @'''+@@@@    
          @@'''+@''#+''+'''''''''''''''+@@@@@@@'''''@@@''''''+@.   
          ##'''#'+'''+'''''''''''''''''''''''''''''+''''''''''@@   
          #@'''@'''''''''''''''''''''''''''''''''''''#''''''''@'   
          @#''''+#@@@@@@@@@@@@@@@@@@@@@@@@@@@@#+'''''''''#'''+@@   
          @'''''+@@``` @  @@@@  `;  @` ` , `@@@''''''''''+'''''@   
         @@'''''#@@:,     :@@+             .@@@''''''''''+'''''@   
        `@'''''''@@@#, #  @@@@         . @+@@@@'''''''''''''''@@   
        @@'''''''#@`;  #               ; `  @@''''''''''+'''''@    
       .@'''''''''+@`                       @'''''''''''''''''@    
       @@'''''''''''@@.`               .   @'''''''''''''''@@@@    
      .@''''''''''''''@@@;. @ ,` #  # ':.@#''''''''''''''@@;.,     
      @@''''+''''''''''''#@@@:....:..'@@#'''''''''''''+@@,         
      @#''''+'''''''''''''''''+##+++''''''''''''''+'#@@`           
      @@''''@''''''''''''''''''#@@@@''''''''''''''''@:             
       @''''@'''''''''''''''''''''''''''''''''''''''@              
       @@'''@'''''''''''''''''''''''''''''''''''''''@              
        +@''@'''''''''''''''''''''''''''''''''''''''@              
         @@+@'''''+'''''''''''''''''''''''''''''''''@              
          ':@''''+''''''''''''''''''''''''''''''''''@              
            @'''''''''''''''''''''''''''''''''''''''@              
            @''''''''''''''''''''''''''#+'''''''''''@              
            @'''''''''''''#@@@@@@@@@@@'''''''''''''+@              
            @''''''''''''''@@       +@'''''''''''''+@              
            @''''''''''''''@@       #@'''''''''''''+@              
            @+'''''''''''''@@       #@'''''''''''''+@              
            @@''''''''''''#@         @'''''''''''''@@              
             @@@@@@#@@@@@@@,         @@@@+++'+#@@@@+               
                :'##@#',               :@@@@@@@@:                  
	*/
	private float alpha;
	//
	public final static float MAX_LIFE = 0.5f;
	private float timeLeft;
	private Color color;
	
	public Trail(int x, int y, Color c) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = Player.WIDTH;
		this.height = Player.HEIGHT;
		this.alpha = MAX_LIFE;
		this.color = c;
	}
	@Override
	public void tick() {
		this.alpha -= 0.05f;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(this.alpha < 0){
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
		g.setColor(this.color);
		g.fillRect(this.x, this.y, width, height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle(this.x, this.y, this.height, this.width);
	}
	public boolean isDead(){
		if(this.alpha>this.timeLeft){
			return false;
		}
		return true;
	}
	
}
