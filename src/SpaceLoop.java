/*»Œ“‚»¶*/
import java.awt.Graphics;
import java.awt.Toolkit;


public class SpaceLoop extends Inventory{

	public SpaceLoop(TheRoomXingwanying game,int px, int py) {
		super(game, px, py);
		this.imgInv= Toolkit.getDefaultToolkit().getImage("images/renyiquan1.png");
		this.imgGet = Toolkit.getDefaultToolkit().getImage("images/lab4.png");
	}
	public void update(){
		if(this.isGet){
			px = 362;
			py = 65;
		}
		if(this.isUse){
			game.hero.useRyq = true;
			for( int i= 0; i < game.things.invs1.size(); i++){
				if( game.things.invs1.get(i).getClass() == SpaceLoop.class){
					 game.things.invs1.remove(i);
				}
			}
		}
	
	}
		

}
