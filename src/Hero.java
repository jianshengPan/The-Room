import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

//英雄类：具有角色动画特征
public class Hero{
	int curState;
	public static int State_small1 = 0;
	public static int State_samll2 = 1;
	public static int State_Fly = 2;	
	public static int State_Big = 3;
	GamePlay play;
    MyThings things;
	TheRoomXingwanying game;
	int curDirection;    //游戏角色的当前方向编号：0代表左上，1代表右上，2代表右下，3代表左下
	int curFrame;        //游戏角色动画的当前动画帧的编号	
	int curFrameNum;     //当前动画帧已经持续了多少游戏帧,一个while循环就是游戏帧
	boolean moving;      //判断当前英雄是否处于运动状态
	int curMoveTime;     //当前已经运动的游戏仿真时间
	int moveTime = 80;   //运动持续的时间
	String stepSound = "走路声.wav";
	int px,py;
	int w,h;
	int v;
	Boolean changeScene; //是否需要切换场景
	Boolean useRyq = false,useSgbfp = false,useFdd = false,useZqt = false;//是否使用任意圈，时光包袱皮,放大灯，竹蜻蜓
	Boolean getFdd = false;//是否得到放大灯
	Vector<Image> images;  //保存一系列角色图片
	int curScene = 0; //当前所处场景
	
	
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
		Toolkit.getDefaultToolkit().getImage("images/大雄.png");
		images.add(i0);
		Image i1 = 
		Toolkit.getDefaultToolkit().getImage("images/大雄.png");
		images.add(i1);
		Image i2 = 
		Toolkit.getDefaultToolkit().getImage("images/大雄fly.png");
		images.add(i2);
		Image i3 = 
		Toolkit.getDefaultToolkit().getImage("images/大大雄.png");
		images.add(i3);
     
     	this.changeScene = false;
     	
		
	}
	//状态更新方法，游戏的每一帧会被调用
    public void update(){
    	 //进行角色动画
    	
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
        	if(curMoveTime > moveTime ){   //运动时间结束
        		moving = false;
        		curMoveTime = 0;
        	}
    	}

    	 
    }

	public void collide(){
		switch(curState){
		case 0:         //房间1里的小大雄
			//与窗口的碰撞检测
	    	if( px <= 200 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}//左
	        
	    	else if( py <= 670  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}//上
	    	else if( px >=1200 && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}//右
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
	    	   this.v = 0;
	    	   changeScene = true;	    	
	    	   this.px = 500;
	    	   this.py = 800;
	    		}//下
	    	
			break;
		case 1:         //房间2里的小大雄
			if( px <= 260 && (curDirection == 0 ||curDirection == 3)){
	    		this.v = 0;
			}//左
	        
	    	else if( py <= 620  && (curDirection == 0 ||curDirection == 1)){
		      this.v = 0;
			}//上
	    	else if( px >=1050 && (curDirection == 1 ||curDirection == 2)){
	  	      this.v = 0;
	  		}//右
	    	else if( py >=game.getHeight() - this.h && (curDirection == 3 ||curDirection == 2)){
	    	   this.v = 0;
	    	   changeScene = true;
	    	   this.px = 1000;
	    	   this.py = 800;
	    		}//下
			break;
			
		case 2:         //有竹蜻蜓的小大雄
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
		    		}//下
			break;
			
		case 3:     //变大的大雄
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
		    		}//下
			break;
		}
		
	}
	
		
	    
    //角色动画
	protected void animate(){
		if(!moving) return; 
		curFrameNum++;
    	if( curFrameNum > 4 ){    //该换到下一动画帧了
    		curFrame = (curFrame+1)%4;
    		curFrameNum = 0;
    	}  
	}
	//接受键盘控制
	public void keyPressed(int key) {
		if( key == KeyEvent.VK_UP ){    //朝左上运动
			curDirection = 0;		
		}else if( key == KeyEvent.VK_RIGHT){  //朝右上运动
			curDirection = 1;
		}else if( key == KeyEvent.VK_DOWN ){  //朝右下运动
			curDirection = 2;
		}else if( key == KeyEvent.VK_LEFT ){  //朝左下运动
			curDirection = 3;	
		}
		moving = true;
		this.v = 1;
	    curMoveTime = 0;
	    playSound(stepSound);
		
	}
	//根据游戏角色的当前方向编号和当前动画帧的编号渲染角色动画
	public void draw(Graphics g){
		Image i = images.get(curState);	
		this.w = i.getWidth(null)/4;
		this.h = i.getHeight(null)/4;
		g.drawImage( i,
			     px,py,px+w,py+h,   //图片在窗口上的大小
			     curFrame*w,curDirection*h,curFrame*w+w,curDirection*h+h,
			     null);
	
		
    }  
	//播放声音
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
	//播放声音
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
