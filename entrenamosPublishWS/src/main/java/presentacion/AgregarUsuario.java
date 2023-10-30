package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import excepciones.InstitucionDeportivaRepetidaException;
import excepciones.UsuarioRepetidoException;
import interfaces.IControlador;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class AgregarUsuario extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private IControlador icon;
	private JDialog dialogoPadre;
	private JDateChooser fecNac;
	private JTextField textFieldNick;
	private JTextField textFieldEmail;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDescripcion;
	private JTextField textFieldWeb;
	private JTextField textFieldBio;
	private JTextField textFieldPassword;
	private JComboBox<String> seleccionarTipoUser;
	private JComboBox<String> comboBoxNombreInstitucion;
	
	public AgregarUsuario(IControlador icon, JDialog dialogoPadre) {
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
		
        // Campo email
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(12, 49, 70, 15);
		getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
        textFieldEmail.setBounds(160, 47, 150, 19);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);
		
        // Campo nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(12, 76, 70, 15);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
        textFieldNombre.setBounds(160, 74, 150, 19);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
		
        // Campo apellido
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setBounds(12, 103, 70, 15);
		getContentPane().add(lblApellido);
		
		textFieldApellido = new JTextField();
        textFieldApellido.setBounds(160, 101, 150, 19);
        getContentPane().add(textFieldApellido);
        textFieldApellido.setColumns(10);
        
		// Campo fecha nacimiento
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setForeground(new Color(255, 255, 255));
		lblFechaNacimiento.setBounds(12, 130, 139, 15);
		getContentPane().add(lblFechaNacimiento);
		
		fecNac = new JDateChooser();
        fecNac.setBounds(160, 128, 150, 19);
        getContentPane().add(fecNac);   
		
        // Campo tipo usuario
		JLabel lblTipoUsuario = new JLabel("Tipo Usuario");
		lblTipoUsuario.setForeground(new Color(255, 255, 255));
		lblTipoUsuario.setBounds(12, 166, 102, 15);
		getContentPane().add(lblTipoUsuario);
		
		// Campo descripción
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBounds(12, 367, 102, 15);
		getContentPane().add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(160, 365, 150, 19);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblpass = new JLabel("Password:");
		lblpass.setForeground(new Color(255, 255, 255));
		lblpass.setBounds(12, 199, 102, 15);
		getContentPane().add(lblpass);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(160, 197, 150, 19);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		// Campo sitio web
		JLabel lblWeb = new JLabel("Web");
		lblWeb.setForeground(new Color(255, 255, 255));
		lblWeb.setBounds(12, 226, 70, 15);
		getContentPane().add(lblWeb);
		
		textFieldWeb = new JTextField();
		textFieldWeb.setText("");
		textFieldWeb.setBounds(160, 224, 150, 19);
		getContentPane().add(textFieldWeb);
		textFieldWeb.setColumns(10);
		
		// Campo biografía		
		JLabel lblBiografia = new JLabel("Biografia");
		lblBiografia.setForeground(new Color(255, 255, 255));
		lblBiografia.setBounds(12, 253, 70, 15);
		getContentPane().add(lblBiografia);
				
		textFieldBio = new JTextField();
		textFieldBio.setText("");
		textFieldBio.setBounds(160, 251, 150, 19);
		getContentPane().add(textFieldBio);
		textFieldBio.setColumns(10);
		
		// Campo institucion
		JLabel lblInstitucion = new JLabel("Institucion");
		lblInstitucion.setForeground(Color.WHITE);
		lblInstitucion.setBackground(Color.WHITE);
		lblInstitucion.setBounds(12, 284, 95, 15);
		getContentPane().add(lblInstitucion);

		comboBoxNombreInstitucion = new JComboBox<String>();
		comboBoxNombreInstitucion.setBounds(160, 282, 150, 19);
		getContentPane().add(comboBoxNombreInstitucion);
		
		
		seleccionarTipoUser = new JComboBox<String>();
		seleccionarTipoUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedItem = (String) seleccionarTipoUser.getSelectedItem();
				if ("Socio".equals(selectedItem)) {
		            textFieldDescripcion.setVisible(false); // Ocultar el JTextField
		            textFieldWeb.setVisible(false);
		            textFieldBio.setVisible(false);
		            comboBoxNombreInstitucion.setVisible(false);
		            lblDescripcion.setVisible(false);
		            lblWeb.setVisible(false);
		            lblBiografia.setVisible(false);
		            lblInstitucion.setVisible(false);
		        } else {
		            textFieldDescripcion.setVisible(true); // Mostrar el JTextField
		            textFieldWeb.setVisible(true);
		            textFieldBio.setVisible(true);
		            comboBoxNombreInstitucion.setVisible(true);
		            lblDescripcion.setVisible(true);
		            lblWeb.setVisible(true);
		            lblBiografia.setVisible(true);
		            lblInstitucion.setVisible(true);
		        }
			}
		});
		seleccionarTipoUser.setModel(new DefaultComboBoxModel<String>(new String[] {"Profesor", "Socio"}));
		seleccionarTipoUser.setBounds(160, 161, 150, 24);
		getContentPane().add(seleccionarTipoUser);
		
		
		
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
		btnAceptar.setBounds(12, 319, 124, 37);
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
		btnCancelar.setBounds(193, 319, 117, 37);
		getContentPane().add(btnCancelar);
	}
	
	protected void agregarUsuarioAceptarActionPerformed(ActionEvent arg0) {
		String nick = this.textFieldNick.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String email = this.textFieldEmail.getText();
		String web = this.textFieldWeb.getText();
		String bio = this.textFieldBio.getText();
		String desc = this.textFieldDescripcion.getText();
		String pass = this.textFieldPassword.getText();
		Date fecha = fecNac.getDate();
		String tipoUser = this.seleccionarTipoUser.getSelectedItem().toString();
		DtUsuario dtU = null;
		if (nombre.isEmpty() || nick.isEmpty() || apellido.isEmpty() || fecha == null || (tipoUser.equals("Profesor") && (desc.isEmpty() || bio.isEmpty() || web.isEmpty() || this.comboBoxNombreInstitucion.getSelectedItem() == null))){
        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
            	if(tipoUser.equals("Profesor")) {
					String inst = this.comboBoxNombreInstitucion.getSelectedItem().toString();
        			dtU = new DtProfesor(nick, nombre, apellido, email,pass, fecha, desc, bio, web);
        			this.icon.agregarUsuario(dtU, inst);
        		}else if(tipoUser.equals("Socio")){
        			dtU = new DtSocio(nick, nombre, apellido, email,pass, fecha);
        			this.icon.agregarUsuario(dtU, "");
        		}
            	JOptionPane.showMessageDialog(this, "El usuario se ha creado con éxito", "Agregar Usuario ", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                dialogoPadre.dispose();
            } catch (UsuarioRepetidoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Usuario", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	
	// Cancela el caso de uso
	protected void agregarUsuarioCancelar(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
	
	public void inicializarComboBoxes() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(icon.listarInstitutos());
		comboBoxNombreInstitucion.setModel(modelInstitutos);
	}
	
	private void limpiar() {
		textFieldNick.setText("");
		textFieldEmail.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldDescripcion.setText("");
		textFieldWeb.setText("");
		textFieldBio.setText("");
		fecNac.setDate(null);
	}
}