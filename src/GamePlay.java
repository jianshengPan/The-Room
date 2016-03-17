import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GamePlay implements GameState{
	TheRoomXingwanying game;
	Boolean isGameOver;
	Vector<Image> backgrounds;
	MyThings invs; //游戏道具栏
	Hero hero;      //游戏主角
	int index = 0;    //当前场景图片序号
    public GamePlay(TheRoomXingwanying game){
    	this.game = game;
    	backgrounds = new Vector<Image>();
    	Image i1 = 
    	Toolkit.getDefaultToolkit().getImage("images/9.jpg");
    	backgrounds.add(i1);
    	Image i2 = 
    	Toolkit.getDefaultToolkit().getImage("images/10.jpg");
    	backgrounds.add(i2);
    	//invs = new MyThings(game);
    //	hero = new Hero(this,game,invs,1000,800,64,76,0);
    	game.setFocusable(true);
    	
    	isGameOver = false;
    	
    }
	public void update() {
		
		hero.update();
		hero.collide();
		if(hero.changeScene){
			transactionState();
			hero.changeScene = false;
			
		}
		invs.update();
		
	}
	public void draw(Graphics g) {
		
		Image i = backgrounds.get(index%2);
		g.drawImage(i,0,0,null);
		invs.draw(g);
		hero.draw(g);
	}
	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();
		if( key == KeyEvent.VK_ESCAPE ){
			transactionState();
		}else{
			hero.keyPressed(key);	  
		}
	}
	public void mouseClicked(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		System.out.println("x="+x);
		System.out.println("y="+y);
		invs.mouseClicked(x, y);
		hero.mouseClicked(me);
	    game.repaint();
	}
	public void transactionState() {
		    if(isGameOver){
		    	GameEnd ge = new GameEnd(game);
				game.changeStateTo(ge);
		    }else{
		    	 index++;
		    }
			
	}
}
