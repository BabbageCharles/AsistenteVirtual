package intefaces;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

import cliente.Cliente;
import paqueteEnvios.Comando;
import paqueteEnvios.PaqueteMensaje;
import paqueteEnvios.PaqueteSala;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Sala extends JFrame  {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texto;
	private static SimpleAttributeSet attrs = new SimpleAttributeSet();
	private String ownerSala;

	private JLabel lblNombreUsuario;
	private JTextPane chat;

	private String nombreSala;
	private Cliente cli;

	private JList<String> listaConectadosSala = new JList<String>();

	public Sala(Cliente cliente) {
		cli = cliente;
		this.nombreSala = cli.getPaqueteSala().getNombreSala();
		this.ownerSala = cli.getPaqueteSala().getOwnerSala();
		setTitle(nombreSala);
		setBounds(100, 100, 646, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cli) {
						PaqueteSala paqueteSala = new PaqueteSala(nombreSala, cli.getPaqueteUsuario().getUsername());
						cli.setPaqueteSala(paqueteSala);
						cli.setAccion(Comando.DESCONECTARDESALA);
						cli.notify();
					}
					dispose();
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		setContentPane(contentPane);

		JScrollPane scrollPaneChat = new JScrollPane();


		chat = new JTextPane();
		chat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chat.setForeground(Color.BLACK);
		chat.setBackground(Color.WHITE);
		chat.setEditable(false);
		scrollPaneChat.setViewportView(chat);

		JScrollPane scrollPaneConectados = new JScrollPane();
		scrollPaneConectados.setViewportView(listaConectadosSala);
		listaConectadosSala.setForeground(Color.BLACK);
		listaConectadosSala.setBackground(Color.WHITE);
		listaConectadosSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (listaConectadosSala.getSelectedValue() != null) {
						if (!cli.getChatsActivos().containsKey(listaConectadosSala.getSelectedValue())) {
							Chat chat = new Chat(cli);
							cli.getChatsActivos().put(listaConectadosSala.getSelectedValue(), chat);
							chat.setTitle(listaConectadosSala.getSelectedValue());
						}
					}
				}
			}
		});


		texto = new JTextField();
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
			public void actionPerformed(ActionEvent e) {
				enviarAServidor();		
			}
		});
		texto.setForeground(Color.BLACK);
		texto.setBackground(Color.WHITE);
		texto.setCaretColor(Color.BLACK);
		texto.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			enviarAServidor();
			}
		});

		JButton btnDesconectarse = new JButton("Salir de la Sala");
		btnDesconectarse.setBackground(UIManager.getColor("CheckBox.shadow"));
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cli) {
						PaqueteSala paqueteSala = new PaqueteSala(nombreSala, cli.getPaqueteUsuario().getUsername());
						cli.setPaqueteSala(paqueteSala);
						cli.setAccion(Comando.DESCONECTARDESALA);
						cli.notify();
					}
					dispose();	
				}
			}
		});

		JLabel lblUsuario = new JLabel("Usuario ");

		lblNombreUsuario = new JLabel(cli.getPaqueteUsuario().getUsername());
		
		JLabel lblUsuariosConectados = new JLabel("Usuarios conectados");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(47)
									.addComponent(lblNombreUsuario, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPaneConectados, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsuariosConectados))
							.addGap(14)
							.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDesconectarse, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(texto, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
							.addGap(4)
							.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombreUsuario)
								.addComponent(lblUsuario))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblUsuariosConectados)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneConectados, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDesconectarse, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(texto, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(4))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

	public String getName() {
		return nombreSala;
	}

	public void setName(String name) {
		this.nombreSala = name;
	}

	public void setNombreUsuario(String nombre){
		this.lblNombreUsuario.setText(nombre);
	}

	public JList<String> getListaConectadosSala() {
		return listaConectadosSala;
	}
	public  void setListaConectadosSala(JList<String> listaConectadosSala) {
		this.listaConectadosSala = listaConectadosSala;
	}

	public JTextPane getChat() {
		return chat;
	}

	public void setChat(JTextPane chat) {
		this.chat = chat;
	}

	public JTextField getTexto() {
		return texto;
	}

	public void setTexto(JTextField texto) {
		this.texto = texto;
	}

	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir de la sala?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	public String getOwnerSala() {
		return ownerSala;
	}

	public void setOwnerSala(String ownerSala) {
		this.ownerSala = ownerSala;
	}

	public void eliminarConectados() {
		this.listaConectadosSala.removeAll();
	}

	public void cambiarModelo(DefaultListModel<String> modelo) {
		this.listaConectadosSala.setModel(modelo);
	}

	public void agregarMsj(String msj) {
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
		texto.grabFocus();
	}

	public void cargarHistorial(String historial) {
		chat.setText(historial);
	}
	
	public void enviarAServidor() {
		if(!texto.getText().equals("")) {
			if(texto.getText().startsWith("/")) {
				String[] words;
				words = texto.getText().substring(1).split(" ", 2);
				if (words.length > 1 && words[1] != "") {
					words[1] = words[1].trim();
					
					if(cli.getPaqueteUsuario().getListaDeConectados().contains(words[0]) && !words[0].equals(cli.getPaqueteUsuario().getUsername())){
						cli.setAccion(Comando.MP);
						PaqueteMensaje paqueteMsj = new PaqueteMensaje(cli.getPaqueteUsuario().getUsername(),words[0],words[1],null);
						cli.setPaqueteMensaje(paqueteMsj);

						if(cli.getChatsActivos().containsKey(words[0])){
							String msjAgregar = cli.getPaqueteUsuario().getUsername() + ": " + words[1] + "\n";
							cli.getChatsActivos().get(words[0]).agregarMsj(msjAgregar);
						} else {
							Chat chatPropio = new Chat(cli);

							chatPropio.setTitle(words[0]);

							cli.getChatsActivos().put(words[0], chatPropio);
							String msjAgregar = cli.getPaqueteUsuario().getUsername() + ": " + words[1] + "\n";
							chatPropio.agregarMsj(msjAgregar);
						}
						synchronized (cli) {
							cli.notify();
						}
					}

				} else if(words.length<=1 && !words[0].equals(cli.getPaqueteUsuario().getUsername())){
					Chat chatPropio = new Chat(cli);
					chatPropio.setTitle(words[0]);
					cli.getChatsActivos().put(words[0], chatPropio);
				}
				
				texto.setText("");
				texto.requestFocus();
			} else {
				if (texto.getText().startsWith("@") || texto.getText().contains(" @")) {
					String res =cli.getPaqueteUsuario().getUsername() + ": " + texto.getText() + "\n";
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),res,attrs);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 //chat.append(cli.getPaqueteUsuario().getUsername() + ": " + texto.getText() + "\n");

					String mensaje;
					String[] mensajeArray;
					mensaje = texto.getText();
					int pos = mensaje.indexOf("@") + 1;
					mensajeArray = mensaje.substring(pos).split(" ", 2);
					if (mensajeArray.length > 1 && mensajeArray[1] != null) {
						mensajeArray[1] = mensajeArray[1].trim();
					}


					if (!mensajeArray[0].equals(cli.getPaqueteUsuario().getUsername())) {
						cli.setAccion(Comando.MENCIONSALA);
						PaqueteMensaje paqueteMsj = new PaqueteMensaje(cli.getPaqueteUsuario().getUsername(),
								mensajeArray[0], texto.getText(), nombreSala);
						cli.setPaqueteMensaje(paqueteMsj);
						texto.setText("");
						texto.requestFocus();
						synchronized (cli) {
							cli.notify();
						} 
					}
				} else {
					String res =cli.getPaqueteUsuario().getUsername() + " : " + texto.getText() + "\n";
					try {
						chat.getStyledDocument().insertString(chat.getStyledDocument().getLength(),res,attrs);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//chat.append(cli.getPaqueteUsuario().getUsername() + " : " + texto.getText() + "\n");
					cli.setAccion(Comando.CHATSALA);
					PaqueteMensaje paqueteMsj = new PaqueteMensaje(cli.getPaqueteUsuario().getUsername(), null,
							texto.getText(), nombreSala);
					cli.setPaqueteMensaje(paqueteMsj);
					texto.setText("");
					texto.requestFocus();
					synchronized (cli) {
						cli.notify();
					}
				}
			}
		}
	}
}
