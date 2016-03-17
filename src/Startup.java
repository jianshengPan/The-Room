import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
/*
 * 游戏一开始就进入本状态，首先显示一张图片；
 * 过一段时间后，或者鼠标点击画面后，显示另外一张图片；
 * 直到图片现实完毕，进入下一个游戏状态：主菜单状态
 */
public class Startup implements GameState{
	TheRoomXingwanying game;   //反引用，存储主类对象
	Vector<Image> images;  //保存一系列开场动画图片
	int index;    //当前动画图片的序号
	int startTime;     //当前图片开始的时间
	int currTime;     //当前时间
	int life;        //图片可以存在的最长时间  
	public Startup(TheRoomXingwanying game){
		this.game = game;
		images = new Vector<Image>();
		Image i1 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/1.jpg");
		images.add(i1);
		Image i2 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/2.jpg");
		images.add(i2);
		Image i3 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/3.jpg");
		images.add(i3);
		Image i4 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/4.jpg");
		images.add(i4);
		Image i5 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/5.jpg");
		images.add(i5);
		Image i6 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/6.jpg");
		images.add(i6);
		Image i7 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/7.jpg");
		images.add(i7);
		Image i8 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/8.jpg");
		images.add(i8);
		Image i9 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/9.jpg");
		images.add(i9);
		Image i10 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/10.jpg");
		images.add(i10);
		Image i11 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/11.jpg");
		images.add(i11);
		Image i12 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/12.jpg");
		images.add(i12);
		Image i13 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/13.jpg");
		images.add(i13);
		Image i14 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/14.jpg");
		images.add(i14);
		Image i15 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/15.jpg");
		images.add(i15);
		Image i16 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/16.jpg");
		images.add(i16);
		Image i17 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/17.jpg");
		images.add(i17);
		Image i18 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/18.jpg");
		images.add(i18);
		Image i19 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/19.jpg");
		images.add(i19);
		Image i20 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/20.jpg");
		images.add(i20);
		Image i21 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/21.jpg");
		images.add(i21);
		Image i22 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/22.jpg");
		images.add(i22);
		Image i23 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/23.jpg");
		images.add(i23);
		Image i24 = 
		Toolkit.getDefaultToolkit().getImage("images/opening/24.jpg");
		images.add(i24);
		Image i25 = 
		Toolkit.getDefaultToolkit().getImage("images/4.jpg");
		images.add(i25);
		Image i26 = 
		Toolkit.getDefaultToolkit().getImage("images/5.jpg");
		images.add(i26);
		Image i27 = 
		Toolkit.getDefaultToolkit().getImage("images/6.jpg");
		images.add(i27);
		Image i28 = 
		Toolkit.getDefaultToolkit().getImage("images/7.jpg");
		images.add(i28);
		Image i29 = 
		Toolkit.getDefaultToolkit().getImage("images/sm.jpg");
		images.add(i29);
								
				
		
		playSound();
		
		index = 0;
		life = 5;
		
		startTime = 0;
		currTime = 0;
	}
	public void update() {
		if( currTime - startTime > life ){
			transactionState();			
		}else{
			currTime++;
		}
		if(index == 24){
			life = 400;
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
		}else{//如果动画图片已经显示完毕，则转向下一个游戏状态
			MainMenu mm = new MainMenu(game);
			game.changeStateTo(mm);
		}
	}
	void playSound(){
		try {
			AudioStream as = new AudioStream( 
				getClass().getResource("bgm.wav").openStream());
				AudioPlayer.player.start(as);
				if(game.isGameEnd){
				AudioPlayer.player.stop(as);
				}
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	
	}
	
	


}
