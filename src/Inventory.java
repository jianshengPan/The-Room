import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



public class Inventory {
	TheRoomXingwanying game;
	Image imgInv; //初始道具图片
	Image imgGet;//捡到道具时的提示图片
	Image imgUnUse;//道具不可用提示图片
	Image imgUnGet ; //道具不可得提示图片
	int x,y;//鼠标
	int px,py;  //位置坐标
	Boolean isGet = false;//是否捡到道具
	Boolean isUse = false;//是否使用道具
	Boolean isView = false;//提示是否可见
	Boolean isClick = false;//提示是否点击
	Boolean isMeet = false;//提示是否可得
	public Inventory(TheRoomXingwanying game,int px,int py){
		this.game = game;
		this.px = px;
		this.py = py;
		
	}
	public void update(){
	
	} 
	public void draw(Graphics g){
		if(this.isGet == false && this.isUse == false){
			
			g.drawImage(imgInv,px,py,imgInv.getWidth(null),imgInv.getHeight(null),null);
		}
		else if(this.isGet == true && this.isView == true){
			g.drawImage(imgGet,0,0,1440,931,null);
		}
		
	}
		
	//播放声音
		void playSound(){
				try {
					AudioStream as = new AudioStream( 
							getClass().getResource("拾取声.wav").openStream());
					AudioPlayer.player.start(as);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			
			
		}
		public void mouseClicked(MouseEvent me){

		}
		


}