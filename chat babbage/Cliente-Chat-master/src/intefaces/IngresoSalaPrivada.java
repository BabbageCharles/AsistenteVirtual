package intefaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteSala;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngresoSalaPrivada extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private Cliente cliente;

	public IngresoSalaPrivada(String nomb,Cliente c) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.cliente=c;
		setVisible(true);
		
		JLabel lblIngreseLaConstrasea = new JLabel("Ingrese la constraseña de la Sala");
		
		passwordField = new JPasswordField();
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(passwordField.getText().equals("123")) {
					PaqueteSala paqueteSala = new PaqueteSala(nomb,cliente.getPaqueteUsuario().getUsername());
					cliente.setPaqueteSala(paqueteSala);
					synchronized (cliente) {
						cliente.setAccion(Comando.ENTRARSALA);
						cliente.notify();
					}					
					dispose();
				}else
				{
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(118)
							.addComponent(lblIngreseLaConstrasea))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addComponent(btnIngresar)))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(lblIngreseLaConstrasea)
					.addGap(26)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnIngresar)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
