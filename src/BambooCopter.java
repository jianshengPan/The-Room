/*÷ÒÚﬂÚ—*/

import java.awt.Graphics;
import java.awt.Toolkit;


public class BambooCopter extends Inventory{
	
	public BambooCopter(TheRoomXingwanying game,int px, int py ){
		super(game, px, py);
		this.imgInv = Toolkit.getDefaultToolkit().getImage("images/zqt1.png");
		this.imgGet = Toolkit.getDefaultToolkit().getImage("images/lab1.png");
		
	}
	public void update(){
		if(this.isUse){
			game.hero.useZqt = true;
			game.hero.curState = 2;  
			
			for( int i= 0; i < game.things.invs2.size(); i++){
				if( game.things.invs2.get(i).getClass() == BambooCopter.class){
					 game.things.invs2.remove(i);
				}
			}
		}
	 if(this.isGet){
			px = 68;
			py = 65;
		}
		
	}
	
	

}
