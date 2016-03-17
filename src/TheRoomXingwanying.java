import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

@SuppressWarnings("serial") 
public class TheRoomXingwanying extends Applet implements Runnable,KeyListener,MouseListener{
	Image offScreen;   //后备缓冲区
	Graphics offScreenGraphics;  //后备缓冲上的绘图对象
	Thread newThread;  //动画线程
	GameState currentGameState;  //游戏的当前状态
	Hero hero;
	MyThings things;//道具栏
	Boolean isGameEnd =false;
	public void init(){		
		this.addKeyListener(this); //将自己注册为键盘事件监听者
		this.addMouseListener(this);//将自己注册为鼠标事件监听者
		this.setSize(1440,931);				
		currentGameState = new Startup(this);			
		this.setVisible(true);		
		newThread = new Thread(this);
		newThread.start();   //启动run()方法
		hero = new Hero(this,1000,800,64,76,0,0);
		things = new MyThings(this);
	}	
    public void run() {
		while( newThread != null ){
			currentGameState.update();
			try {
				Thread.sleep(24);
			}catch(InterruptedException e){e.printStackTrace();}
			repaint();
		}
	}
	public void paint(Graphics g){
		if( currentGameState == null )return;
		if( offScreen == null ){
			offScreen = createImage(getWidth(),getHeight());
			offScreenGraphics = offScreen.getGraphics();
		}
		offScreenGraphics.setColor(Color.white);
		offScreenGraphics.fillRect(0,0,1440,931);
		//将当前游戏状态渲染到游戏次画面上
		currentGameState.draw(offScreenGraphics);
		g.drawImage(offScreen,0,0,this); //将次画面渲染到主画面
	}
	public void update(Graphics g){
		paint(g);
	}	
	
	
	//三个键盘响应接口方法
	public void keyPressed(KeyEvent arg0) {
		//让当前游戏状态响应键盘事件
		currentGameState.keyPressed(arg0);
	}
	public void keyTyped(KeyEvent arg0) {}	
	public void keyReleased(KeyEvent arg0) {}
	//鼠标事件响应方法
	public void mouseClicked(MouseEvent arg0) {
		//让当前游戏状态响应鼠标事件
		currentGameState.mouseClicked(arg0);
	}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void changeStateTo(GameState nextState) {
		this.currentGameState = nextState;
		validate();
	}		
}
