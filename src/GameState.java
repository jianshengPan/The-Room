import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public interface GameState {
	public void update();
	public void draw(Graphics g);
	public void transactionState();
	public void keyPressed(KeyEvent ke);
	public void mouseClicked(MouseEvent me);
}
