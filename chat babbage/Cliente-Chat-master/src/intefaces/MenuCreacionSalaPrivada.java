package intefaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteSala;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

public class MenuCreacionSalaPrivada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSala;
	private String name;
	private String pass;
	private Cliente cliente;
	private JPasswordField passwordField;

	public MenuCreacionSalaPrivada(Cliente cli) {
		setResizable(false);
		this.cliente = cli;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 369, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnCrearSala.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!textFieldSala.getText().equals("")){
					name = textFieldSala.getText();
					pass = passwordField.getText();
					cliente.getPaqueteSala().setNombreSala(name+"(Privada)");
					cliente.getPaqueteSala().setOwnerSala(pass);
					cliente.getPaqueteSala().setTipo(1); 					
					cliente.setAccion(Comando.NEWSALA);
					synchronized (cliente) {
						cliente.notify();
					}
					dispose();
				}
			}
		});
		btnCrearSala.setBounds(10, 164, 333, 46);
		contentPane.add(btnCrearSala);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(10, 221, 333, 46);
		contentPane.add(btnSalir);
		
		JLabel lblNombreSala = new JLabel("Nombre de la Sala");
		lblNombreSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSala.setBounds(79, 11, 154, 37);
		contentPane.add(lblNombreSala);
		
		textFieldSala = new JTextField();
		textFieldSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldSala.getText().equals("")){
					PaqueteSala paqueteSala = new PaqueteSala(textFieldSala.getText(),cliente.getPaqueteUsuario().getUsername());
					cliente.setPaqueteSala(paqueteSala);
					cliente.setAccion(Comando.NEWSALA);
					synchronized (cliente) {
						cliente.notify();
					}
					dispose();
				}
			}
		});
		textFieldSala.setBounds(10, 43, 333, 30);
		contentPane.add(textFieldSala);
		textFieldSala.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(118, 84, 94, 14);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 106, 333, 37);
		contentPane.add(passwordField);
		setVisible(true);
	}
}
