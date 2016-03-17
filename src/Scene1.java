import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class Scene1 implements GameState {
	
	TheRoomXingwanying game;
	GamePlay play;
	Image image;
	ArrayList<Inventory> invs; //道具
	Boolean view = false;//提示框是否可见
	
	public Scene1(TheRoomXingwanying game){
		
		this.image = Toolkit.getDefaultToolkit().getImage("images/9.jpg");
		this.game = game;
		invs = game.things.invs1;
    	game.setFocusable(true);
    	
	}
	

	@Override
	public void update() {
		if(game.isGameEnd){
			transactionState();
		}
		game.hero.collide();
		
		
		//判断角色是否捡到道具
		for(int i = 0; i < invs.size(); i++ ){
		
			if( (((invs.get(i).px+7)> game.hero.px) && ((invs.get(i).px+7 )< (game.hero.px + game.hero.w)) &&
				((invs.get(i).py+20) > game.hero.py) && (invs.get(i).py+20 )< (game.hero.py + game.hero.h))) {
				
				
				
				if(invs.get(i).getClass() == TimeMachine.class){
					
					if(game.hero.useRyq){
				
						invs.get(i).isGet = true;
						invs.get(i).isView = true;
						invs.get(i).playSound();
					}else {
						invs.get(i).isGet = false;
						invs.get(i).isMeet = true;
					}
				}
			
				else{
					invs.get(i).isGet = true;
					invs.get(i).isView = true;
					invs.get(i).playSound();
				}
			}
		}
		
			
				
		if(game.hero.changeScene){
			transactionState();
			game.hero.changeScene = false;
			
		}
	
		game.things.update();
		game.hero.update();
		
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, 1440, 931, null);
		
		for( int i= 0; i < invs.size(); i++){
			((Inventory)invs.get(i)).draw(g);
			}
			
		game.things.draw(g);
		game.hero.draw(g);
		
	}

	@Override
	public void transactionState(){
		 if(game.isGameEnd){
		    	GameEnd ge = new GameEnd(game);
				game.changeStateTo(ge);
		    }else{
		    	game.hero.curScene = 1;
		    	 Scene2 scene = new Scene2(game);
		    	 game.changeStateTo(scene);
		    }
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		game.hero.keyPressed(key);	
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		for( int i= 0; i < invs.size(); i++){
			((Inventory)invs.get(i)).isView = false;
			((Inventory)invs.get(i)).isClick = false;
			((Inventory)invs.get(i)).isMeet = false;
		
			}
		int x = me.getX();
		int y = me.getY();
		System.out.println("x="+x);
		System.out.println("y="+y);
		game.things.mouseClicked(x, y);
		game.hero.mouseClicked(me);
	    game.repaint();
		
	}
	
	
}
	
