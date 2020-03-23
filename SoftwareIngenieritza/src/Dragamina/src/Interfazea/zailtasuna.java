package  Dragamina.src.Interfazea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class zailtasuna extends JDialog {
	private JLabel lblSartuZureIzena;
	private JTextField textField;
	private JLabel lblZailtasunMailaHautatu;
	private JSlider slider;
	private JLabel label;
	private JPanel panel_1;
	private JButton btnHasi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			zailtasuna dialog = new zailtasuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public zailtasuna() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_lblSartuZureIzena = new GridBagConstraints();
			gbc_lblSartuZureIzena.insets = new Insets(0, 0, 5, 0);
			gbc_lblSartuZureIzena.gridx = 7;
			gbc_lblSartuZureIzena.gridy = 0;
			panel.add(getLblSartuZureIzena(), gbc_lblSartuZureIzena);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 6;
			gbc_label.gridy = 2;
			panel.add(getLabel(), gbc_label);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 7;
			gbc_textField.gridy = 2;
			panel.add(getTextField(), gbc_textField);
			GridBagConstraints gbc_lblZailtasunMailaHautatu = new GridBagConstraints();
			gbc_lblZailtasunMailaHautatu.insets = new Insets(0, 0, 5, 0);
			gbc_lblZailtasunMailaHautatu.gridx = 7;
			gbc_lblZailtasunMailaHautatu.gridy = 5;
			panel.add(getLblZailtasunMailaHautatu(), gbc_lblZailtasunMailaHautatu);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.gridx = 7;
			gbc_slider.gridy = 7;
			panel.add(getSlider(), gbc_slider);
		}
		getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
	}

	private JLabel getLblSartuZureIzena() {
		if (lblSartuZureIzena == null) {
			lblSartuZureIzena = new JLabel("Izena");
		}
		return lblSartuZureIzena;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblZailtasunMailaHautatu() {
		if (lblZailtasunMailaHautatu == null) {
			lblZailtasunMailaHautatu = new JLabel("Zailtasuna");
		}
		return lblZailtasunMailaHautatu;
	}
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.setMajorTickSpacing(1);
			slider.setMaximum(3);
			slider.setMinimum(1);
			slider.setSnapToTicks(true);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
		}
		return slider;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
		}
		return label;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getBtnHasi());
		}
		return panel_1;
	}
	private JButton getBtnHasi() {
		if (btnHasi == null) {
			btnHasi = new JButton("Hasi");
			btnHasi.addActionListener(new BtnHasiActionListener());
		}
		return btnHasi;
	}
	private class BtnHasiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(getSlider().getValue()==1){ //7x10
				TableroaI tableroI = new TableroaI(7,10);
				setVisible(false);
				tableroI.setVisible(true);
			}
			else if(getSlider().getValue()==2){//10x15
				TableroaI tableroI = new TableroaI(10,15);
				setVisible(false);
				tableroI.setVisible(true);
			}
			else{//12x25
				TableroaI tableroI = new TableroaI(12,25);
				setVisible(false);
				tableroI.setVisible(true);
			}
		}
	}
}

