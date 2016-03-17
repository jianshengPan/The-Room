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
	TheRoomXingwanying game;   //反引用，存储主类对象
	Vector<Image> images;  //保存一系列开场动画图片
	int index;    //当前动画图片的序号
	int startTime;     //当前图片开始的时间
	int currTime;     //当前时间
	int life;        //图片可以存在的最长时间  
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
		//渲染第index张图片
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
		if( index < images.size()-1 ){ //如果后面还有图片
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
