package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import interfaces.IControlador;

public class LogIn extends JInternalFrame {
private static final long serialVersionUID = 1L;
	
	private IControlador icon;
	private JDialog dialogoPadre;
	private JTextField textFieldNick;
	private JTextField textFieldPassword;
	
	
	public LogIn(IControlador icon, JDialog dialogoPadre) {
		setTitle("Agregar Usuario");
		this.dialogoPadre = dialogoPadre;
		this.icon = icon;
		setBounds(100, 100, 445, 463);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(54, 61, 75));;
		
		// Campo nickname
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(new Color(255, 255, 255));
		lblNickname.setBounds(12, 22, 70, 15);
		getContentPane().add(lblNickname);
		
		textFieldNick = new JTextField();
        textFieldNick.setBounds(160, 20, 150, 19);
        getContentPane().add(textFieldNick);
        textFieldNick.setColumns(10);
        
        JLabel lblpass = new JLabel("Password:");
		lblpass.setForeground(new Color(255, 255, 255));
		lblpass.setBounds(12, 72, 102, 15);
		getContentPane().add(lblpass);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(160, 70, 150, 19);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		// Botón aceptar
        JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logInAceptarActionPerformed(e);
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(new Color(54, 61, 75));
		btnAceptar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
		btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAceptar.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAceptar.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
		btnAceptar.setBounds(12, 319, 124, 37);
		getContentPane().add(btnAceptar);
		
	}
	
	protected void logInAceptarActionPerformed(ActionEvent arg0) {
		if(icon.logIn(this.textFieldNick.getText(),this.textFieldPassword.getText())){
			JOptionPane.showMessageDialog(this, "Log In exitoso", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
