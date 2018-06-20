
package chat;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class VentanaConfiguracion extends JDialog{
    
    private JTextField tfUsuario;
    private JTextField tfHost;
    private JTextField tfPuerto;
    private JPasswordField tfContrase�a;
    
     public VentanaConfiguracion() {
        this.setModal(true);
        JLabel lbUsuario = new JLabel("Usuario:");
        JLabel lbHost = new JLabel("Host:");
        JLabel lbPuerto = new JLabel("Puerto:");
        JLabel lbContrase�a = new JLabel("Contarse�a:");
        
        tfUsuario = new JTextField();
        tfHost = new JTextField("localhost");
        tfHost.setColumns(10);
        tfPuerto = new JTextField("1234");
        tfPuerto.setColumns(10);
        tfContrase�a = new JPasswordField();
        tfContrase�a.setColumns(10);
        
        
        JButton btAceptar = new JButton("Aceptar");
        btAceptar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);//cuando apretas aceptar, se deja de ver la ventana
            }
        });
        
        Container c = this.getContentPane();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(lbContrase�a)
        			.addGap(18)
        			.addComponent(tfContrase�a, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(67)
        			.addComponent(btAceptar))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(30)
        					.addComponent(lbUsuario))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(37)
        					.addComponent(lbHost))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(32)
        					.addComponent(lbPuerto)))
        			.addGap(30)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(tfHost, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
        				.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
        				.addComponent(tfPuerto, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(lbUsuario))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(14)
        					.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(lbHost))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(14)
        					.addComponent(tfHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(lbPuerto))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(14)
        					.addComponent(tfPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(lbContrase�a))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(tfContrase�a, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(20)
        			.addComponent(btAceptar))
        );
        getContentPane().setLayout(groupLayout);
        
        this.pack(); // Le da a la ventana el minimo tamaño posible
        this.setLocation(450, 200); // Posicion de la ventana
        this.setResizable(false); // Evita que se pueda estirar la ventana
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Deshabilita el boton de cierre de la ventana 
        this.setVisible(true);
    }
    
    
    public String getUsuario(){
        return this.tfUsuario.getText();
    }
    
    public String getHost(){
        return this.tfHost.getText();
    }
    
    public int getPuerto(){
        return Integer.parseInt(this.tfPuerto.getText());
    }


	public String getPassword() {
		return this.tfContrase�a.getText();
	}

}