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
	Image offScreen;   //�󱸻�����
	Graphics offScreenGraphics;  //�󱸻����ϵĻ�ͼ����
	Thread newThread;  //�����߳�
	GameState currentGameState;  //��Ϸ�ĵ�ǰ״̬
	Hero hero;
	MyThings things;//������
	Boolean isGameEnd =false;
	public void init(){		
		this.addKeyListener(this); //���Լ�ע��Ϊ�����¼�������
		this.addMouseListener(this);//���Լ�ע��Ϊ����¼�������
		this.setSize(1440,931);				
		currentGameState = new Startup(this);			
		this.setVisible(true);		
		newThread = new Thread(this);
		newThread.start();   //����run()����
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
		//����ǰ��Ϸ״̬��Ⱦ����Ϸ�λ�����
		currentGameState.draw(offScreenGraphics);
		g.drawImage(offScreen,0,0,this); //���λ�����Ⱦ��������
	}
	public void update(Graphics g){
		paint(g);
	}	
	
	
	//����������Ӧ�ӿڷ���
	public void keyPressed(KeyEvent arg0) {
		//�õ�ǰ��Ϸ״̬��Ӧ�����¼�
		currentGameState.keyPressed(arg0);
	}
	public void keyTyped(KeyEvent arg0) {}	
	public void keyReleased(KeyEvent arg0) {}
	//����¼���Ӧ����
	public void mouseClicked(MouseEvent arg0) {
		//�õ�ǰ��Ϸ״̬��Ӧ����¼�
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
