package packInterfazeak;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packEstructura.Tableroa;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Popup extends JDialog {
	
	private int zailtasuna;
	private int puntuazioa;
	private int i;
	private int j;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Popup dialog = new Popup(1,0,7,10);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Popup(int z, int p, int pI, int pJ) {
		
		this.zailtasuna = z;
		this.puntuazioa = p;
		this.i = pI;
		this.j = pJ;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gbl_panel);
			{
				JLabel lblZerEginNahi = new JLabel("Zer egin nahi duzu?");
				GridBagConstraints gbc_lblZerEginNahi = new GridBagConstraints();
				gbc_lblZerEginNahi.insets = new Insets(0, 0, 5, 0);
				gbc_lblZerEginNahi.gridx = 7;
				gbc_lblZerEginNahi.gridy = 2;
				panel.add(lblZerEginNahi, gbc_lblZerEginNahi);
			}
			{
				JButton btnBestePartidaBat = new JButton("Beste partida bat jokatu");
				btnBestePartidaBat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TableroaI t = new TableroaI(i,j);
						t.setVisible(true);
						setVisible(false);
					}
				});
				GridBagConstraints gbc_btnBestePartidaBat = new GridBagConstraints();
				gbc_btnBestePartidaBat.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBestePartidaBat.insets = new Insets(0, 0, 5, 0);
				gbc_btnBestePartidaBat.gridx = 7;
				gbc_btnBestePartidaBat.gridy = 3;
				panel.add(btnBestePartidaBat, gbc_btnBestePartidaBat);
			}
			{
				JButton btnPuntuazioakIkusi = new JButton("Puntuazioak ikusi");
				btnPuntuazioakIkusi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Puntuazioak p = new Puntuazioak();
						p.setVisible(true);
						setVisible(false);
					}
				});
				GridBagConstraints gbc_btnPuntuazioakIkusi = new GridBagConstraints();
				gbc_btnPuntuazioakIkusi.insets = new Insets(0, 0, 5, 0);
				gbc_btnPuntuazioakIkusi.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnPuntuazioakIkusi.gridx = 7;
				gbc_btnPuntuazioakIkusi.gridy = 4;
				panel.add(btnPuntuazioakIkusi, gbc_btnPuntuazioakIkusi);
			}
			{
				JButton btnBukatu = new JButton("Bukatu");
				btnBukatu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						hasierakoMenua hm = new hasierakoMenua();
						setVisible(false);
						hm.setVisible(true);
					}
				});
				GridBagConstraints gbc_btnBukatu = new GridBagConstraints();
				gbc_btnBukatu.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBukatu.gridx = 7;
				gbc_btnBukatu.gridy = 5;
				panel.add(btnBukatu, gbc_btnBukatu);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblZurePuntuazioaHurrengoa = new JLabel("Zure puntuazioa hurrengoa izan da: "+puntuazioa);
				panel.add(lblZurePuntuazioaHurrengoa);
			}
		}
	}

}
