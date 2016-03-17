import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/*Ê±¹â»ú*/

public class TimeMachine extends Inventory{
	
	public TimeMachine(TheRoomXingwanying game,int px, int py) {
		super(game,px, py);
		this.imgInv= Toolkit.getDefaultToolkit().getImage("images/shiguanji1.png");
		this.imgGet = Toolkit.getDefaultToolkit().getImage("images/lab5.png");
		this.imgUnUse = Toolkit.getDefaultToolkit().getImage("images/error4.png");
		this.imgUnGet = Toolkit.getDefaultToolkit().getImage("images/error1.png");
	
	}
	
	
	public void update(){
		if(this.isGet){
			px = 465;
			py = 70;
		}
		if(this.isUse){
			game.isGameEnd = true;
			System.out.println(game.isGameEnd);
		}
	}
	public void draw(Graphics g){
		if(this.isGet == false && this.isUse == false){
				g.drawImage(imgInv,px,py,imgInv.getWidth(null),imgInv.getHeight(null),null);
		}
		else if(this.isGet == true && this.isView == true){
			g.drawImage(imgGet,0,0,1440,931,null);
		}else if(this.isClick == true && game.hero.curState !=3){
			g.drawImage(imgUnUse,0,0,1440,931,null);
		 
		}
		if(this.isMeet){
			g.drawImage(imgUnGet,0,0,1440,931,null);
			
		}
		
	}
}
