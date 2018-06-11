import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {

	public Fenetre() {
		this.setTitle("Ma première fenêtre Java");
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,0);
		 //Instanciation d'un objet JPanel
	    JPanel pan = new JPanel();
	    //Définition de sa couleur de fond
	    pan.setBackground(Color.ORANGE);        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(pan);  
		this.setVisible(true);
	}

	public static void main(String[] args) {

		Fenetre fen = new Fenetre();

	}

}
