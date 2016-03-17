import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



public class Inventory {
	TheRoomXingwanying game;
	Image imgInv; //��ʼ����ͼƬ
	Image imgGet;//�񵽵���ʱ����ʾͼƬ
	Image imgUnUse;//���߲�������ʾͼƬ
	Image imgUnGet ; //���߲��ɵ���ʾͼƬ
	int x,y;//���
	int px,py;  //λ������
	Boolean isGet = false;//�Ƿ�񵽵���
	Boolean isUse = false;//�Ƿ�ʹ�õ���
	Boolean isView = false;//��ʾ�Ƿ�ɼ�
	Boolean isClick = false;//��ʾ�Ƿ���
	Boolean isMeet = false;//��ʾ�Ƿ�ɵ�
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
		
	//��������
		void playSound(){
				try {
					AudioStream as = new AudioStream( 
							getClass().getResource("ʰȡ��.wav").openStream());
					AudioPlayer.player.start(as);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			
			
		}
		public void mouseClicked(MouseEvent me){

		}
		


}