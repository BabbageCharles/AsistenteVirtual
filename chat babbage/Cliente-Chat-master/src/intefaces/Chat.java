package intefaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import cliente.Cliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;

import java.awt.Color;
import java.awt.Font;


import javax.swing.UIManager;

import javax.swing.GroupLayout;

import javax.swing.GroupLayout.Alignment;

public class Chat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texto;
	private JTextPane chat;
	private static SimpleAttributeSet attrs = new SimpleAttributeSet();	
	
	public Chat(final Cliente cliente) {
	
		setBounds(100, 100, 485, 315);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (mostrarVentanaConfirmacion()) {
					cliente.eliminarChatActivo(getTitle());
					dispose();
				}
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		chat = new JTextPane();
		chat.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		chat.setForeground(Color.BLACK);
		chat.setBackground(Color.WHITE);
		chat.setEditable(false);
		scrollPane.setViewportView(chat);

		texto = new JTextField();
		texto.setForeground(Color.BLACK);
		texto.setCaretColor(Color.BLACK);
		texto.setBackground(Color.WHITE);
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				texto.requestFocus();
			}
		});


		texto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!texto.getText().equals("")) {
					String msj= cliente.getPaqueteUsuario().getUsername() + ": " + texto.getText() + "\n";
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msj,attrs);
					} catch (BadLocationException e1) {
						
						e1.printStackTrace();
					}

					cliente.setAccion(Comando.MP);
					PaqueteMensaje paqueteMsj = new PaqueteMensaje(cliente.getPaqueteUsuario().getUsername(),getTitle(),texto.getText(),null);
					cliente.setPaqueteMensaje(paqueteMsj);

					synchronized (cliente) {
						cliente.notify();
					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});


		JButton enviar = new JButton("Enviar");
		enviar.setBackground(UIManager.getColor("CheckBox.shadow"));
		enviar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!texto.getText().equals("")) {					
					String msj= cliente.getPaqueteUsuario().getUsername() + ": " + texto.getText() + "\n";
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msj,attrs);
					} catch (BadLocationException e1) {
						
						e1.printStackTrace();
					}
					

					cliente.setAccion(Comando.MP);
					PaqueteMensaje paqueteMsj = new PaqueteMensaje(cliente.getPaqueteUsuario().getUsername(),getTitle(),texto.getText(),null);
					cliente.setPaqueteMensaje(paqueteMsj);

					synchronized (cliente) {
						cliente.notify();
					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});
		texto.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(texto, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(enviar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(texto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(enviar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

	private boolean mostrarVentanaConfirmacion() {
		int res = JOptionPane.showConfirmDialog(this, "¿Desea salir de la sesión de chat?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public JTextPane getChat() {
		return chat;
	}

	public JTextField getTexto() {
		return texto;
	}

	public void agregarMsj(String msjAgregar) {
		try {			
			chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msjAgregar,attrs);
			
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}	
		texto.grabFocus();
	}
}
