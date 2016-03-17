import java.awt.Graphics;
import java.awt.Toolkit;

/*Ê±¹â°ü¸¤*/

public class TimeFabric extends Inventory {

	public TimeFabric(TheRoomXingwanying game,int px, int py) {
		super(game,px, py);
		this.imgInv= Toolkit.getDefaultToolkit().getImage("images/shiguangbaofu1.png");
		this.imgGet = Toolkit.getDefaultToolkit().getImage("images/lab3.png");
		this.imgUnUse = Toolkit.getDefaultToolkit().getImage("images/error3.png");
	}
	public void update(){
		if(this.isGet){
			px = 268;
			py = 70;
		}
		if(this.isUse){
			game.hero.useSgbfp = true;
			for( int i= 0; i < game.things.invs1.size(); i++){
				if( game.things.invs1.get(i).getClass() == TimeFabric.class){
					 game.things.invs1.remove(i);
				}
			}
		}
	
	
	}
	public void draw(Graphics g){
		if(this.isGet == false && this.isUse == false){
				g.drawImage(imgInv,px,py,imgInv.getWidth(null),imgInv.getHeight(null),null);
		}
		else if(this.isGet == true && this.isView == true){
			g.drawImage(imgGet,0,0,1440,931,null);
		}else if(this.isClick == true && game.hero.getFdd == false){
			g.drawImage(imgUnUse,0,0,1440,931,null);
			
		}
		
	}

}
