package intefaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Inicio extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String ipScanned = "localhost";
	private int puertoScanned = 1234;
	private Cliente cliente;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Inicio() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 348, 182);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextField ip = new JTextField();
		ip.setBackground(Color.WHITE);
		
		ip.setBounds(206, 29, 116, 22);
		contentPane.add(ip);
		ip.setColumns(10);

		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setFont(new Font("Bodoni MT", Font.PLAIN, 14));
		lblDireccion.setBounds(36, 31, 128, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Bodoni MT", Font.PLAIN, 14));
		lblPuerto.setBounds(36, 68, 46, 14);
		contentPane.add(lblPuerto);
		
		JTextField puerto = new JTextField();
		puerto.setBounds(206, 66, 116, 20);
		contentPane.add(puerto);
		puerto.setColumns(10);
		
		ip.setText(ipScanned);
		puerto.setText(String.valueOf(puertoScanned));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ipScanned = ip.getText();
				puertoScanned = Integer.valueOf(puerto.getText());
				cliente = new Cliente(ipScanned, puertoScanned);
				cliente.start();
				dispose();
			}
		});
		btnAceptar.setBounds(107, 109, 89, 23);
		contentPane.add(btnAceptar);
		this.getRootPane().setDefaultButton(btnAceptar);
		btnAceptar.requestFocus();
	}
}
