package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import interfaces.IControlador;
import com.toedter.calendar.JDateChooser;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import excepciones.DictadoRepetidoException;
import excepciones.RegistroAClaseRepetidoException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class RegistroADictadoClase extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	
	private IControlador icon;
	private JDialog dialogoPadre;
	private JComboBox<String> comboBoxNombreInstitucion;
	private JComboBox<String> comboBoxActividadesDeportivas;
	private JComboBox<String> comboBoxClases;
	private JComboBox<String> comboBoxSocio;
	private JDateChooser fecReg;
	private JLabel datoNombreClase;
	private JLabel datoFechaClase;
	private JLabel datoHoraInicioClase;
	
	public RegistroADictadoClase(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
		setTitle("Registro a Dictado Clase");
		this.dialogoPadre = dialogoPadre;
		this.icon = icon;
		setBounds(100, 100, 400, 380);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(54, 61, 75));
		
		// Campo institucion
		JLabel lblInstitucion = new JLabel("Institucion");
		lblInstitucion.setForeground(Color.WHITE);
		lblInstitucion.setBackground(Color.WHITE);
		lblInstitucion.setBounds(12, 30, 95, 15);
		getContentPane().add(lblInstitucion);
		
		comboBoxNombreInstitucion = new JComboBox<String>();
		comboBoxNombreInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBoxActividades();
	        }
	    });
		comboBoxNombreInstitucion.setBounds(210, 28, 150, 19);
		getContentPane().add(comboBoxNombreInstitucion);
		
		// Campo actividades deportivas
		JLabel lblActividades = new JLabel("Actividad Deportiva");
		lblActividades.setForeground(Color.WHITE);
		lblActividades.setBackground(Color.WHITE);
		lblActividades.setBounds(12, 61, 180, 15);
		getContentPane().add(lblActividades);
		
		comboBoxActividadesDeportivas = new JComboBox<String>();
		comboBoxActividadesDeportivas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBoxClase();
			}
		});
		comboBoxActividadesDeportivas.setBounds(210, 59, 150, 19);
		getContentPane().add(comboBoxActividadesDeportivas);
		
		// Campo Clases
		JLabel lblClases = new JLabel("Clase");
		lblClases.setForeground(Color.WHITE);
		lblClases.setBackground(Color.WHITE);
		lblClases.setBounds(12, 90, 180, 15);
		getContentPane().add(lblClases);
		
		comboBoxClases = new JComboBox<String>();
		comboBoxClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarInfoClase();
			}
		});
		comboBoxClases.setBounds(210, 90, 150, 19);
		/*comboBoxClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarComboBoxClase();
			}
		});*/
		getContentPane().add(comboBoxClases);
		
		// Información de la clase
		
		// Nombre de la clase
		JLabel lblNombreClase = new JLabel("Nombre Clase");
		lblNombreClase.setForeground(new Color(255, 255, 255));
		lblNombreClase.setBounds(12, 117, 114, 15);
		getContentPane().add(lblNombreClase);
		
		datoNombreClase = new JLabel("");
		datoNombreClase.setForeground(Color.WHITE);
		datoNombreClase.setBounds(210, 121, 150, 15);
		getContentPane().add(datoNombreClase);
		
		// Fecha de la clase
		JLabel lblFechaClase = new JLabel("Fecha Clase");
		lblFechaClase.setForeground(Color.WHITE);
		lblFechaClase.setBounds(12, 144, 114, 15);
		getContentPane().add(lblFechaClase);
		
		datoFechaClase = new JLabel("");
		datoFechaClase.setForeground(Color.WHITE);
		datoFechaClase.setBounds(210, 144, 150, 15);
		getContentPane().add(datoFechaClase);
		
		// Hora inicio de la clase
		JLabel lblHoraInicioClase = new JLabel("Hora Inicio Clase");
		lblHoraInicioClase.setForeground(Color.WHITE);
		lblHoraInicioClase.setBounds(12, 170, 143, 15);
		getContentPane().add(lblHoraInicioClase);
		
		datoHoraInicioClase = new JLabel("");
		datoHoraInicioClase.setForeground(Color.WHITE);
		datoHoraInicioClase.setBounds(210, 170, 150, 15);
		getContentPane().add(datoHoraInicioClase);
		
		// Campo socio
		JLabel lblSocio = new JLabel("Socio");
		lblSocio.setForeground(Color.WHITE);
		lblSocio.setBackground(Color.WHITE);
		lblSocio.setBounds(12, 197, 95, 15);
		getContentPane().add(lblSocio);
		
		comboBoxSocio = new JComboBox<String>();
		comboBoxSocio.setBounds(210, 197, 150, 19);
		getContentPane().add(comboBoxSocio);
		
		// Campo fecha registro
		JLabel lblFechaRegistro = new JLabel("Fecha registro");
		lblFechaRegistro.setForeground(Color.WHITE);
		lblFechaRegistro.setBackground(Color.WHITE);
		lblFechaRegistro.setBounds(12, 240, 122, 15);
		getContentPane().add(lblFechaRegistro);
		
		fecReg = new JDateChooser();
		fecReg.setBounds(210, 240, 150, 19);
		getContentPane().add(fecReg);
		
		// Botón aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
		btnAceptar.setBackground(new Color(54, 61, 75));
		btnAceptar.setBounds(31, 293, 124, 37);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroADictadoClaseAceptarActionPerformed(e);
			}
		});
		
		// Botón cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroADictadoClaseCancelar(e);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
		btnCancelar.setBackground(new Color(54, 61, 75));
		btnCancelar.setBounds(207, 293, 117, 37);
		getContentPane().add(btnCancelar);
	}
	
	protected void registroADictadoClaseAceptarActionPerformed(ActionEvent arg0) {
		if(checkearComboBoxes()) {
			String nomClase = this.comboBoxClases.getSelectedItem().toString();
			String nickSocio = this.comboBoxSocio.getSelectedItem().toString();
			Date fechaReg = this.fecReg.getDate();
			try {
				this.icon.registroADictadoClase(nickSocio, nomClase, fechaReg);
				JOptionPane.showMessageDialog(this, "Se ha registrado con éxito el socio a la clase", "Registro a dictado de Clase", JOptionPane.INFORMATION_MESSAGE);
				limpiar();
				dialogoPadre.dispose();
			} catch(RegistroAClaseRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registro a dictado de Clase", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Cancela el caso de uso
		protected void registroADictadoClaseCancelar(ActionEvent arg0) {
			limpiar();
			dialogoPadre.dispose();
		}
		
		private void limpiar() {
			comboBoxActividadesDeportivas.removeAllItems();
			comboBoxClases.removeAllItems();
			comboBoxNombreInstitucion.removeAllItems();
			datoNombreClase.setText("");
			datoFechaClase.setText("");
			datoHoraInicioClase.setText("");
			fecReg.setDate(null);
		}
		
		public Boolean checkearComboBoxes() {
			if(comboBoxNombreInstitucion.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar una institución", "Registro a dictado clase", JOptionPane.ERROR_MESSAGE);	
				return false;
			}
			if(comboBoxActividadesDeportivas.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar una actividad deportiva", "Registro a dictado clase", JOptionPane.ERROR_MESSAGE);	
				return false;
			}
			if(comboBoxClases.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar una clase", "Registro a dictado clase", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(comboBoxSocio.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un socio", "Registro a dictado clase", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(fecReg == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de registro", "Registro a dictado clase", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}
		
		public static LocalTime convertToLocalTime(JSpinner jSpinner) {
	        Date dateValue = (Date) jSpinner.getValue();

	        // Convertir Date a Instant
	        Instant instant = dateValue.toInstant();

	        // Obtener la zona horaria del sistema
	        ZoneId zone = ZoneId.systemDefault();

	        // Crear objeto LocalTime a partir del Instant y la zona horaria
	        LocalTime localTime = instant.atZone(zone).toLocalTime();

	        return localTime;
	    }
		
		/*private void actualizarComboBoxes() {
			actualizarComboBoxActividades();
			actualizarComboBoxClase();
		}*/
		
		private void actualizarComboBoxActividades() {
			if(this.comboBoxNombreInstitucion.getSelectedItem() != null) {
			DefaultComboBoxModel<String> modelActDepor = new DefaultComboBoxModel<String>(icon.listarActividadesDeportivas(this.comboBoxNombreInstitucion.getSelectedItem().toString()));
			comboBoxActividadesDeportivas.setModel(modelActDepor);
			actualizarComboBoxClase();}
	    }
		
		private void actualizarComboBoxClase() {
			if(this.comboBoxNombreInstitucion.getSelectedItem() != null && this.comboBoxActividadesDeportivas.getSelectedItem() != null) {
				DefaultComboBoxModel<String> modelClase = new DefaultComboBoxModel<String>(icon.listarClases(this.comboBoxNombreInstitucion.getSelectedItem().toString(), this.comboBoxActividadesDeportivas.getSelectedItem().toString()));
				comboBoxClases.setModel(modelClase);
				}else
					comboBoxClases.removeAllItems();
					
			
				actualizarInfoClase();
			
				
		}
		
		private void actualizarInfoClase() {
			if(comboBoxClases.getSelectedItem() != null) {
			datoNombreClase.setText(comboBoxClases.getSelectedItem().toString());
			Date fechaClase = icon.obtenerClase(comboBoxClases.getSelectedItem().toString()).getFecha();

			// Crea un objeto SimpleDateFormat para formatear la fecha en el formato deseado
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Cambia el formato según tus preferencias

			// Formatea la fecha como una cadena
			String fecha = sdf.format(fechaClase);
			datoFechaClase.setText(fecha);
			datoHoraInicioClase.setText(icon.obtenerClase(comboBoxClases.getSelectedItem().toString()).getHoraInicio());
			}else {
				datoNombreClase.setText("");
				datoFechaClase.setText("");
				datoHoraInicioClase.setText("");
			};
			 
		};
		
		
		public void inicializarComboBoxes() {
			DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(icon.listarInstitutos());
			comboBoxNombreInstitucion.setModel(modelInstitutos);
			if(comboBoxActividadesDeportivas.getSelectedItem() == null) {
				if(comboBoxNombreInstitucion.getSelectedItem() != null){
					actualizarComboBoxActividades();
					comboBoxActividadesDeportivas.setVisible(true);
				}
				else
					comboBoxActividadesDeportivas.setVisible(false);
			}
			if(comboBoxClases.getSelectedItem() == null) {
				if(comboBoxNombreInstitucion.getSelectedItem() != null){
					actualizarComboBoxClase();
					comboBoxClases.setVisible(true);
				}
				else
					comboBoxClases.setVisible(false);
			}
			if(this.comboBoxClases.getSelectedItem() != null) {
				DefaultComboBoxModel<String> modelSocio = new DefaultComboBoxModel<String>(icon.listarSocios(this.comboBoxClases.getSelectedItem().toString()));
				comboBoxSocio.setModel(modelSocio);
			}
		}
}
