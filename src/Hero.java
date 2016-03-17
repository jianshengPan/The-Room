import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

//Ӣ���ࣺ���н�ɫ��������
public class Hero{
	int curState;
	public static int State_small1 = 0;
	public static int State_samll2 = 1;
	public static int State_Fly = 2;	
	public static int State_Big = 3;
	GamePlay play;
    MyThings things;
	TheRoomXingwanying game;
	int curDirection;    //��Ϸ��ɫ�ĵ�ǰ�����ţ�0�������ϣ�1�������ϣ�2�������£�3��������
	int curFrame;        //��Ϸ��ɫ�����ĵ�ǰ����֡�ı��	
	int curFrameNum;     //��ǰ����֡�Ѿ������˶�����Ϸ֡,һ��whileѭ��������Ϸ֡
	boolean moving;      //�жϵ�ǰӢ���Ƿ����˶�״̬
	int curMoveTime;     //��ǰ�Ѿ��˶�����Ϸ����ʱ��
	int moveTime = 80;   //�˶�������ʱ��
	String stepSound = "��·��.wav";
	int px,py;
	int w,h;
	int v;
	Boolean changeScene; //�Ƿ���Ҫ�л�����
	Boolean useRyq = false,useSgbfp = false,useFdd = false,useZqt = false;//�Ƿ�ʹ������Ȧ��ʱ�����Ƥ,�Ŵ�ƣ�������
	Boolean getFdd = false;//�Ƿ�õ��Ŵ��
	Vector<Image> images;  //����һϵ�н�ɫͼƬ
	int curScene = 0; //��ǰ��������
	
	
	public Hero(TheRoomXingwanying game, int cx, int cy, int w, int h,int v,int curState) {

		this.game = game;
    	this.px = cx;
    	this.py = cy;
    	this.w = w;
    	this.h = h;
    	this.v = v;
    	this.curState = curState;
        images = new Vector<Image>();
		Image i0 = 
		Toolkit.getDefaultToolkit().getImage("images/����.png");
		images.add(i0);
		Image i1 = 
		Toolkit.getDefaultToolkit().getImage("images/����.png");
		images.add(i1);
		Image i2 = 
		Toolkit.getDefaultToolkit().getImage("images/����fly.png");
		images.add(i2);
		Image i3 = 
		Toolkit.getDefaultToolkit().getImage("images/�����.png");
		images.add(i3);
     
     	this.changeScene = false;
     	
		
	}
	//״̬���·�������Ϸ��ÿһ֡�ᱻ����
    public void update(){
    	 //���н�ɫ����
    	
    	if(!changeScene){
    		if(useFdd){
    			curState = 3;
    		}else if(useZqt && !useFdd){
    			curState = 2;
    		}else{
    			curState = curScene;
    		}
    		
    	}else{
    		if(useFdd){
    			curState = 3;
    		}else if(useZqt && !useFdd){
    			curState = 2;
    		}
    	}
    	
    	  	
    	if(moving ){
    		
    		animate();
    		
        	if( curDirection == 0 ){
        		px -= v;
        		py -= v;
        		
        	}else if( curDirection == 1 ){
        		px += v;
        		py -= v;
        	}else if( curDirection == 2 ){
        		px += v;
        		py += v;
        	}else if( curDirection == 3 ){
        		px -= v;
        		py += v;
        	}	
        	curMoveTime ++;
        	if(curMoveTime > moveTime ){   //�˶�ʱ�����
        		moving = false;
        		curMoveTime = 0;
        	}
    	}

    	 
    }

	public void collide(){
		switch(curState){
		case 0:         //����1���С����
			//�봰�ڵ���ײ���
	    	if( px <= 200 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}//��
	        
	    	else if( py <= 670  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}//��
	    	else if( px >=1200 && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}//��
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
	    	   this.v = 0;
	    	   changeScene = true;	    	
	    	   this.px = 500;
	    	   this.py = 800;
	    		}//��
	    	
			break;
		case 1:         //����2���С����
			if( px <= 260 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}//��
	        
	    	else if( py <= 620  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}//��
	    	else if( px >=1050 && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}//��
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
	    	   this.v = 0;
	    	   changeScene = true;
	    	   this.px = 1000;
	    	   this.py = 800;
	    		}//��
			break;
			
		case 2:         //�������ѵ�С����
			if( px <= 0 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}
	        
	    	else if( py <= 0  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}
	    	else if( px >= game.getWidth() - this.w && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
		    	   this.v = 0;
		    	   changeScene = true;
		    	   this.px = 1000;
		    	   this.py = 800;
		    		}//��
			break;
			
		case 3:     //���Ĵ���
			if( px <= 0 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}
	        
	    	else if( py <= 0  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}
	    	else if( px >= game.getWidth() - this.w && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
		    	   this.v = 0;
		    	   changeScene = true;
		    	   this.px = 1000;
		    	   this.py = 800;
		    		}//��
			break;
		}
		
	}
	
		
	    
    //��ɫ����
	protected void animate(){
		if(!moving) return; 
		curFrameNum++;
    	if( curFrameNum > 4 ){    //�û�����һ����֡��
    		curFrame = (curFrame+1)%4;
    		curFrameNum = 0;
    	}  
	}
	//���ܼ��̿���
	public void keyPressed(int key) {
		if( key == KeyEvent.VK_UP ){    //�������˶�
			curDirection = 0;		
		}else if( key == KeyEvent.VK_RIGHT){  //�������˶�
			curDirection = 1;
		}else if( key == KeyEvent.VK_DOWN ){  //�������˶�
			curDirection = 2;
		}else if( key == KeyEvent.VK_LEFT ){  //�������˶�
			curDirection = 3;	
		}
		moving = true;
		this.v = 1;
	    curMoveTime = 0;
	    playSound(stepSound);
		
	}
	//������Ϸ��ɫ�ĵ�ǰ�����ź͵�ǰ����֡�ı����Ⱦ��ɫ����
	public void draw(Graphics g){
		Image i = images.get(curState);	
		this.w = i.getWidth(null)/4;
		this.h = i.getHeight(null)/4;
		g.drawImage( i,
			     px,py,px+w,py+h,   //ͼƬ�ڴ����ϵĴ�С
			     curFrame*w,curDirection*h,curFrame*w+w,curDirection*h+h,
			     null);
	
		
    }  
	//��������
	void playSound(String sound){
		switch(curState){
		case 0:
		case 1:
			try {
				AudioStream as = new AudioStream( 
						getClass().getResource(sound).openStream());
				AudioPlayer.player.start(as);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
			break;
		case 2:
		case 3:
			try {
				AudioStream as = new AudioStream( 
						getClass().getResource("fly.wav").openStream());
				AudioPlayer.player.start(as);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
			break;
			
		}
			
	}
	//��������
			void playSound(){
					try {
						AudioStream as = new AudioStream( 
								getClass().getResource("change.wav").openStream());
						AudioPlayer.player.start(as);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    
				
				
			}
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
}
