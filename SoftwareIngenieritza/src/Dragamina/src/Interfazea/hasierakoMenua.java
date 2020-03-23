package Dragamina.src.Interfazea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class hasierakoMenua extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			hasierakoMenua dialog = new hasierakoMenua();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public hasierakoMenua() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Zer egin nahi duzu?");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 4;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JButton btnJolastu = new JButton("Jolastu");
				btnJolastu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						zailtasuna z = new zailtasuna();
						setVisible(false);
						z.setVisible(true);
					}
				});
				GridBagConstraints gbc_btnJolastu = new GridBagConstraints();
				gbc_btnJolastu.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnJolastu.insets = new Insets(0, 0, 5, 5);
				gbc_btnJolastu.gridx = 4;
				gbc_btnJolastu.gridy = 3;
				panel.add(btnJolastu, gbc_btnJolastu);
			}
			{
				JButton btnPuntuazioakIkusi = new JButton("Puntuazioak ikusi");
				GridBagConstraints gbc_btnPuntuazioakIkusi = new GridBagConstraints();
				gbc_btnPuntuazioakIkusi.insets = new Insets(0, 0, 0, 5);
				gbc_btnPuntuazioakIkusi.gridx = 4;
				gbc_btnPuntuazioakIkusi.gridy = 6;
				panel.add(btnPuntuazioakIkusi, gbc_btnPuntuazioakIkusi);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon("/home/jonander/Descargas/Untitled(1).png"));
				panel.add(label);
			}
		}
	}

}
