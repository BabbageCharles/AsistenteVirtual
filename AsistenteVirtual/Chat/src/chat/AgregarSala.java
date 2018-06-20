package chat;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarSala extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNombreDeLa;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		try {
			AgregarSala dialog = new AgregarSala();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarSala(JList list, DefaultListModel listmodel) {
		setAutoRequestFocus(false);
		//this.pack();
		//this.setLocation(450, 200);
		setTitle("Agregar nueva sala");
		setBounds(100, 100, 448, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNombreDeLa = new JLabel("Nombre de la nueva Sala");
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
		}
		{
			
			
			okButton = new JButton("Agregar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textField.getText().length()>0) {
						listmodel.addElement(textField.getText());
						list.setModel(listmodel);
						ServidorChat.agregarSala(textField.getText());
						dispose();
					}else {
						MostrarError error = new MostrarError("Por favor ingrese el nombre de la sala");
						error.setVisible(true);
						error.pack();
					}
					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(23)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(95)
							.addComponent(lblNombreDeLa)))
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(49)
					.addComponent(okButton)
					.addPreferredGap(ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
					.addComponent(cancelButton)
					.addGap(50))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombreDeLa)
					.addGap(44)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}