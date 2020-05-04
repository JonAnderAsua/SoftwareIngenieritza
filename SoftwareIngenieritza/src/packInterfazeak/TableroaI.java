package packInterfazeak;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import packEstruktura.Bandera;
import packEstruktura.Galdera;
import packEstruktura.Hutsa;
import packEstruktura.Irekita;
import packEstruktura.Itxita;
import packEstruktura.JokalariKatalogo;
import packEstruktura.Kronometroa;
import packEstruktura.Mina;
import packEstruktura.Tableroa;
import packEstruktura.Zenbakia;


public class TableroaI extends JDialog implements Observer{
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
	private int ler;
	private int zut;
	private JLabel[][] tablero;
	
	

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
		this.tablero = new JLabel[nLerro][nZutabe];
		ler=nLerro;
		zut=nZutabe;
		if(nLerro==7){
			this.setMinimumSize(new Dimension(206, 295));
            zailtasuna=1;
        }
        else if(nLerro==10){
        	this.setMinimumSize(new Dimension(406, 350));
            zailtasuna=2;
        }
        else{
            zailtasuna=3;
            this.setMinimumSize(new Dimension(606, 400));
        }
		initialize(nLerro,nZutabe);
		Tableroa.getTableroa().addObserver(this);
	}
	private void initialize(int nLerro, int nZutabe) {
		switch (zailtasuna){
		case 1:
			setTitle("Dragamina. Tableroa 7x10");
			break;
		case 2: 
			setTitle("Dragamina. Tableroa 10x15");
			break;
		case 3: 
			setTitle("Dragamina. Tableroa 12x25");
			break;
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TableroaI.class.getResource("/packInterfazeak/unnamed.jpg")));
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

        JLabel label = new JLabel();
        this.tablero[i][j] = label;
        label.setBorder(BorderFactory.createLoweredBevelBorder());
        Image img = ImageIO.read(getClass().getResource("tablero.gif"));
        label.setIcon(new ImageIcon(img));
        label.setMaximumSize(new Dimension(40, 40));
        label.setMinimumSize(new Dimension(18, 18));
        label.setSize(new Dimension(40, 40));

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                		//EZKER KLIK ZEREGINAK
            			Tableroa.getTableroa().ezkerrekoClick(zut, ler, i, j);
            			
                	} 
                	else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
                		//ESKUBI KLIK ZEREGINAK
                		Tableroa.getTableroa().eskuinekoClick(i, j);
                		
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
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Hutsa){
			if(!Tableroa.getTableroa().partidaGaldu()){
				if(((Hutsa) arg1).getEgoera() instanceof Irekita){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("c0.gif"));
						tablero[((Hutsa) arg1).geti()][((Hutsa) arg1).getj()].setIcon(new ImageIcon(img));
						
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
				}
				else if(((Hutsa) arg1).getEgoera() instanceof Bandera){
					this.banderaIpini(((Hutsa) arg1).geti(), ((Hutsa) arg1).getj());
					
				}
				else if(((Hutsa) arg1).getEgoera() instanceof Itxita){
					this.itxi(((Hutsa) arg1).geti(), ((Hutsa) arg1).getj());
				}
				else if(((Hutsa) arg1).getEgoera() instanceof Galdera){
					this.galderaIpini(((Hutsa) arg1).geti(), ((Hutsa) arg1).getj());
				}
			}
			else{//Partida galdu da
				if(((Hutsa) arg1).getEgoera() instanceof Bandera){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-x.gif"));
						tablero[((Hutsa) arg1).geti()][((Hutsa) arg1).getj()].setIcon(new ImageIcon(img));
						
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
					
				}
			}
			
		}
		else if(arg1 instanceof Zenbakia){
			if(!Tableroa.getTableroa().partidaGaldu()){
				if(((Zenbakia) arg1).getEgoera() instanceof Irekita){
					this.zenbakiaIpini(((Zenbakia) arg1).geti(), ((Zenbakia) arg1).getj(), ((Zenbakia) arg1).getZenbakia());
				}
				else if(((Zenbakia) arg1).getEgoera() instanceof Bandera){
					this.banderaIpini(((Zenbakia) arg1).geti(), ((Zenbakia) arg1).getj());
					
				}
				else if(((Zenbakia) arg1).getEgoera() instanceof Itxita){
					this.itxi(((Zenbakia) arg1).geti(), ((Zenbakia) arg1).getj());
				}
				else if(((Zenbakia) arg1).getEgoera() instanceof Galdera){
					this.galderaIpini(((Zenbakia) arg1).geti(), ((Zenbakia) arg1).getj());
				}
			}
			else{//Partida galdu da
				if(((Zenbakia) arg1).getEgoera() instanceof Bandera){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-x.gif"));
						tablero[((Zenbakia) arg1).geti()][((Zenbakia) arg1).getj()].setIcon(new ImageIcon(img));
						
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
				}
			}
			
			
		}
		else if(arg1 instanceof Mina){
			if(!Tableroa.getTableroa().partidaGaldu()){
				if(((Mina) arg1).getEgoera() instanceof Irekita){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-r.gif"));
						tablero[((Mina) arg1).geti()][((Mina) arg1).getj()].setIcon(new ImageIcon(img));
						
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
				}
				else if(((Mina) arg1).getEgoera() instanceof Bandera){
					this.banderaIpini(((Mina) arg1).geti(), ((Mina) arg1).getj());
					
				}
				else if(((Mina) arg1).getEgoera() instanceof Itxita){
					this.itxi(((Mina) arg1).geti(), ((Mina) arg1).getj());
				}
				else if(((Mina) arg1).getEgoera() instanceof Galdera){
					this.galderaIpini(((Mina) arg1).geti(), ((Mina) arg1).getj());
				}
			}
			else{//Partida galdu da
				if(((Mina) arg1).getEgoera() instanceof Itxita || ((Mina) arg1).getEgoera() instanceof Galdera){
					Image img;
					try {
						img = ImageIO.read(getClass().getResource("mina-n.gif"));
						tablero[((Mina) arg1).geti()][((Mina) arg1).getj()].setIcon(new ImageIcon(img));
						
					} catch (IOException e) {
						System.out.println("Ezin da irudia kargatu");
						e.printStackTrace();
					}
					
				}
			}
			
			
		}
		else if(arg1 instanceof String){
			switch (((String)arg1)){
			case "Irabazi":
				this.partidaIrabazi();
				this.denboraJarri();
				break;
			case "Galdu":
				this.partidaGaldu();
				this.denboraJarri();
				break;
			case "PopupI":
				int denbora = Kronometroa.getKronometroa().pasaDirenSegunduakLortu();
				int jokPunt = JokalariKatalogo.getJokalariKatalogo().puntuazioaKalkulatu(denbora,zailtasuna);
				Popup pop = new Popup(zailtasuna,jokPunt,ler,zut,this);
				System.out.println("Irabazi");
				pop.setLocation(600, 100);
				pop.setVisible(true);
				break;
			case "PopupG":
				Popup pop1 = new Popup(zailtasuna,0,ler,zut,this);
				System.out.println("Galdu");
				pop1.setLocation(600, 100);
				pop1.setVisible(true);
				break;
			}
		}
		else{ //Mina kontagailua eguneratu behar da
			this.minaKontagailuaEguneratu();
		}
	}
	
	private void banderaIpini(int i, int j){
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("bandera.gif"));
			tablero[i][j].setIcon(new ImageIcon(img));
			
		} catch (IOException e) {
			System.out.println("Ezin da irudia kargatu");
			e.printStackTrace();
		}
	}
	
	private void itxi(int i, int j){
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("tablero.gif"));
			tablero[i][j].setIcon(new ImageIcon(img));
			
		} catch (IOException e) {
			System.out.println("Ezin da irudia kargatu");
			e.printStackTrace();
		}
	}
	
	private void galderaIpini(int i, int j){
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("marca.gif"));
			tablero[i][j].setIcon(new ImageIcon(img));
			
		} catch (IOException e) {
			System.out.println("Ezin da irudia kargatu");
			e.printStackTrace();
		}
	}
	
	private void zenbakiaIpini(int i, int j, int zenb){
		Image img;
		switch(zenb){
		case 1:
			try {
				img = ImageIO.read(getClass().getResource("c1.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
		case 2:
			try {
				img = ImageIO.read(getClass().getResource("c2.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 3:
			try {
				img = ImageIO.read(getClass().getResource("c3.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 4:
			try {
				img = ImageIO.read(getClass().getResource("c4.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 5:
			try {
				img = ImageIO.read(getClass().getResource("c5.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 6:
			try {
				img = ImageIO.read(getClass().getResource("c6.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 7:
			try {
				img = ImageIO.read(getClass().getResource("c7.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
			
		case 8:
			try {
				img = ImageIO.read(getClass().getResource("c8.gif"));
				tablero[i][j].setIcon(new ImageIcon(img));
				
			} catch (IOException e) {
				System.out.println("Ezin da irudia kargatu");
				e.printStackTrace();
			}
			
			break;
		}
	}
	
	private void denboraJarri(){
		int denbora = Kronometroa.getKronometroa().pasaDirenSegunduakLortu();
		int unitateak = denbora%10;
		this.zenbakiaJarri(getLabel_1_4(), unitateak);
		
		denbora = denbora/10;
		int hamarrekoak = denbora%10;
		this.zenbakiaJarri(getLabel_1_3(), hamarrekoak);
		
		denbora = denbora/10;
		int ehunekoak = denbora%10;
		this.zenbakiaJarri(getLabel_1_2(), ehunekoak);
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
		
		//minakErakutsi();
		
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
			lblCarita.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
	            			Tableroa.getTableroa().deleteObservers();
	                    	TableroaI tableroI = new TableroaI(ler,zut);
	                    	Tableroa.getTableroa().hasieratu();
	                    	setVisible(false);
	                    	tableroI.setVisible(true);
	                	} 	  	
	            }
	        });
	            
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
			mnAtzera = new JMenu("Erreseteatu");
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
			Tableroa.getTableroa().deleteObservers();
			TableroaI tableroI = new TableroaI(7,10);
			Tableroa.getTableroa().addObserver(tableroI);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmErtainaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Tableroa.getTableroa().hasieratu();
			Tableroa.getTableroa().deleteObservers();
			TableroaI tableroI = new TableroaI(10,15);
			Tableroa.getTableroa().addObserver(tableroI);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmZailaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Tableroa.getTableroa().hasieratu();
			Tableroa.getTableroa().deleteObservers();
			TableroaI tableroI = new TableroaI(12,25);
			Tableroa.getTableroa().addObserver(tableroI);
			setVisible(false);
			tableroI.setVisible(true);
		}
	}
	private class MntmPartidaBerriaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Tableroa.getTableroa().deleteObservers();
			TableroaI tableroI = new TableroaI(ler,zut);
			Tableroa.getTableroa().addObserver(tableroI);
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
	
}

