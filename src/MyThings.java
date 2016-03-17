import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;


public class MyThings {
	Image zqt,fdd,sgbf,ryq,sgj;
	int px,py;//鼠标坐标
	TheRoomXingwanying game;
	Image image; //道具栏背景
	ArrayList<Inventory> invs1; //场景一道具
	ArrayList<Inventory> invs2;//场景二道具
	public MyThings(TheRoomXingwanying game){
		this.image = Toolkit.getDefaultToolkit().getImage("images/道具.png");	
		this.game = game;
	    invs1 = new ArrayList<Inventory>();
	    invs1.add(new TimeMachine(game,596,489)); 
		invs1.add(new TimeFabric(game,210,242)); 
		invs1.add(new SpaceLoop(game,807,64)); 	
	    invs2 = new ArrayList<Inventory>();
	    invs2.add(new BigLight(game,468,450));
		invs2.add(new BambooCopter(game,433,638)); 
		zqt = Toolkit.getDefaultToolkit().getImage("images/zqt.png");
		fdd = Toolkit.getDefaultToolkit().getImage("images/fdd.png");
		ryq = Toolkit.getDefaultToolkit().getImage("images/renyiquan.png");
		sgbf = Toolkit.getDefaultToolkit().getImage("images/shiguangbaofu.png");
		sgj = Toolkit.getDefaultToolkit().getImage("images/shiguanji.png");
	}
	public void update(){
		
		for( int i= 0; i < invs1.size(); i++){
			
				//判断是否使用道具
			if((((Inventory)invs1.get(i)).px <= this.px && ((Inventory)invs1.get(i)).px+118 >= this.px) &&
					(((Inventory)invs1.get(i)).py <= this.py && ((Inventory)invs1.get(i)).py+118 >= this.py)&&
					(((Inventory)invs1.get(i)).isGet )){
						((Inventory)invs1.get(i)).isClick = true;
						if( invs1.get(i).getClass() == SpaceLoop.class){
						{
							((Inventory)invs1.get(i)).isUse = true;
							
							}
					
						}else if(invs1.get(i).getClass() == TimeFabric.class){
							if(game.hero.getFdd ){
							((Inventory)invs1.get(i)).isUse = true;
							}
						
						}else if(invs1.get(i).getClass() == TimeMachine.class){
							if(game.hero.useFdd ){
							((Inventory)invs1.get(i)).isUse = true;
							
							}
						
						}
						
						
				}
			((Inventory)invs1.get(i)).update();
			
							
		}
		for(int i = 0; i <invs2.size();i++){

			if((((Inventory)invs2.get(i)).px <= this.px && ((Inventory)invs2.get(i)).px+118 >= this.px) &&
			(((Inventory)invs2.get(i)).py <= this.py && ((Inventory)invs2.get(i)).py+118 >= this.py) &&
			(((Inventory)invs2.get(i)).isGet )){
				((Inventory)invs2.get(i)).isClick = true;
				
				if( invs2.get(i).getClass() == BambooCopter.class){
		
					((Inventory)invs2.get(i)).isUse = true;
					game.hero.playSound();
					
				}
				else if(invs2.get(i).getClass() == BigLight.class){
					if(game.hero.useSgbfp){
						((Inventory)invs2.get(i)).isUse = true;
						game.hero.playSound();
			
						}
					
					
				}
				
				
				
			}
			
			((Inventory)invs2.get(i)).update();
		
		}
	
		
		game.repaint();
		px = 0;
		py = 0;
	}
	
	public void draw(Graphics g){
		g.drawImage(image,20,5,899,242,null);
		switch(game.hero.curScene){
		case 0:
			for( int i= 0; i < invs1.size(); i++){
				((Inventory)invs1.get(i)).draw(g);
			}
			
			break;
		case 1:
			for( int i= 0; i < invs2.size(); i++){
				((Inventory)invs2.get(i)).draw(g);
			}
			
			break;
		}
		for( int i= 0; i < invs1.size(); i++){
			if(((invs1.get(i)).getClass() == SpaceLoop.class) &&
					(((Inventory)invs1.get(i)).isGet == true) &&
					(((Inventory)invs1.get(i)).isUse == false)){
					g.drawImage(ryq,362,65,118,118,null);
				
				}
			if(((invs1.get(i)).getClass() == TimeFabric.class) &&
					(((Inventory)invs1.get(i)).isGet == true) &&
					(((Inventory)invs1.get(i)).isUse == false)){
					g.drawImage(sgbf,268,70,118,118,null);
				
				}
			if(((invs1.get(i)).getClass() == TimeMachine.class) &&
					(((Inventory)invs1.get(i)).isGet == true) &&
					(((Inventory)invs1.get(i)).isUse == false)){
					g.drawImage(sgj,465,70,118,118,null);
				
				}
			
			}
		for( int i= 0; i < invs2.size(); i++){
			if(((invs2.get(i)).getClass() == BambooCopter.class) &&
					(((Inventory)invs2.get(i)).isGet == true) &&
					(((Inventory)invs2.get(i)).isUse == false)){
					g.drawImage(zqt,68,65,118,118,null);
				
				}
			if(((invs2.get(i)).getClass() == BigLight.class) &&
					(((Inventory)invs2.get(i)).isGet == true) &&
					(((Inventory)invs2.get(i)).isUse == false)){
					g.drawImage(fdd,161,60,118,118,null);
				
				}
			
			}
			
		
	}
			
	
	public void mouseClicked(int x,int y) {
		this.px = x;
		this.py = y;
		
	}
}
