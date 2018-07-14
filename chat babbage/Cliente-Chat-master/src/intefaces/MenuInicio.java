package intefaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import paqueteEnvios.Comando;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

public class MenuInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cliente cliente;

	public MenuInicio(final Cliente cli) {
		
		this.cliente = cli;
		setTitle("Inicio");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);	
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
					synchronized (cliente) {
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
		
		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnRegistrar.setBounds(121, 162, 191, 23);
		layeredPane.add(btnRegistrar, new Integer(1));
		btnRegistrar.setFocusable(false);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuRegistro menuRegistro = new MenuRegistro(cliente);
				menuRegistro.setVisible(true);
				dispose();
			}
		});
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(UIManager.getColor("CheckBox.shadow"));
		
		btnIniciarSesion.setBounds(121, 92, 191, 23);
		layeredPane.add(btnIniciarSesion, new Integer(1));
		btnIniciarSesion.setFocusable(true);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
				menuInicioSesion.setVisible(true);
				dispose();
			}
		});
		
		this.getRootPane().setDefaultButton(btnIniciarSesion);
		btnIniciarSesion.requestFocus();
		setVisible(true);
	}
}


