import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class BigLight extends Inventory{
	
	public BigLight(TheRoomXingwanying game,int px, int py){
		super(game,px, py);
		this.imgInv= Toolkit.getDefaultToolkit().getImage("images/fdd1.png");
		this.imgGet = Toolkit.getDefaultToolkit().getImage("images/lab2.png");
		this.imgUnUse = Toolkit.getDefaultToolkit().getImage("images/error2.png");
	}
	
	public void update(){
		if (this.isUse){
			game.hero.useFdd = true;
			game.hero.curState = 3;

			for( int i= 0; i < game.things.invs2.size(); i++){
				if( game.things.invs2.get(i).getClass() == BigLight.class){
					 game.things.invs2.remove(i);
				}
			}
		}if(this.isGet){
			px = 161;
			py = 60;
		}
		
	}
	public void draw(Graphics g){
		if(this.isGet == false && this.isUse == false){
			g.drawImage(imgInv,px,py,imgInv.getWidth(null),imgInv.getHeight(null),null);
		}
		else if(this.isGet == true && this.isView == true){
			g.drawImage(imgGet,0,0,1440,931,null);
		}else if(this.isClick == true && game.hero.useSgbfp == false){
			g.drawImage(imgUnUse,0,0,1440,931,null);
			
		}
		
	}
		
  
}
