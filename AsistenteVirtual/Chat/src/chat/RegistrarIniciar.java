package chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegistrarIniciar extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public boolean salio = false;

	/**
	 * Create the dialog.
	 */
	

	public RegistrarIniciar(ClienteChat cliente){
		setResizable(false);//, JFrame cli) {
		//super(ventanachat, "registrar", true);
		this.setModal(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 244, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("INICIAR SESION");
			
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					VentanaConfiguracion vc = new VentanaConfiguracion();
					Usuario user=new Usuario();				
					user.setHost(vc.getHost());
					user.setPuerto(vc.getPuerto());
					user.setUsuario(vc.getUsuario());
					user.setPassword(vc.getPassword());
					cliente.setUser(user);
					setVisible(false);
					salio = ServidorChat.loguearUser(cliente);
					dispose();

				}
			});
			btnNewButton.setBounds(29, 46, 175, 23);
			contentPanel.add(btnNewButton);
		}

		JButton btnNewButton_1 = new JButton("REGISTRARSE");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaConfiguracion vc = new VentanaConfiguracion();
				Usuario user=new Usuario();
				user.setHost(vc.getHost());
				user.setPuerto(vc.getPuerto());
				user.setUsuario(vc.getUsuario());
				user.setPassword(vc.getPassword());
				cliente.setUser(user);
				setVisible(false);
				salio = ServidorChat.registrarUser(cliente);
				dispose();
			}
		});
		btnNewButton_1.setBounds(29, 99, 175, 23);
		contentPanel.add(btnNewButton_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setVisible(true);
	}
}