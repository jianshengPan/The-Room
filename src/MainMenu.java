import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public class MainMenu extends Panel implements GameState,ActionListener{
    TheRoomXingwanying game;
    Image background; //±³¾°Í¼
    public MainMenu(TheRoomXingwanying game){
    	this.game = game;
    	game.add(this);
    	background = Toolkit.getDefaultToolkit().getImage("images/mainMenu.jpg");
    }
	public void update(){
	}
	public void draw(Graphics g) {
		g.drawImage(background,0,0,null);
	}
	public void keyPressed(KeyEvent ke) {
	}
	public void mouseClicked(MouseEvent me){	
		transactionState();
	}
	
	
	public void transactionState() {
		
		Scene1 scene = new Scene1(game);
		game.changeStateTo(scene);		
		game.removeAll();		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}



