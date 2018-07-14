package intefaces;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.*;
import paqueteEnvios.Comando;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

public class MenuInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;

	public MenuInicioSesion(final Cliente cliente) {	

		setTitle("Iniciar Sesion");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized(cliente){
					cliente.setAccion(Comando.DESCONECTAR);
					cliente.notify();
				}
				dispose();
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 444, 271);
		contentPane.add(layeredPane);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(111, 118, 68, 21);
		layeredPane.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setForeground(Color.BLACK);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(111, 66, 55, 23);
		layeredPane.add(lblUser, new Integer(2));
		lblUser.setForeground(Color.BLACK);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtUser = new JTextField();
		txtUser.setBounds(198, 69, 118, 20);
		layeredPane.add(txtUser, new Integer(1));
		txtUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!txtUser.getText().equals("") && !passwordField.getText().equals("")){
					synchronized(cliente){
						cliente.setAccion(Comando.INICIOSESION);
						cliente.getPaqueteUsuario().setUsername(txtUser.getText());
						cliente.getPaqueteUsuario().setPassword(passwordField.getText());
						cliente.notify();
						dispose();
					}
				}
			}
		});
		passwordField.setBounds(198, 119, 118, 20);
		layeredPane.add(passwordField, new Integer(1));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnConectar = new JButton("Ingresar");
		btnConectar.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnConectar.setBounds(141, 182, 153, 23);
		layeredPane.add(btnConectar, new Integer(1));
		btnConectar.setFocusable(false);
		btnConectar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!txtUser.getText().equals("") && !passwordField.getText().equals("")){
					synchronized(cliente){
						cliente.setAccion(Comando.INICIOSESION);
						cliente.getPaqueteUsuario().setUsername(txtUser.getText());
						cliente.getPaqueteUsuario().setPassword(passwordField.getText());
						cliente.notify();
						dispose();
					}
				}
			}
		});
	}
}
