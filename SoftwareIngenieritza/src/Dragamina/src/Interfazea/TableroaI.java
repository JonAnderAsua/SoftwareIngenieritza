package Dragamina.src.Interfazea;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import Dragamina.src.packdragamina.Gelaxka;
import  Dragamina.src.packdragamina.Tableroa;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class TableroaI extends JDialog{
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblT;
	private JLabel lblT_1;
	private JLabel lblCarita;
	private JLabel lblT_2;
	private JLabel lblT_3;
	private JLabel lblT_4;
	private int zailtasuna;
	private JMenuBar menuBar;
	private JMenu mnZailtasuna;
	private JMenu mnAtzera;
	private JMenuItem mntmErraza;
	private JMenuItem mntmErtaina;
	private JMenuItem mntmZaila;
	private JMenuItem mntmPartidaBerria;
	private boolean lehenengoa = true;
	private int ler;
	private int zut;
	private Gelaxka[][] tablero;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroaI dialog = new TableroaI(10,15);
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
	public TableroaI(int nLerro, int nZutabe) {
		this.tablero = new Gelaxka[nLerro][nZutabe];
		ler=nLerro;
		zut=nZutabe;
		if(nLerro==7){
			setSize(206,294);
            zailtasuna=1;
        }
        else if(nLerro==10){
        	setSize(406,495);  
            zailtasuna=2;
        }
        else{
        	setSize(606,695);
            zailtasuna=3;
        }
		initialize(nLerro,nZutabe);
	}
	private void initialize(int nLerro, int nZutabe) {
		setTitle("Dragamina");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TableroaI.class.getResource("/Interfazea/unnamed.jpg")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		this.sortuGelaxkak(nLerro,nZutabe);
		getContentPane().add(getPanel_1(), BorderLayout.NORTH);
		setJMenuBar(getMenuBar());
	}

	//Gelaxken panela
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());
		}
		return panel;
	}
	
	private JLabel gelaxkaSortu(int i, int j) throws IOException {

        Gelaxka label = new Gelaxka(i,j,zailtasuna);
        this.tablero[i][j] = label;
        label.setBorder(BorderFactory.createLoweredBevelBorder());
        Image img = ImageIO.read(getClass().getResource("tablero.gif"));
        label.setIcon(new ImageIcon(img));
        label.setMaximumSize(new Dimension(40, 40));
        label.setMinimumSize(new Dimension(18, 18));
        label.setSize(new Dimension(40, 40));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if(!Tableroa.getTableroa().partidaIrabazi() && !Tableroa.getTableroa().partidaGaldu()){
            		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                		if(lehenengoa){
                			lehenengoa = false;
                			label.clickEgin("lehengoa",tablero);
                		}
                		else{
                			label.clickEgin("ezkerra",tablero);
                			
                		}
                		//EZKER KLIK ZEREGINAK
                		//Begiratu partida nola geratu den
                		if(Tableroa.getTableroa().partidaIrabazi()){
                    		partidaIrabazi();
                    	}
                		else if(Tableroa.getTableroa().partidaGaldu()){ //Partida galdu da
                    		partidaGaldu();
                    	}

                	} 
                	else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
                		if(!lehenengoa){
                			int minak = Tableroa.getTableroa().getMinak();
                			label.clickEgin("eskuina",tablero);
                			if(minak != Tableroa.getTableroa().getMinak()){ //Bandera jarri da
                				minaKontagailuaEguneratu();
                			}
                    		//ESKUBI KLIK ZEREGINAK
                		}
                	}
            	}
            }
        });
        
        return label;
    }
	
	
	private void sortuGelaxkak(int nLerro, int nZutabe){
		for (int y = 0; y < nLerro; y++) {
			for (int x = 0; x < nZutabe; x++) {
				
				JLabel gelaxkaBerri;
				try {
					gelaxkaBerri = gelaxkaSortu(y,x);
					getPanel().add(gelaxkaBerri);
					getPanel().add(gelaxkaBerri,new GridBagConstraints(x, y, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				} catch (IOException e) {
					System.out.println("Ezin da irudia kargatu");
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_panel_1.rowWeights = new double[]{0.0};
			panel_1.setLayout(gbl_panel_1);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			panel_1.add(getLblNewLabel(), gbc_lblNewLabel);
			GridBagConstraints gbc_lblT = new GridBagConstraints();
			gbc_lblT.insets = new Insets(0, 0, 0, 5);
			gbc_lblT.gridx = 1;
			gbc_lblT.gridy = 0;
			panel_1.add(getLblT(), gbc_lblT);
			GridBagConstraints gbc_lblT_1 = new GridBagConstraints();
			gbc_lblT_1.insets = new Insets(0, 0, 0, 5);
			gbc_lblT_1.gridx = 2;
			gbc_lblT_1.gridy = 0;
			panel_1.add(getLblT_1(), gbc_lblT_1);
			GridBagConstraints gbc_lblCarita = new GridBagConstraints();
			gbc_lblCarita.insets = new Insets(0, 0, 0, 5);
			gbc_lblCarita.gridx = 4;
			gbc_lblCarita.gridy = 0;
			panel_1.add(getLabel_1_1(), gbc_lblCarita);
			GridBagConstraints gbc_lblT_2 = new GridBagConstraints();
			gbc_lblT_2.insets = new Insets(0, 0, 0, 5);
			gbc_lblT_2.gridx = 7;
			gbc_lblT_2.gridy = 0;
			panel_1.add(getLabel_1_2(), gbc_lblT_2);
			GridBagConstraints gbc_lblT_3 = new GridBagConstraints();
			gbc_lblT_3.insets = new Insets(0, 0, 0, 5);
			gbc_lblT_3.gridx = 8;
			gbc_lblT_3.gridy = 0;
			panel_1.add(getLabel_1_3(), gbc_lblT_3);
			GridBagConstraints gbc_lblT_4 = new GridBagConstraints();
			gbc_lblT_4.gridx = 9;
			gbc_lblT_4.gridy = 0;
			panel_1.add(getLabel_1_4(), gbc_lblT_4);
		}
		return panel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			
			Image img;
			try {
				img = ImageIO.read(getClass().getResource("n0.gif"));
				lblNewLabel.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblNewLabel;
	}
	private JLabel getLblT() {
		if (lblT == null) {
			lblT = new JLabel("");
			
			Image img = null;
			try {
				switch (this.zailtasuna){
					case 1:
						img = ImageIO.read(getClass().getResource("n1.gif"));
						break;
					case 2:
						img = ImageIO.read(getClass().getResource("n3.gif"));
						break;
					case 3: 
						img = ImageIO.read(getClass().getResource("n7.gif"));
						break;
				}
				lblT.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblT;
	}
	private JLabel getLblT_1() {
		if (lblT_1 == null) {
			lblT_1 = new JLabel("");
			
			Image img = null;
			try {
				switch (this.zailtasuna){
					case 1:
						img = ImageIO.read(getClass().getResource("n0.gif"));
						break;
					case 2:
						img = ImageIO.read(getClass().getResource("n0.gif"));
						break;
					case 3: 
						img = ImageIO.read(getClass().getResource("n5.gif"));
						break;
				}
				lblT_1.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblT_1;
	}
	private JLabel getLabel_1_1() {
		if (lblCarita == null) {
			lblCarita = new JLabel("");
			Image img;
			try {
				img = ImageIO.read(getClass().getResource("cara1.gif"));
				lblCarita.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblCarita;
	}
	private JLabel getLabel_1_2() {
		if (lblT_2 == null) {
			lblT_2 = new JLabel("");
			
			Image img;
			try {
				img = ImageIO.read(getClass().getResource("n0.gif"));
				lblT_2.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblT_2;
	}
	private JLabel getLabel_1_3() {
		if (lblT_3 == null) {
			lblT_3 = new JLabel("");
			
			Image img;
			try {
				img = ImageIO.read(getClass().getResource("n0.gif"));
				lblT_3.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblT_3;
	}
	private JLabel getLabel_1_4() {
		if (lblT_4 == null) {
			lblT_4 = new JLabel("");
			
			Image img;
			try {
				img = ImageIO.read(getClass().getResource("n0.gif"));
				lblT_4.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
		}
		return lblT_4;
	}
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnZailtasuna());
			menuBar.add(getMnAtzera());
		}
		return menuBar;
	}
	private JMenu getMnZailtasuna() {
		if (mnZailtasuna == null) {
			mnZailtasuna = new JMenu("Zailtasuna");
			mnZailtasuna.add(getMntmErraza());
			mnZailtasuna.add(getMntmErtaina());
			mnZailtasuna.add(getMntmZaila());
		}
		return mnZailtasuna;
	}
	private JMenu getMnAtzera() {
		if (mnAtzera == null) {
			mnAtzera = new JMenu("Laguntza");
			mnAtzera.add(getMntmPartidaBerria());
		}
		return mnAtzera;
	}
	private JMenuItem getMntmErraza() {
		if (mntmErraza == null) {
			mntmErraza = new JMenuItem("Erraza");
			mntmErraza.addActionListener(new MntmErrazaActionListener());
		}
		return mntmErraza;
	}
	private JMenuItem getMntmErtaina() {
		if (mntmErtaina == null) {
			mntmErtaina = new JMenuItem("Normala");
			mntmErtaina.addActionListener(new MntmErtainaActionListener());
		}
		return mntmErtaina;
	}
	private JMenuItem getMntmZaila() {
		if (mntmZaila == null) {
			mntmZaila = new JMenuItem("Zaila");
			mntmZaila.addActionListener(new MntmZailaActionListener());
		}
		return mntmZaila;
	}
	
	private class MntmErrazaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Tableroa.getTableroa().hasieratu();
			TableroaI tableroI = new TableroaI(7,10);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmErtainaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Tableroa.getTableroa().hasieratu();
			TableroaI tableroI = new TableroaI(10,15);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmZailaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Tableroa.getTableroa().hasieratu();
			TableroaI tableroI = new TableroaI(12,25);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmPartidaBerriaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TableroaI tableroI = new TableroaI(ler,zut);
			Tableroa.getTableroa().hasieratu();
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	
	private JMenuItem getMntmPartidaBerria() {
		if (mntmPartidaBerria == null) {
			mntmPartidaBerria = new JMenuItem("Partida Berria");
			mntmPartidaBerria.addActionListener(new MntmPartidaBerriaActionListener());
		}
		return mntmPartidaBerria;
	}
	
	private void partidaGaldu(){
		JLabel aurpegia = this.getLabel_1_1();
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("cara2.gif"));
			aurpegia.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			System.out.println("Ezin da irudia kargatu");
			e.printStackTrace();
		}
		
		minakErakutsi();
		
	}
	
	private void minakErakutsi(){
		for(int i=0; i < tablero.length; i++){
			for(int j=0; j < tablero[0].length; j++){
				if(!tablero[i][j].irekita() && !tablero[i][j].banderaJarrita() && (Tableroa.getTableroa().balioa(i, j)=='M')){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-n.gif"));
						tablero[i][j].setIcon(new ImageIcon(img));
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
				}
				else if(!tablero[i][j].irekita() && tablero[i][j].banderaJarrita() && (Tableroa.getTableroa().balioa(i, j)=='M')){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-x.gif"));
						tablero[i][j].setIcon(new ImageIcon(img));
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	private void partidaIrabazi(){
		JLabel aurpegia = this.getLabel_1_1();
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("cara3.gif"));
			aurpegia.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			System.out.println("Ezin da irudia kargatu");
			e.printStackTrace();
		}
	}
	
	private void minaKontagailuaEguneratu(){
		int minak = Tableroa.getTableroa().getMinak();
		int unitateak = minak%10;
		this.zenbakiaJarri(getLblT_1(), unitateak);
		
		int hamarrekoak = minak/10;
		this.zenbakiaJarri(getLblT(), hamarrekoak);
	}
	
	private void zenbakiaJarri(JLabel label, int zenb){
		Image img;
		
		switch(zenb){
		case 0: 
			try {
				img = ImageIO.read(getClass().getResource("n0.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 1: 
			try {
				img = ImageIO.read(getClass().getResource("n1.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 2: 
			try {
				img = ImageIO.read(getClass().getResource("n2.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 3: 
			try {
				img = ImageIO.read(getClass().getResource("n3.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 4: 
			try {
				img = ImageIO.read(getClass().getResource("n4.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 5: 
			try {
				img = ImageIO.read(getClass().getResource("n5.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 6: 
			try {
				img = ImageIO.read(getClass().getResource("n6.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 7: 
			try {
				img = ImageIO.read(getClass().getResource("n7.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 8: 
			try {
				img = ImageIO.read(getClass().getResource("n8.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
			
		case 9: 
			try {
				img = ImageIO.read(getClass().getResource("n9.gif"));
				label.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			break;
		}
	}
	
}

