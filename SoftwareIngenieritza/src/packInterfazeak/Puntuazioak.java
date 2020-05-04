package packInterfazeak;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import packEstruktura.JokalariKatalogo;
import packEstruktura.Jokalaria;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Puntuazioak extends JDialog {
	private JTextArea textArea;
	private JPanel panel;
	private JButton btnAtzeraEgin;
	private JLabel lblHamarHoberenakHurrengoak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Puntuazioak dialog = new Puntuazioak();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Puntuazioak() {

		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getTextArea(), BorderLayout.CENTER);
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getLblHamarHoberenakHurrengoak(), BorderLayout.NORTH);
		setTitle("Dragamina. Puntuazioak");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TableroaI.class.getResource("/packInterfazeak/unnamed.jpg")));
		
		//JokalariKatalogo.getJokalariKatalogo().datuakKargatu(); //Badaezpada berriz kargatu
		Iterator<Jokalaria> itr = JokalariKatalogo.getJokalariKatalogo().jokalariakLortu();
		while(itr.hasNext()){
			Jokalaria j = itr.next();
			this.textArea.append(j.getPuntuazioa() + " " + j.getIzena() + '\n');
		}
		
	
		
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnAtzeraEgin());
		}
		return panel;
	}
	private JButton getBtnAtzeraEgin() {
		if (btnAtzeraEgin == null) {
			btnAtzeraEgin = new JButton("Atzera egin");
			btnAtzeraEgin.addActionListener(new BtnAtzeraEginActionListener());
		}
		return btnAtzeraEgin;
	}
	private JLabel getLblHamarHoberenakHurrengoak() {
		if (lblHamarHoberenakHurrengoak == null) {
			lblHamarHoberenakHurrengoak = new JLabel("HAMAR HOBERENAK HURRENGOAK DIRA:");
			lblHamarHoberenakHurrengoak.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHamarHoberenakHurrengoak;
	}
	private class BtnAtzeraEginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			hasierakoMenua hm = new hasierakoMenua();
			hm.setVisible(true);
			setVisible(false);
		}
	}
}
