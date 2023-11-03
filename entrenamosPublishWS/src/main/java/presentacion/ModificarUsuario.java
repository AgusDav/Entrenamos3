package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import datatypes.DtActividadDeportiva;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.UsuarioRepetidoException;
import interfaces.IControlador;
import javax.swing.JTextPane;

public class ModificarUsuario extends JInternalFrame {
	private IControlador icon;
	private JDialog dialogoPadre;
	private JComboBox<String> comboBoxUsuario;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextPane textPaneUsuario;
	private JDateChooser fecNac;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldDescripcion;
	private JTextField textFieldBiografia;
	private JTextField textFieldSitioWeb;
	private JLabel lblDescripcion;
	private JLabel lblSitioWeb;
	private JLabel lblBiografia;
	
	
	public ModificarUsuario(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
				setTitle("Modificar Usuario");
				this.icon = icon;
				this.dialogoPadre = dialogoPadre;
				setBounds(100, 100, 504, 533);
				getContentPane().setBackground(new Color(54, 61, 75));
				getContentPane().setLayout(null);
				
				JLabel lblUsuario = new JLabel("Usuario ");
				lblUsuario.setForeground(new Color(255, 255, 255));
				lblUsuario.setBounds(23, 18, 83, 20);
				getContentPane().add(lblUsuario);
				
				comboBoxUsuario = new JComboBox<String>();
				comboBoxUsuario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actulizarInfoUsuario();
					}
				});
				comboBoxUsuario.setBackground(new Color(54, 61, 75));
				comboBoxUsuario.setForeground(new Color(255, 255, 255));
				comboBoxUsuario.setBounds(174, 16, 135, 24);
				getContentPane().add(comboBoxUsuario);
				
				// Campo nombre
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setForeground(new Color(255, 255, 255));
				lblNombre.setBounds(12, 204, 70, 15);
				getContentPane().add(lblNombre);
				
				textFieldNombre = new JTextField();
		        textFieldNombre.setBounds(159, 202, 150, 19);
		        getContentPane().add(textFieldNombre);
		        textFieldNombre.setColumns(10);
				
		        // Campo apellido
				JLabel lblApellido = new JLabel("Apellido");
				lblApellido.setForeground(new Color(255, 255, 255));
				lblApellido.setBounds(12, 237, 70, 15);
				getContentPane().add(lblApellido);
				
				textFieldApellido = new JTextField();
		        textFieldApellido.setBounds(159, 233, 150, 19);
		        getContentPane().add(textFieldApellido);
		        textFieldApellido.setColumns(10);
		        
				// Campo fecha nacimiento
				JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
				lblFechaNacimiento.setForeground(new Color(255, 255, 255));
				lblFechaNacimiento.setBounds(12, 264, 139, 15);
				getContentPane().add(lblFechaNacimiento);
				
				fecNac = new JDateChooser();
		        fecNac.setBounds(159, 260, 150, 19);
		        getContentPane().add(fecNac);
		        
		     // Botón aceptar
		        JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarUsuarioAceptarActionPerformed(e);
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
				btnAceptar.setBounds(27, 399, 124, 37);
				getContentPane().add(btnAceptar);
				
				// Botón cancelar
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarUsuarioCancelar(e);
					}
				});
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.setBackground(new Color(54, 61, 75));
				btnCancelar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
				btnCancelar.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseEntered(MouseEvent e) {
		            	btnCancelar.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
		            }
		            @Override
		            public void mouseExited(MouseEvent e) {
		            	btnCancelar.setBackground(new Color(54, 61, 75)); // Original color when not hovering
		            }
		        });
				btnCancelar.setBounds(174, 399, 117, 37);
				getContentPane().add(btnCancelar);
				
				textPaneUsuario = new JTextPane();
				textPaneUsuario.setForeground(new Color(255, 255, 255));
				textPaneUsuario.setBackground(new Color(54, 61, 75));
				textPaneUsuario.setBounds(33, 52, 258, 138);
				getContentPane().add(textPaneUsuario);
				
				lblDescripcion = new JLabel("Descripcion:");
				lblDescripcion.setForeground(Color.WHITE);
				lblDescripcion.setBounds(12, 291, 139, 15);
				getContentPane().add(lblDescripcion);
				
				lblBiografia = new JLabel("Biografia:");
				lblBiografia.setForeground(Color.WHITE);
				lblBiografia.setBounds(12, 318, 129, 15);
				getContentPane().add(lblBiografia);
				
				lblSitioWeb = new JLabel("Sitio Web:");
				lblSitioWeb.setForeground(Color.WHITE);
				lblSitioWeb.setBounds(12, 353, 129, 15);
				getContentPane().add(lblSitioWeb);
				
				textFieldDescripcion = new JTextField();
				textFieldDescripcion.setColumns(10);
				textFieldDescripcion.setBounds(159, 289, 150, 19);
				getContentPane().add(textFieldDescripcion);
				
				textFieldBiografia = new JTextField();
				textFieldBiografia.setColumns(10);
				textFieldBiografia.setBounds(159, 320, 150, 19);
				getContentPane().add(textFieldBiografia);
				
				textFieldSitioWeb = new JTextField();
				textFieldSitioWeb.setColumns(10);
				textFieldSitioWeb.setBounds(159, 351, 150, 19);
				getContentPane().add(textFieldSitioWeb);
	}
	
	public void inciarComboBoxUsuario() {
		DefaultComboBoxModel<String> modelUsuarios = new DefaultComboBoxModel<String>(icon.listarUsuarios());
		comboBoxUsuario.setModel(modelUsuarios);
		actulizarInfoUsuario();
	}
	
	
	
	protected void agregarUsuarioAceptarActionPerformed(ActionEvent arg0) {
		if(this.comboBoxUsuario.getSelectedItem() != null) {
			String nick = this.comboBoxUsuario.getSelectedItem().toString();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			DtUsuario us = icon.obtenerUsuario(this.comboBoxUsuario.getSelectedItem().toString());
			Date fecha = fecNac.getDate();
			if(nombre.isEmpty() || apellido.isEmpty() || fecha == null){
	        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
			}else {
				if(us instanceof DtSocio)
					icon.ModificarUsuario(nick, nombre, apellido, fecha , null , null , null);
				if(us instanceof DtProfesor) {
					String Descripcion = this.textFieldDescripcion.getText();
					String Biografia = this.textFieldBiografia.getText();
					String Sitio = this.textFieldSitioWeb.getText();
					icon.ModificarUsuario(nick, nombre, apellido, fecha , Descripcion , Biografia , Sitio);
				}
	    		JOptionPane.showMessageDialog(this, "El usuario se ha modificado con éxito", "Modificar Usuario ", JOptionPane.INFORMATION_MESSAGE);
	    		limpiar();
	    		dialogoPadre.dispose();
	            
	    	}
		}else
			JOptionPane.showMessageDialog(this, "No hay usuarios", "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
	}
    
	private void actulizarInfoUsuario() {
		textPaneUsuario.setText("");
		if(this.comboBoxUsuario.getSelectedItem() != null) {
			DtUsuario us = icon.obtenerUsuario(this.comboBoxUsuario.getSelectedItem().toString());
			String datos = "";
			if(us instanceof DtProfesor) {
				DtProfesor usP = (DtProfesor)us;
				datos = "INFORMACION PROFESOR" + "\n\nNombre:  " + us.getNombre() + "\nApellido:  " + us.getApellido() + "\nFecha de Spawn:  " + us.getFecNac() + "\nDescripcion:  " + usP.getDescripcion() + "\nBiografia:  " + usP.getBiografia() + "\nSitioWeb:  " + usP.getSitioWeb();
				textFieldDescripcion.setVisible(true); // Ocultar el JTextField
	            textFieldSitioWeb.setVisible(true);
	            textFieldBiografia.setVisible(true);
	            lblDescripcion.setVisible(true);
	            lblSitioWeb.setVisible(true);
	            lblBiografia.setVisible(true);
			}else {
				datos = "INFORMACION SOCIO" + "\n\nNombre:  " + us.getNombre() + "\nApellido:  " + us.getApellido() + "\nFecha de Spawn:  " + us.getFecNac();
				textFieldDescripcion.setVisible(false); // Ocultar el JTextField
	            textFieldSitioWeb.setVisible(false);
	            textFieldBiografia.setVisible(false);
	            lblDescripcion.setVisible(false);
	            lblSitioWeb.setVisible(false);
	            lblBiografia.setVisible(false);
			}
			textPaneUsuario.setText(datos);
		}
		
	}
	
	private void limpiar() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldDescripcion.setText("");
		textFieldSitioWeb.setText("");
		textFieldBiografia.setText("");
		fecNac.setDate(null);
	}
	
	// Cancela el caso de uso
	protected void agregarUsuarioCancelar(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
}
