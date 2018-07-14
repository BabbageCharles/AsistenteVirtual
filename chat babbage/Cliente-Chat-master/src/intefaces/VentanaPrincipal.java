package intefaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import cliente.Cliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;
import paqueteEnvios.PaqueteSala;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private String user = null;
	private Cliente cliente;

	private static JList<String> listaUsuariosChatGeneral = new JList<String>();
	private static JList<String> listaSalas = new JList<String>();
	private static SimpleAttributeSet attrs = new SimpleAttributeSet();	

	private static JTextPane chat;

	public VentanaPrincipal(Cliente cli) {


		cliente = cli;
		user = cliente.getPaqueteUsuario().getUsername();
		setTitle("Chat Babbage");
		setBounds(100, 100, 673, 537);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cliente) {
						cliente.setAccion(Comando.DESCONECTAR);
						cliente.notify();
					}
					dispose();
				}
			}
		});

		JPanel contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel labelUsuario = new JLabel("Mi Usuario: ");
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelUsuario.setForeground(Color.BLACK);

		JScrollPane panelListaUsuarios = new JScrollPane();
		panelListaUsuarios.setViewportView(listaUsuariosChatGeneral);
		listaUsuariosChatGeneral.setForeground(Color.BLACK);
		listaUsuariosChatGeneral.setBackground(Color.WHITE);

		listaUsuariosChatGeneral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (listaUsuariosChatGeneral.getSelectedValue() != null) {
						if (!cliente.getChatsActivos().containsKey(listaUsuariosChatGeneral.getSelectedValue())) {
							if (cliente != null) {
								Chat chat = new Chat(cliente);
								cliente.getChatsActivos().put(listaUsuariosChatGeneral.getSelectedValue(), chat);
								chat.setTitle(listaUsuariosChatGeneral.getSelectedValue());
							}
						}
					}
				}
			}
		});


		JScrollPane panelListaSalas = new JScrollPane();
		panelListaSalas.setViewportView(listaSalas);
		listaSalas.setForeground(Color.BLACK);
		listaSalas.setBackground(Color.WHITE);



		listaSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (listaSalas.getSelectedValue() != null) {
						if (!cliente.getSalasActivas().containsKey(listaSalas.getSelectedValue())) {
							if(listaSalas.getSelectedValue().contains("Privada")) {
								
								new IngresoSalaPrivada(listaSalas.getSelectedValue(),cliente);									
								
							}								
							else {
								PaqueteSala paqueteSala = new PaqueteSala(listaSalas.getSelectedValue(),cliente.getPaqueteUsuario().getUsername());
								cliente.setPaqueteSala(paqueteSala);
								synchronized (cliente) {
									cliente.setAccion(Comando.ENTRARSALA);
									cliente.notify();
								}
								
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "Ya se encuentra en esta sala.");
						}
					}
				}
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();

		chat = new JTextPane();
		chat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode() == KeyEvent.VK_A) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"Eres un elegido\n",attrs);
						chat.setCaretPosition(chat.getStyledDocument().getLength());
						Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/resources/MemeProhibido.jpg"));				
						chat.insertIcon(new ImageIcon(image));
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"\n",attrs);						
						
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		chat.setEditable(false);
		chat.setEnabled(true);
		chat.setForeground(Color.BLACK);
		chat.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(chat);

		JTextField texto = new JTextField();
		texto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode() == KeyEvent.VK_A) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"Eres un elegido\n",attrs);
						chat.setCaretPosition(chat.getStyledDocument().getLength());
						Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/resources/MemeProhibido.jpg"));				
						chat.insertIcon(new ImageIcon(image));
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"\n",attrs);						
						
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		texto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarMsjAServidor(texto.getText());
				texto.setText("");
				texto.grabFocus();
			}
		});

		texto.setEditable(true);
		texto.setForeground(Color.BLACK);
		texto.setBackground(Color.WHITE);
		texto.setCaretColor(Color.BLACK);
		texto.setColumns(10);

		JButton enviarATodos = new JButton("Enviar a Todos");
		enviarATodos.setEnabled(true);
		enviarATodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMsjAServidor(texto.getText());
				texto.setText("");
				texto.grabFocus();
			}

		});




		JLabel labelNombreUsuario = new JLabel(user);
		labelNombreUsuario.setForeground(Color.BLACK);
		labelNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel labelUsuariosConectados = new JLabel("Usuarios Online");
		labelUsuariosConectados.setForeground(Color.BLACK);
		labelUsuariosConectados.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel labelSalas = new JLabel("Salas Disponibles");
		labelSalas.setForeground(Color.BLACK);
		labelSalas.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuCreacionSala(cliente);
			}
		});
		btnCrearSala.setEnabled(true);

		JButton btnNewButton = new JButton("Crear Sala Privada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuCreacionSalaPrivada(cliente);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(64)
											.addComponent(labelNombreUsuario, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
										.addComponent(labelUsuario, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(labelUsuariosConectados, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addComponent(panelListaUsuarios, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(labelSalas, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addComponent(panelListaSalas, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCrearSala, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(texto, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(enviarATodos, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelNombreUsuario, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUsuario, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addComponent(labelUsuariosConectados, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(panelListaUsuarios, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addGap(22)
							.addComponent(labelSalas, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(panelListaSalas, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCrearSala)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(texto, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(enviarATodos, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir del Chat?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}	

	public static JList<String> getListConectados() {
		return listaUsuariosChatGeneral;
	}

	public static JList<String> getListSalas() {
		return listaSalas;
	}

	public static void setTextoChatGeneral (String msj) { 
		try {			
			String res = "-";
			if(msj.contains("http")){
				res = msj.substring(msj.indexOf("http"));				
				System.out.println(res);
				if(msj.contains("Fuente")) 
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msj,attrs);
				
				if(msj.contains("Titulo") || msj.contains("Rss"))
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msj,attrs);
				
				if(res.contains("9gag")) {
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"@Jenkins: Aqui tienes\n",attrs);
					chat.setCaretPosition(chat.getStyledDocument().getLength());					
					URL url = new URL(res);						
					chat.insertIcon(new ImageIcon(url));
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"\n",attrs);
				}
				
				if(res.contains("giphy")){
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"@Jenkins: Aqui tienes\n",attrs);
					chat.setCaretPosition(chat.getStyledDocument().getLength());				
					URL url;
					url = new URL(res);					
					chat.insertIcon(new ImageIcon(url));
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"\n",attrs);
				}
				
				if(msj.contains("meme")) {
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"@Jenkins: Aqui tienes\n",attrs);
					chat.setCaretPosition(chat.getStyledDocument().getLength());
					URL url;
					url = new URL(res);						
					chat.insertIcon(new ImageIcon(url));
					chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),"\n",attrs);
				}
				
			}else {
				chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),msj,attrs);
			}
			
						
		} catch (BadLocationException | IOException e) {
			
			e.printStackTrace();
		}	
		
		
	}

	public static void eliminarConectados() {
		listaUsuariosChatGeneral.removeAll();
	}

	public static void cambiarModelo(DefaultListModel<String> modelo) {
		listaUsuariosChatGeneral.setModel(modelo);
	}

	public static void eliminarSalas() {
		listaSalas.removeAll();
	}

	public static void cambiarModeloSalas(DefaultListModel<String> modelo) {
		listaSalas.setModel(modelo);		
	}
	
	public void enviarMsjAServidor(String msj) {
		if(!msj.equals("")) {
			if(msj.startsWith("/")) {
				String[] words;
				words = msj.substring(1).split(" ", 2);
				if (words.length > 1 && words[1] != "") {
					words[1] = words[1].trim();
					
					if(cliente.getPaqueteUsuario().getListaDeConectados().contains(words[0]) && !words[0].equals(cliente.getPaqueteUsuario().getUsername())){
						cliente.setAccion(Comando.MP);
						PaqueteMensaje paqueteMsj = new PaqueteMensaje(cliente.getPaqueteUsuario().getUsername(),words[0],words[1],null);
						cliente.setPaqueteMensaje(paqueteMsj);

						if(cliente.getChatsActivos().containsKey(words[0])){
							String msjAgregar = cliente.getPaqueteUsuario().getUsername() + ": " + words[1] + "\n";
							cliente.getChatsActivos().get(words[0]).agregarMsj(msjAgregar);
						} else {
							Chat chatPropio = new Chat(cliente);

							chatPropio.setTitle(words[0]);

							cliente.getChatsActivos().put(words[0], chatPropio);
							String msjAgregar = cliente.getPaqueteUsuario().getUsername() + ": " + words[1] + "\n";
							chatPropio.agregarMsj(msjAgregar);
						}
						synchronized (cliente) {
							cliente.notify();
						}
					}

				} else if(words.length<=1 && !words[0].equals(cliente.getPaqueteUsuario().getUsername())){
					Chat chatPropio = new Chat(cliente);
					chatPropio.setTitle(words[0]);
					cliente.getChatsActivos().put(words[0], chatPropio);
				}
				
			} else {
				if (msj.startsWith("@") || msj.contains(" @")) { 
					String res =cliente.getPaqueteUsuario().getUsername() + ": " + msj + "\n";
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),res,attrs);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// chat.append(cliente.getPaqueteUsuario().getUsername() + "---: " + msj + "\n");

					String mensaje;
					String[] mensajeArray;
					mensaje = msj;
					int pos = mensaje.indexOf("@") + 1;
					mensajeArray = mensaje.substring(pos).split(" ", 2);
					if (mensajeArray.length > 1 && mensajeArray[1] != null) {
						mensajeArray[1] = mensajeArray[1].trim();
					}


					if (!mensajeArray[0].equals(cliente.getPaqueteUsuario().getUsername())) {
						cliente.setAccion(Comando.MENCIONSALA);
						PaqueteMensaje paqueteMsj = new PaqueteMensaje(cliente.getPaqueteUsuario().getUsername(),
								mensajeArray[0], msj, "Ventana Principal");
						cliente.setPaqueteMensaje(paqueteMsj);
						synchronized (cliente) {
							cliente.notify();
						} 
					}
				} else {
					String res =cliente.getPaqueteUsuario().getUsername() + ": " + msj + "\n"; // escribe en pantalla lo que yo escribo
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),res,attrs);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//chat.append(cliente.getPaqueteUsuario().getUsername() + " ******: " + msj + "\n");
					cliente.setAccion(Comando.CHATALL);
					PaqueteMensaje paqueteMsj = new PaqueteMensaje(cliente.getPaqueteUsuario().getUsername(), null,
							msj, null);
					cliente.setPaqueteMensaje(paqueteMsj);

					synchronized (cliente) {
						cliente.notify();
					}
				}
			}
		}
		
	}
}
