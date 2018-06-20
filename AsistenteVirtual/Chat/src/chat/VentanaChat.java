package chat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Scrollbar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class VentanaChat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea;
	private Scrollbar scrollbar;

	/**
	 * Create the frame.
	 */
	public VentanaChat(ClienteChat cliente) {
		setAutoRequestFocus(false);
		setTitle("Sala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 423);
		contentPane = new JPanel();
//		contentPane.setPreferredSize(new Dimension(400, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{236, 83, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{348, 30, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setWrapStyleWord(true);
		textArea.setEnabled(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridwidth = 2;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		contentPane.add(textArea, gbc_textArea);
		
		scrollbar = new Scrollbar();
		GridBagConstraints gbc_scrollbar = new GridBagConstraints();
		gbc_scrollbar.insets = new Insets(0, 0, 5, 5);
		gbc_scrollbar.gridx = 3;
		gbc_scrollbar.gridy = 0;
		contentPane.add(scrollbar, gbc_scrollbar);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ConexionServidor(cliente.getSocket(), textField, cliente.getUser().getUsuario()));
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnviar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 2;
		contentPane.add(btnEnviar, gbc_btnEnviar);
		
		
	}
}
