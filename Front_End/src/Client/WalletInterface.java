package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.Color;

public class WalletInterface implements ActionListener{

	private JFrame frame;
	private JPasswordField Pin_Code;
	private JTextField txtTnd;
	private JButton Cancel_First_Panel;
	private JButton Valider_First_Panel;
	private JButton Solde_Main_Frame,crediter_Main_Frame,debiter_main_frame;
	private JButton Terminer_Erreur,retry_Erreur;
	private JButton crediter_10_btn,crediter_20_btn,crediter_30_btn,crediter_40_btn,crediter_50_btn,crediter_70_btn,crediter_90_btn,crediter_120_btn;
	private JButton debiter_10_btn,debiter_20_btn,debiter_30_btn,debiter_40_btn,debiter_50_btn,debiter_70_btn,debiter_90_btn,debiter_120_btn;
	private JButton a_propos_btn, back_solde,exit_solde,Retour_Succes,Terminer_Succes  ;
	private JLabel lblNewLabel_10;
	private CardLayout Main_Layout;
	private JPanel Main_Panel;
	private JPanel Succes_panel;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JPanel Erreur_panel;
	private JLabel lblNewLabel_11;
	private JLabel Erreur_message;
	private JButton Retour_erreur,Retour_btn_apropos;
	private JButton Terminer_Erreur_btn,Terminer_Bloquage;

	public static final byte INS_INTERROGER_COMPTE = 0x01;
	public static final byte INS_INCREMENTER_COMPTE = 0x02;
	public static final byte INS_DECREMENTER_COMPTE = 0x03;
	public static final byte INS_INITIALISER_COMPTE = 0x04;
	public static final byte INS_TEST_CODE_PIN = 0x00;
	
	private WalletFunction client;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WalletInterface window = new WalletInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WalletInterface() {
		client = new WalletFunction();
		client.Connect();
		try {
			client.select();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CadTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("Ben Slama Bank _BSB_ ");
		frame.setBounds(100, 100, 900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Main_Layout=new CardLayout(0, 0);
		Main_Panel=(JPanel)frame.getContentPane();
		Main_Panel.setLayout(Main_Layout);
		
		JPanel First_Panel = new JPanel();
		Main_Panel.add(First_Panel, "First_Panel");
		First_Panel.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		First_Panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 886, 112);
		lblNewLabel.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/LogoBank_BSB.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		First_Panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Please Enter Your Code [4 chiffres]:");
		lblNewLabel_1.setBounds(0, 48, 886, 86);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		First_Panel.add(panel_3);
		panel_3.setLayout(null);
		
		Pin_Code = new JPasswordField(4);
		Pin_Code.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Pin_Code.setBounds(210, 25, 470, 60);
		Pin_Code.setHorizontalAlignment(SwingConstants.CENTER);
		Pin_Code.setMaximumSize(new Dimension(470, 60));
		Pin_Code.setMinimumSize(new Dimension(470, 60));
		panel_3.add(Pin_Code);
		
		JPanel panel_4 = new JPanel();
		First_Panel.add(panel_4);
		panel_4.setLayout(null);
		
		Cancel_First_Panel = new JButton("Annuler");
		Cancel_First_Panel.setFont(new Font("Tahoma", Font.BOLD, 22));
		Cancel_First_Panel.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/icons8-close-48.png")));
		Cancel_First_Panel.setBounds(133, 23, 180, 60);
		panel_4.add(Cancel_First_Panel);
		
		Valider_First_Panel = new JButton("Valider");
		Valider_First_Panel.setFont(new Font("Tahoma", Font.BOLD, 22));
		Valider_First_Panel.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/icons8-forward-button-48.png")));
		Valider_First_Panel.setBounds(578, 23, 180, 60);
		panel_4.add(Valider_First_Panel);
		
		JPanel panel_5 = new JPanel();
		First_Panel.add(panel_5);
		
		lblNewLabel_10 = new JLabel("Erreur: Il faut que le code pin soit en chiffres !!");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_10.setVisible(false);
		panel_5.add(lblNewLabel_10);
		
		JPanel Main_panel = new JPanel();
		Main_Panel.add(Main_panel, "Main_panel");
		Main_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/LogoBank_BSB.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		lblNewLabel_2.setBounds(0, 0, 886, 120);
		Main_panel.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("RFU");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_2.setBounds(706, 250, 180, 70);
		Main_panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("RFU");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_3.setBounds(706, 360, 180, 70);
		Main_panel.add(btnNewButton_3);
		
		a_propos_btn = new JButton("A propos");
		a_propos_btn.setFont(new Font("Tahoma", Font.BOLD, 22));
		a_propos_btn.setBounds(706, 470, 180, 70);
		Main_panel.add(a_propos_btn);
		
		Solde_Main_Frame = new JButton("Solde");
		Solde_Main_Frame.setFont(new Font("Tahoma", Font.BOLD, 22));
		Solde_Main_Frame.setBounds(0, 250, 180, 70);
		Main_panel.add(Solde_Main_Frame);
		
		crediter_Main_Frame = new JButton("Créditer");
		crediter_Main_Frame.setFont(new Font("Tahoma", Font.BOLD, 22));
		crediter_Main_Frame.setBounds(0, 360, 180, 70);
		Main_panel.add(crediter_Main_Frame);
		
		debiter_main_frame = new JButton("Débiter");
		debiter_main_frame.setFont(new Font("Tahoma", Font.BOLD, 22));
		debiter_main_frame.setBounds(0, 470, 180, 70);
		Main_panel.add(debiter_main_frame);
		
		JPanel pin_erreur = new JPanel();
		Main_Panel.add(pin_erreur, "pin_erreur");
		pin_erreur.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/Error.png")));
		lblNewLabel_5.setBounds(0, 71, 886, 166);
		pin_erreur.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Erreur: Code Pin est incorrect");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_6.setBounds(0, 210, 886, 181);
		pin_erreur.add(lblNewLabel_6);
		
		Terminer_Erreur = new JButton("Terminer");
		Terminer_Erreur.setFont(new Font("Tahoma", Font.BOLD, 22));
		Terminer_Erreur.setBounds(135, 386, 180, 70);
		pin_erreur.add(Terminer_Erreur);
		
		retry_Erreur = new JButton("Réessayer");
		retry_Erreur.setFont(new Font("Tahoma", Font.BOLD, 22));
		retry_Erreur.setBounds(548, 386, 180, 70);
		pin_erreur.add(retry_Erreur);
		
		JPanel Solde = new JPanel();
		Main_Panel.add(Solde, "Solde");
		Solde.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Votre Solde: ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_7.setBounds(0, 159, 886, 96);
		Solde.add(lblNewLabel_7);
		
		txtTnd = new JTextField();
		txtTnd.setEditable(false);
		txtTnd.setHorizontalAlignment(SwingConstants.CENTER);
		txtTnd.setText("1800 TND");
		txtTnd.setFont(new Font("Tahoma", Font.ITALIC, 30));
		txtTnd.setBounds(129, 257, 606, 81);
		Solde.add(txtTnd);
		txtTnd.setColumns(10);
		
		back_solde = new JButton("Retour");
		back_solde.setFont(new Font("Tahoma", Font.BOLD, 22));
		back_solde.setBounds(129, 408, 180, 70);
		Solde.add(back_solde);
		
		exit_solde = new JButton("Terminer");
		exit_solde.setFont(new Font("Tahoma", Font.BOLD, 22));
		exit_solde.setBounds(555, 408, 180, 70);
		Solde.add(exit_solde);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/LogoBank_BSB.png")));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		lblNewLabel_8.setBounds(0, 10, 886, 121);
		Solde.add(lblNewLabel_8);
		
		JPanel Crediter_panel = new JPanel();
		Main_Panel.add(Crediter_panel, "Crediter_panel");
		Crediter_panel.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Choissir le montant qui vous voulez créditer");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_9.setBounds(0, 10, 886, 123);
		Crediter_panel.add(lblNewLabel_9);
		
		crediter_10_btn = new JButton("10 TND");
		crediter_10_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_10_btn.setBounds(0, 172, 180, 70);
		Crediter_panel.add(crediter_10_btn);
		
		crediter_20_btn = new JButton("20 TND");
		crediter_20_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_20_btn.setBounds(0, 270, 180, 70);
		Crediter_panel.add(crediter_20_btn);
		
		crediter_30_btn = new JButton("30 TND");
		crediter_30_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_30_btn.setBounds(0, 373, 180, 70);
		Crediter_panel.add(crediter_30_btn);
		
		crediter_40_btn = new JButton("40 TND");
		crediter_40_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_40_btn.setBounds(0, 468, 180, 70);
		Crediter_panel.add(crediter_40_btn);
		
		crediter_50_btn = new JButton("50 TND");
		crediter_50_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_50_btn.setBounds(706, 172, 180, 70);
		Crediter_panel.add(crediter_50_btn);
		
		crediter_70_btn = new JButton("70 TND");
		crediter_70_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_70_btn.setBounds(706, 270, 180, 70);
		Crediter_panel.add(crediter_70_btn);
		
		crediter_90_btn = new JButton("90 TND");
		crediter_90_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_90_btn.setBounds(706, 373, 180, 70);
		Crediter_panel.add(crediter_90_btn);
		
		crediter_120_btn = new JButton("120 TND");
		crediter_120_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		crediter_120_btn.setBounds(706, 468, 180, 70);
		Crediter_panel.add(crediter_120_btn);
		
		JPanel Debiter_panel = new JPanel();
		Debiter_panel.setLayout(null);
		Main_Panel.add(Debiter_panel, "Debiter_panel");
		
		JLabel lblNewLabel_9_1 = new JLabel("Choissir le montant qui vous voulez débiter");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_9_1.setBounds(0, 10, 886, 123);
		Debiter_panel.add(lblNewLabel_9_1);
		
		debiter_10_btn = new JButton("10 TND");
		debiter_10_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_10_btn.setBounds(0, 172, 180, 70);
		Debiter_panel.add(debiter_10_btn);
		
		debiter_20_btn = new JButton("20 TND");
		debiter_20_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_20_btn.setBounds(0, 270, 180, 70);
		Debiter_panel.add(debiter_20_btn);
		
		debiter_30_btn = new JButton("30 TND");
		debiter_30_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_30_btn.setBounds(0, 373, 180, 70);
		Debiter_panel.add(debiter_30_btn);
		
		debiter_40_btn = new JButton("40 TND");
		debiter_40_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_40_btn.setBounds(0, 468, 180, 70);
		Debiter_panel.add(debiter_40_btn);
		
		debiter_50_btn = new JButton("50 TND");
		debiter_50_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_50_btn.setBounds(706, 172, 180, 70);
		Debiter_panel.add(debiter_50_btn);
		
		debiter_70_btn = new JButton("70 TND");
		debiter_70_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_70_btn.setBounds(706, 270, 180, 70);
		Debiter_panel.add(debiter_70_btn);
		
		debiter_90_btn = new JButton("90 TND");
		debiter_90_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_90_btn.setBounds(706, 373, 180, 70);
		Debiter_panel.add(debiter_90_btn);
		
		debiter_120_btn = new JButton("120 TND");
		debiter_120_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		debiter_120_btn.setBounds(706, 468, 180, 70);
		Debiter_panel.add(debiter_120_btn);
		
		JPanel A_propos_Panel = new JPanel();
		Main_Panel.add(A_propos_Panel, "A_propos_Panel");
		A_propos_Panel.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/309124236_5326592124105141_8747167939274630792_n.jpg")));
		lblNewLabel_14.setBounds(51, 193, 252, 278);
		A_propos_Panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/LogoBank_BSB.png")));
		lblNewLabel_15.setBounds(0, 0, 886, 152);
		A_propos_Panel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Realisé par:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_16.setBounds(329, 177, 178, 70);
		A_propos_Panel.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Proposé par:");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_17.setBounds(329, 257, 178, 70);
		A_propos_Panel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("E-mail:");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_18.setBounds(329, 337, 159, 70);
		A_propos_Panel.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("Num Telephone: ");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_19.setBounds(329, 417, 225, 70);
		A_propos_Panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Haithem Ben Slama");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_20.setBounds(517, 177, 300, 70);
		A_propos_Panel.add(lblNewLabel_20);
		
		JLabel lblNewLabel_20_1 = new JLabel("Mr Ramzi Mahmoudi");
		lblNewLabel_20_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_20_1.setBounds(517, 257, 300, 70);
		A_propos_Panel.add(lblNewLabel_20_1);
		
		JLabel lblNewLabel_20_2 = new JLabel("Haithem.benslama@hotmail.fr");
		lblNewLabel_20_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_20_2.setBounds(440, 337, 420, 70);
		A_propos_Panel.add(lblNewLabel_20_2);
		
		JLabel lblNewLabel_20_3 = new JLabel("+216 27 660 834");
		lblNewLabel_20_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_20_3.setBounds(546, 417, 300, 70);
		A_propos_Panel.add(lblNewLabel_20_3);
		
		Retour_btn_apropos = new JButton("Retour");
		Retour_btn_apropos.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Retour_btn_apropos.setBounds(61, 481, 180, 70);
		A_propos_Panel.add(Retour_btn_apropos);
		
		Succes_panel = new JPanel();
		frame.getContentPane().add(Succes_panel, "Succes_panel");
		Succes_panel.setLayout(null);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/success_logo.png")));
		lblNewLabel_3.setBounds(0, 120, 886, 170);
		Succes_panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Opération effectuée avec succès");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 311, 886, 71);
		Succes_panel.add(lblNewLabel_4);
		
		Retour_Succes = new JButton("Retour");
		Retour_Succes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Retour_Succes.setBounds(106, 431, 180, 70);
		Succes_panel.add(Retour_Succes);
		
		Terminer_Succes = new JButton("Terminer");
		Terminer_Succes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Terminer_Succes.setBounds(576, 431, 180, 70);
		Succes_panel.add(Terminer_Succes);
		
		Erreur_panel = new JPanel();
		Erreur_panel.setLayout(null);
		frame.getContentPane().add(Erreur_panel, "Erreur_panel");
		
		lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/Error.png")));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(0, 120, 886, 170);
		Erreur_panel.add(lblNewLabel_11);
		
		Erreur_message = new JLabel("Erreur: ");
		Erreur_message.setHorizontalAlignment(SwingConstants.CENTER);
		Erreur_message.setFont(new Font("Tahoma", Font.BOLD, 34));
		Erreur_message.setBounds(0, 311, 886, 71);
		Erreur_panel.add(Erreur_message);
		
		Retour_erreur = new JButton("Retour");
		Retour_erreur.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Retour_erreur.setBounds(125, 431, 180, 70);
		Erreur_panel.add(Retour_erreur);
		
		Terminer_Erreur_btn = new JButton("Terminer");
		Terminer_Erreur_btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Terminer_Erreur_btn.setBounds(576, 431, 180, 70);
		Erreur_panel.add(Terminer_Erreur_btn);
		
		JPanel Bloquage_panel = new JPanel();
		frame.getContentPane().add(Bloquage_panel, "Bloquage_panel");
		Bloquage_panel.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(WalletInterface.class.getResource("/Img/Bloqued.png")));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(0, 75, 886, 215);
		Bloquage_panel.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Nombre de tentatives est depassé. Votre Carte est bloquée");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_13.setBounds(0, 327, 886, 86);
		Bloquage_panel.add(lblNewLabel_13);
		
		Terminer_Bloquage = new JButton("Terminer");
		Terminer_Bloquage.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Terminer_Bloquage.setBounds(358, 450, 180, 70);
		Bloquage_panel.add(Terminer_Bloquage);
		
		Cancel_First_Panel.addActionListener(this);
		Valider_First_Panel.addActionListener(this);
		Solde_Main_Frame.addActionListener(this);
		crediter_Main_Frame.addActionListener(this);
		debiter_main_frame.addActionListener(this);
		Terminer_Erreur.addActionListener(this);
		retry_Erreur.addActionListener(this);
		crediter_10_btn.addActionListener(this);
		crediter_20_btn.addActionListener(this);
		crediter_30_btn.addActionListener(this);
		crediter_40_btn.addActionListener(this);
		crediter_50_btn.addActionListener(this);
		crediter_70_btn.addActionListener(this);
		crediter_90_btn.addActionListener(this);
		crediter_120_btn.addActionListener(this);
		debiter_10_btn.addActionListener(this);
		debiter_20_btn.addActionListener(this);
		debiter_30_btn.addActionListener(this);
		debiter_40_btn.addActionListener(this);
		debiter_50_btn.addActionListener(this);
		debiter_70_btn.addActionListener(this);
		debiter_90_btn.addActionListener(this);
		debiter_120_btn.addActionListener(this);
		a_propos_btn.addActionListener(this);
		back_solde.addActionListener(this);
		exit_solde.addActionListener(this);
		Retour_Succes.addActionListener(this);
		Terminer_Succes.addActionListener(this);
		Retour_erreur.addActionListener(this);
		Terminer_Erreur_btn.addActionListener(this);
		Terminer_Bloquage.addActionListener(this);
		Retour_btn_apropos.addActionListener(this);
	}
	
	public void crediter_action(String a) {
		Scanner sc = new Scanner(a);
		byte[] montant = new byte[] {sc.nextByte() };
		Apdu apdu = null;
		try {
			apdu = client.Msg(INS_INCREMENTER_COMPTE, (byte) 0x01, montant, (byte) 0x7f);
			System.out.println(apdu);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CadTransportException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (apdu.getStatus() != 0x9000) {
			Erreur_message.setText("Erreur: Vous Avez dépasser le montant spécifié");
			Main_Layout.show(Main_Panel, "Erreur_panel");
		} else {
			Main_Layout.show(Main_Panel, "Succes_panel");
		}
	}
	
	public void debiter_action(String a) {
		Scanner sc = new Scanner(a);
		byte[] montant = new byte[] {sc.nextByte() };
		Apdu apdu = null;
		try {
			apdu = client.Msg(INS_DECREMENTER_COMPTE, (byte) 0x01, montant, (byte) 0x7f);
			System.out.println(apdu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CadTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.reset();
		if (apdu.getStatus() == 0x6A85) {
			Erreur_message.setText("Erreur: Solde Insuffisant");
			Main_Layout.show(Main_Panel, "Erreur_panel");
		} else {
			Main_Layout.show(Main_Panel, "Succes_panel");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton) e.getSource();
		
		if(e.getSource()==Valider_First_Panel) {
			String code= Pin_Code.getText();
			if(code.length()!=4) {
				lblNewLabel_10.setText("Erreur: Le code PIN se compose de 4 chiffres ");
				lblNewLabel_10.setVisible(true);
			}
			else {
				int a=0;
				try {
				a= Integer.parseInt(code);
				}catch(NumberFormatException Nfe) {}
				lblNewLabel_10.setVisible(false);
				int a1=a/1000;
				int a2=(a/100)%10;
				int a3=(a/10)%10;
				int a4=a%10;
				byte[] pin_ok= {(byte) a1, (byte) a2, (byte) a3, (byte) a4};
				Apdu apdu = null;
				try {
					apdu = client.Msg(INS_TEST_CODE_PIN, (byte) 0x04, pin_ok, (byte) 0x7f);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CadTransportException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(apdu);
				if (apdu.getStatus() == 0x6300) {
						Main_Layout.show(Main_Panel, "pin_erreur");
				} else if(apdu.getStatus()== 0x6321) {
					Main_Layout.show(Main_Panel, "Bloquage_panel");
				}
				else
					{
					Main_Layout.show(Main_Panel, "Main_panel");
					}
			}
		}
		
		if(e.getSource()==Solde_Main_Frame) {
			Apdu apdu = null;
			try {
				apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CadTransportException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (apdu.getStatus() != 0x9000) {
				System.out.println("Erreur : status word different de 0x9000");
			} else {
				
				BigInteger one;
				one= new BigInteger(apdu.dataOut);
				txtTnd.setText(one +" TND");
				Main_Layout.show(Main_Panel, "Solde");
				
			}
			
		}
		
		if(e.getSource()==Cancel_First_Panel) {
			client.Deselect();
			System.exit(0);
		}
		
		
		if(e.getSource()==crediter_Main_Frame) {
			Main_Layout.show(Main_Panel, "Crediter_panel");
		}
		
		
		if(e.getSource()==debiter_main_frame) {
			Main_Layout.show(Main_Panel, "Debiter_panel");
		}
		
		
		if(e.getSource()==a_propos_btn ) {
			Main_Layout.show(Main_Panel, "A_propos_Panel");
		}
		
		if(e.getSource()==Terminer_Erreur) {
			client.Deselect();
			System.exit(0);
		}
		
		if (e.getSource()==retry_Erreur) {
			Main_Layout.show(Main_Panel, "First_Panel");
		}
		
		if(e.getSource()==back_solde) {
			Main_Layout.show(Main_Panel, "Main_panel");

		}
		
		if(e.getSource()==exit_solde) {
			if(e.getSource()==Terminer_Erreur) {
				client.Deselect();
				System.exit(0);
			}
		}
		
		if(e.getSource()==Retour_Succes) {
			Main_Layout.show(Main_Panel, "Main_panel");
		}
		
		if(e.getSource()==Terminer_Succes) {
			client.Deselect();
			System.exit(0);		
		}
		
		if (e.getSource()==Terminer_Erreur_btn) {
			client.Deselect();
			System.exit(0);	
		}
		
		if(e.getSource()==Terminer_Bloquage) {
			client.Deselect();
			System.exit(0);	
		}
		if(e.getSource()==exit_solde) {
			client.Deselect();
			System.exit(0);	
		}
		if (e.getSource()==Retour_erreur) {
			Main_Layout.show(Main_Panel, "Main_panel");
		}
		if(e.getSource()==crediter_10_btn) {
			this.crediter_action("10");
		}
		if(e.getSource()==crediter_20_btn) {
			this.crediter_action("20");
		}
		if(e.getSource()==crediter_30_btn) {
			this.crediter_action("30");
		}
		if(e.getSource()==crediter_40_btn) {
			this.crediter_action("40");
		}
		if(e.getSource()==crediter_50_btn) {
			this.crediter_action("50");
		}
		if(e.getSource()==crediter_70_btn) {
			this.crediter_action("70");
		}
		if(e.getSource()==crediter_90_btn) {
			this.crediter_action("90");
		}
		if(e.getSource()==crediter_120_btn) {
			this.crediter_action("120");
		}
		
		if(e.getSource()==debiter_10_btn) {
			this.debiter_action("10");
		}
		
		if(e.getSource()==debiter_20_btn) {
			this.debiter_action("20");
		}
		
		if(e.getSource()==debiter_30_btn) {
			this.debiter_action("30");
		}
		
		if(e.getSource()==debiter_40_btn) {
			this.debiter_action("40");
		}
		
		if(e.getSource()==debiter_50_btn) {
			this.debiter_action("50");
		}
		
		if(e.getSource()==debiter_70_btn) {
			this.debiter_action("70");
		}
		
		if(e.getSource()==debiter_90_btn) {
			this.debiter_action("90");
		}
		
		if(e.getSource()==debiter_120_btn) {
			this.debiter_action("120");
		}
		if(e.getSource()==Retour_btn_apropos) {
			Main_Layout.show(Main_Panel, "Main_panel");
		}
		if(e.getSource()==a_propos_btn) {
			Main_Layout.show(Main_Panel, "A_propos_Panel");
		}
		
		
	}
}
