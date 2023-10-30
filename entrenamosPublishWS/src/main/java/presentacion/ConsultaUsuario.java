package presentacion;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;

import interfaces.IControlador;
import javax.swing.JLabel;

import datatypes.DtClase;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import datatypes.DtActividadDeportiva;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ConsultaUsuario extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JComboBox<String> comboBoxUsuario;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxClase;
	private JTextPane textPaneClase;
	private JTextPane textPaneActDepor;
	private JLabel datoFecNac;
	private JLabel datoNombre;
	private JLabel datoApellido;
	private JLabel datoEmail;
	
	public ConsultaUsuario(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
				setTitle("Consulta Usuario");
				this.icon = icon;
				setBounds(100, 100, 716, 400);
				getContentPane().setBackground(new Color(54, 61, 75));
				getContentPane().setLayout(null);
				
				
				JLabel lblUsuario = new JLabel("Usuario ");
				lblUsuario.setForeground(new Color(255, 255, 255));
				lblUsuario.setBounds(23, 18, 83, 20);
				getContentPane().add(lblUsuario);
				
				comboBoxUsuario = new JComboBox<String>();
				comboBoxUsuario.setBackground(new Color(54, 61, 75));
				comboBoxUsuario.setForeground(new Color(255, 255, 255));
				comboBoxUsuario.setBounds(174, 16, 135, 24);
				comboBoxUsuario.addActionListener(this::comboBoxNicknameActionPerformed);
				getContentPane().add(comboBoxUsuario);
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setForeground(new Color(255, 255, 255));
				lblNombre.setBackground(new Color(255, 255, 255));
				lblNombre.setBounds(23, 61, 70, 15);
				getContentPane().add(lblNombre);
				
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setForeground(Color.WHITE);
				lblApellido.setBackground(Color.WHITE);
				lblApellido.setBounds(23, 88, 70, 15);
				getContentPane().add(lblApellido);
				
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setForeground(Color.WHITE);
				lblEmail.setBackground(Color.WHITE);
				lblEmail.setBounds(23, 115, 70, 15);
				getContentPane().add(lblEmail);
				
				JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
				lblFechaDeNacimiento.setFont(new Font("Dialog", Font.BOLD, 10));
				lblFechaDeNacimiento.setForeground(Color.WHITE);
				lblFechaDeNacimiento.setBackground(Color.WHITE);
				lblFechaDeNacimiento.setBounds(23, 142, 161, 15);
				getContentPane().add(lblFechaDeNacimiento);
				
				datoNombre = new JLabel("");
				datoNombre.setForeground(new Color(255, 255, 255));
				datoNombre.setBounds(174, 61, 115, 15);
				getContentPane().add(datoNombre);
				
				datoApellido = new JLabel("");
				datoApellido.setForeground(Color.WHITE);
				datoApellido.setBounds(174, 88, 115, 15);
				getContentPane().add(datoApellido);
				
				datoEmail = new JLabel("");
				datoEmail.setForeground(Color.WHITE);
				datoEmail.setBounds(174, 115, 115, 15);
				getContentPane().add(datoEmail);
				
				datoFecNac = new JLabel("");
				datoFecNac.setForeground(Color.WHITE);
				datoFecNac.setBounds(174, 141, 115, 15);
				getContentPane().add(datoFecNac);
				
				JLabel lblClases = new JLabel("Clases:");
				lblClases.setForeground(Color.WHITE);
				lblClases.setBounds(23, 169, 83, 20);
				getContentPane().add(lblClases);
				
				comboBoxClase = new JComboBox<String>();
				comboBoxClase.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actualizarInfoClase();
					}
				});
				comboBoxClase.setForeground(Color.WHITE);
				comboBoxClase.setBackground(new Color(54, 61, 75));
				comboBoxClase.setBounds(174, 168, 135, 24);
				getContentPane().add(comboBoxClase);
				
				textPaneClase = new JTextPane();
				textPaneClase.setForeground(new Color(255, 255, 255));
				textPaneClase.setBackground(new Color(54, 61, 75));
				textPaneClase.setBounds(353, 18, 149, 230);
				getContentPane().add(textPaneClase);
				
				textPaneActDepor = new JTextPane();
				textPaneActDepor.setForeground(new Color(255, 255, 255));
				textPaneActDepor.setBackground(new Color(54, 61, 75));
				textPaneActDepor.setBounds(514, 18, 149, 230);
				getContentPane().add(textPaneActDepor);
				
				JLabel lblActividadDeportiva = new JLabel("Actividad Deportiva:");
				lblActividadDeportiva.setForeground(Color.WHITE);
				lblActividadDeportiva.setBounds(23, 209, 161, 20);
				getContentPane().add(lblActividadDeportiva);
				
				comboBoxActividad = new JComboBox<String>();
				comboBoxActividad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actualizarInfoActividad();
					}
				});
				comboBoxActividad.setForeground(Color.WHITE);
				comboBoxActividad.setBackground(new Color(54, 61, 75));
				comboBoxActividad.setBounds(174, 209, 135, 24);
				getContentPane().add(comboBoxActividad);
	}
	
	private void comboBoxNicknameActionPerformed(ActionEvent evt) {
	    String nick = (String) comboBoxUsuario.getSelectedItem();
	    
	    if (nick != null) {
	        DtUsuario dtu = icon.obtenerUsuario(nick);
	        datoNombre.setText(dtu.getNombre());
	        datoApellido.setText(dtu.getApellido());
	        datoEmail.setText(dtu.getEmail());
	        datoFecNac.setText(dtu.getFecNac().toString());
	        if(dtu instanceof DtSocio) {
	        	comboBoxActividad.removeAllItems();
	        	actualizarComboBoxClase();
	        	
	        	
	        }else {
	        	actualizarComboBoxClase();
	        	actualizarComboBoxActividad();
	        }
	    }
	}
	
	private void actualizarComboBoxClase() {
		if(this.comboBoxUsuario.getSelectedItem() != null) {
			DefaultComboBoxModel<String> modelClase = new DefaultComboBoxModel<String>(icon.obtenerUsuarioReal(this.comboBoxUsuario.getSelectedItem().toString()).obtenerClases());
			comboBoxClase.setModel(modelClase);
			}else
				comboBoxClase.removeAllItems();
			actualizarInfoClase();	
			
	}
	
	private void actualizarInfoClase() {
		textPaneClase.setText("");
		if(this.comboBoxClase.getSelectedItem() != null) {
			DtClase cls = icon.obtenerClase(this.comboBoxClase.getSelectedItem().toString());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = sdf.format(cls.getFechaReg());
			String datos = "INFORMACION CLASE" + "\n\nNombre:  " + cls.getNombre() + "\nHora de Inicio:  " + cls.getHoraInicio() + "\nUrl:  " + cls.getUrl() + "\nFecha Registro:  " + fecha;
			textPaneClase.setText(datos);
		}
		 
	}
	
	/*String[] clas = obtenerElementosComboBoxClase(comboBoxClase);
    
    // Crear un conjunto para almacenar las actividades únicas
    Set<String> actividadesSet = new HashSet<>();
    
    // Llenar el conjunto con actividades únicas
    for (int i = 0; i < clas.length; i++) {
        DtClase clase = icon.obtenerClaseR(clas[i]);
        String actividadNombre = clase.getActividad().getNombre();
        actividadesSet.add(actividadNombre);
    }
    
    // Convertir el conjunto en un arreglo de strings
    String[] actividades = actividadesSet.toArray(new String[0]);*/
	
	private void actualizarComboBoxActividad() {
		if(this.comboBoxUsuario.getSelectedItem() != null && this.comboBoxClase.getSelectedItem() != null) {
				String[] clas = obtenerElementosComboBoxClase(comboBoxClase);
				
				 // Crear un conjunto para almacenar las actividades únicas
			    Set<String> actividadesSet = new HashSet<>();
			    
			    // Llenar el conjunto con actividades únicas
			    for (int i = 0; i < clas.length; i++) {
			        String actividadNombre = icon.obtenerClaseR(clas[i]).getActividad().getNombre();
			        actividadesSet.add(actividadNombre);
			    }
			    
			    // Convertir el conjunto en un arreglo de strings
			    String[] actividades = actividadesSet.toArray(new String[0]);
							
				DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<String>(actividades);
				comboBoxActividad.setModel(modelActividad);
			}else
				comboBoxActividad.removeAllItems();
			actualizarInfoActividad();	
			
	}
	
	private void actualizarInfoActividad() { 
		textPaneActDepor.setText("");
		if(this.comboBoxActividad.getSelectedItem() != null) {
			DtActividadDeportiva act = icon.obtenerActividad(icon.obtenerInstitucionActividad(this.comboBoxActividad.getSelectedItem().toString()) , this.comboBoxActividad.getSelectedItem().toString());
			String datos = "INFORMACION ACT. DEPORTIVA" + "\n\nNombre:  " + act.getNombre() + "\nDescripcion:  " + act.getDescripcion() + "\nDuracion:  " + act.getDuracion() + "\nCosto:  " + act.getCosto();
			textPaneActDepor.setText(datos);
		}
		 
	}
	
	public void inciarComboBoxUsuario() {
		DefaultComboBoxModel<String> modelUsuarios = new DefaultComboBoxModel<String>(icon.listarUsuarios());
		comboBoxUsuario.setModel(modelUsuarios);
		comboBoxNicknameActionPerformed(null);
	}
	
	private String[] obtenerElementosComboBoxClase(JComboBox<String> comboBox) {
	    DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
	    String[] elementos = new String[model.getSize()];

	    for (int i = 0; i < model.getSize(); i++) {
	        elementos[i] = model.getElementAt(i);
	    }

	    return elementos;
	}
}
