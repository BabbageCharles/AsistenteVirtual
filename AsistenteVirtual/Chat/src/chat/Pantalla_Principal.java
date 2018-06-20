package chat;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import asistenteVirtual.AsistenteVirtual;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Pantalla_Principal extends JFrame {

	private JPanel contentPane;		
	private JTabbedPane tabbedPane;	
	private Usuario user;	
	//esto es para cargar la lista de salas
	DefaultListModel listmodel= new DefaultListModel();
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_Principal frame = new Pantalla_Principal();
					frame.setVisible(true);	
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pantalla_Principal(Usuario user) {
		this.user = user;
		setTitle("CHAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//************panel donde estan incluidas las pesta�as************
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_408410949369167");
		
		//***********pesta�a de las salas*****************
		
		JPanel panel_sala = new JPanel();
		tabbedPane.addTab("Salas de chat", null, panel_sala, null);
		
		JSeparator separator = new JSeparator();		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblListaDeSalas = new JLabel("Lista de Salas");
		
		JScrollPane scrollPane = new JScrollPane();		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		//base de salas
		List<Sala>salas= ServidorChat.levantarSalas();
		for(int i=0;i<salas.size();i++) {
			listmodel.addElement(salas.get(i).getNombre());
		}
		
		list.setModel(listmodel);
		
		JButton btnAgregarSala = new JButton("Agregar Sala");
		btnAgregarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarSala salaNue= new AgregarSala(list,listmodel);
				salaNue.setVisible(true);	
				salaNue.pack();				
				salaNue.setLocation(450, 200);
							
			}
		});		
		
		JButton btnNewButton = new JButton("Agregar Sala Privada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnIngresarASala = new JButton("Ingresar a Sala");
		btnIngresarASala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue() != null) {	
					String nombSala= list.getSelectedValue().toString();
					int index = tabbedPane.indexOfTab(nombSala);
					if (index < 0) {
					    generarPantallaSala(nombSala);
					}
					
				}
			}
		});
		
		
		
		GroupLayout gl_panel_sala = new GroupLayout(panel_sala);
		gl_panel_sala.setHorizontalGroup(
			gl_panel_sala.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
				.addGroup(gl_panel_sala.createSequentialGroup()
					.addGap(171)
					.addComponent(lblListaDeSalas, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addGap(183))
				.addGroup(gl_panel_sala.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAgregarSala, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(42)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(btnIngresarASala, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_sala.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_sala.setVerticalGroup(
			gl_panel_sala.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_sala.createSequentialGroup()
					.addGap(7)
					.addComponent(lblListaDeSalas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_sala.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregarSala)
						.addComponent(btnIngresarASala)
						.addComponent(btnNewButton))
					.addGap(6))
		);
		
		panel_sala.setLayout(gl_panel_sala);
		
		//**************pesta�a del asistente**************
		
		JPanel panel_asistente = new JPanel();
		tabbedPane.addTab("Asistente", null, panel_asistente, null);	
		
		JTextField textFieldAsist = new JTextField();		
		textFieldAsist.setColumns(10);
		
		JScrollPane scrollPaneAsist = new JScrollPane();
		JTextPane textPaneAsist = new JTextPane();
		textPaneAsist.setEditable(false);
		scrollPaneAsist.setViewportView(textPaneAsist);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto =textFieldAsist.getText();
				if(texto.contains("@jenkins"))
					invocarAsistente(textFieldAsist,textPaneAsist);	
				else
					textFieldAsist.setText("@jenkins "+texto);
			}
		});
		
		textFieldAsist.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				int code = evt.getKeyCode();				
				if(code == KeyEvent.VK_ENTER) {
					String texto =textFieldAsist.getText();
					if(texto.contains("@jenkins"))
						invocarAsistente(textFieldAsist,textPaneAsist);	
					else
						textFieldAsist.setText("@jenkins "+texto);
				}
			}
		});
		
		
		
		GroupLayout gl_panel_asistente = new GroupLayout(panel_asistente);
		gl_panel_asistente.setHorizontalGroup(
			gl_panel_asistente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_asistente.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_asistente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_asistente.createSequentialGroup()
							.addComponent(scrollPaneAsist, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_asistente.createSequentialGroup()
							.addComponent(textFieldAsist, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEnviar)
							.addGap(6))))
		);
		gl_panel_asistente.setVerticalGroup(
			gl_panel_asistente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_asistente.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneAsist, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel_asistente.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldAsist, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(btnEnviar))
					.addGap(14))
		);
		
		
		panel_asistente.setLayout(gl_panel_asistente);
		
		//************pesta�a de lista de amigos**************
		
		JPanel panel_amigos = new JPanel();
		tabbedPane.addTab("Amigos", null, panel_amigos, null);
		GroupLayout gl_panel_amigos = new GroupLayout(panel_amigos);
		gl_panel_amigos.setHorizontalGroup(
			gl_panel_amigos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 419, Short.MAX_VALUE)
		);
		gl_panel_amigos.setVerticalGroup(
			gl_panel_amigos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 223, Short.MAX_VALUE)
		);
		panel_amigos.setLayout(gl_panel_amigos);		
		
	}
	
	

	public void generarPantallaSala(String nomSala) {
		JPanel panel_Chat = new JPanel();
		tabbedPane.addTab(nomSala, null,panel_Chat, null);	
		
		JTextField textFieldChat = new JTextField();		
		textFieldChat.setColumns(10);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		JTextPane textPaneChat = new JTextPane();
		textPaneChat.setEditable(false);
		scrollPaneChat.setViewportView(textPaneChat);
		
		JButton btnEnviar_Chat = new JButton("Enviar");
		btnEnviar_Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							
			}
		});
		
		textFieldChat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				int code = evt.getKeyCode();				
				if(code == KeyEvent.VK_ENTER) {
					invocarAsistente(textFieldChat,textPaneChat);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("A�adir amigos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_2 = new JButton("Salir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
		});
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel_Chat);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textFieldChat, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEnviar_Chat)
							.addGap(6))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
							.addComponent(btnNewButton_2)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneChat, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldChat, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(btnEnviar_Chat))
					.addGap(14))
		);		
		
		panel_Chat.setLayout(gl_panel);
		
	}
	
	public void invocarAsistente(JTextField textField, JTextPane textPane) {
		String texto= textField.getText();
		SimpleAttributeSet attrs = new SimpleAttributeSet();		
		AsistenteVirtual asist = new AsistenteVirtual(user.getUsuario());		
		try {
			
			if(texto.contains("@jenkins")) {
				textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),texto+"\n",attrs);
				if(texto.contains("9gag")) {
					textPane.setCaretPosition(textPane.getStyledDocument().getLength());
					Image image = null;
					URL url = new URL(asist.escuchar(texto,user.getUsuario()));				
					System.out.println(url.toString());
					image = ImageIO.read(url);
					ImageIcon etiqueta= new ImageIcon(image);
					textPane.insertIcon(etiqueta);					
					textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),"\n",attrs);					
									
				}else if(texto.contains("meme")) {
					textPane.setCaretPosition(textPane.getStyledDocument().getLength());
					ImageIcon etiqueta= obtenerImagenEnBD(texto.toLowerCase());
					if(etiqueta == null)
						textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),"Lo siento, no conosco ese meme \n",attrs);
					else {
						textPane.insertIcon(etiqueta);
						textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),"\n",attrs);
					}
					
				}else {
					String respuesta = asist.escuchar(texto,user.getUsuario());
					textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(),respuesta+"\n",attrs);
				}
				
				textField.setText(null);
			}			

		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}
	
	public static ImageIcon obtenerImagenEnBD(String nombre) {		
		
		List<Meme>m=ServidorChat.levantarMeme(nombre);	
		int i=0;		
		
		while((i<m.size())) {							
			if(nombre.contains(m.get(i).getNombre()))	
				break;
			else
				i++;
		}
			
		if(i < m.size()) {
			byte[] b = m.get(i).getImagen();
			Image img = Toolkit.getDefaultToolkit().createImage(b);
			ImageIcon icon = new ImageIcon(img);
			return icon;
		}
		
		return null;		
	}	
}