import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameEnd implements GameState{
	TheRoomXingwanying game;   //�����ã��洢�������
	Vector<Image> images;  //����һϵ�п�������ͼƬ
	int index;    //��ǰ����ͼƬ�����
	int startTime;     //��ǰͼƬ��ʼ��ʱ��
	int currTime;     //��ǰʱ��
	int life;        //ͼƬ���Դ��ڵ��ʱ��  
	public GameEnd(TheRoomXingwanying game){
		this.game = game;
		images = new Vector<Image>();
		Image i1 = 
		Toolkit.getDefaultToolkit().getImage("images/11.jpg");
		images.add(i1);
		Image i2 = 
		Toolkit.getDefaultToolkit().getImage("images/gameover.jpg");
		images.add(i2);
		
		
		index = 0;
		life = 400;
		
		startTime = 0;
		currTime = 0;
	}
	public void update() {
		if( currTime - startTime > life ){
			transactionState();			
		}else{
			currTime++;
		}
	}
	public void draw(Graphics g) {
		//��Ⱦ��index��ͼƬ
		Image i = images.get(index);
		g.drawImage(i,0,0,null);
	}
	public void keyPressed(KeyEvent ke) {
		transactionState();
	}
	public void mouseClicked(MouseEvent me) {	
		transactionState();
	}
	public void transactionState(){
		if( index < images.size()-1 ){ //������滹��ͼƬ
			index++;
			startTime = currTime;
		}else{
			System.exit(0);
		}
	}
	void playSound(){
		try {
			AudioStream as = new AudioStream( 
				getClass().getResource("bgm.wav").openStream());
				AudioPlayer.player.start(as);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	
	}
	
	


}
