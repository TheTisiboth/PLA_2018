import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Fenetre {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createWindow();
			}
		});
	}

	public static void createWindow() {
		JFrame f = new JFrame();
		f.setTitle("ImageTest");
		f.setSize(800, 640);
		
		f.setLayout(new BorderLayout());
		
		
		Container c = new Container();
		f.add(c, BorderLayout.SOUTH);
		
		c.setLayout(new FlowLayout());
		c.add(new Label("Nom"));
		c.add(new TextField(20));

		
		ImageComponent component = new ImageComponent();
		f.add(component, BorderLayout.CENTER);

		//f.add(new Label("Nom du joueur 1 : "), BorderLayout.SOUTH);
		//f.add(new TextField(20), BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

}

class ImageComponent extends JComponent {

	private static final long	serialVersionUID	= 1L;
	private Image				image;

	public ImageComponent() {
		try {
			File image2 = new File("/home/vandalj/workshops/oop/workspace/PLA_2018/images/background.png");
			image = ImageIO.read(image2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
