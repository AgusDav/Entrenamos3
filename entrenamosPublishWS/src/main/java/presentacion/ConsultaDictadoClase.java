package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import datatypes.DtClase;
import excepciones.RegistroAClaseRepetidoException;
import interfaces.IControlador;
import javax.swing.JTextPane;

public class ConsultaDictadoClase extends JInternalFrame {

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
	private JTextPane textPaneInfoClase;
	
	public ConsultaDictadoClase(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
		setTitle("Consulta Dictado Clase");
		this.dialogoPadre = dialogoPadre;
		this.icon = icon;
		setBounds(100, 100, 350, 400);
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
		
		
		
		// Botón cancelar
		JButton btnCancelar = new JButton("Salir");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaDictadoClaseCancelar(e);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
		btnCancelar.setBackground(new Color(54, 61, 75));
		btnCancelar.setBounds(207, 293, 117, 37);
		getContentPane().add(btnCancelar);
		
		textPaneInfoClase = new JTextPane();
		textPaneInfoClase.setBounds(49, 133, 277, 130);
		getContentPane().add(textPaneInfoClase);
	}
	

	
	// Cancela el caso de uso
		protected void consultaDictadoClaseCancelar(ActionEvent arg0) {
			limpiar();
			dialogoPadre.dispose();
		}
		
		private void limpiar() {
			comboBoxActividadesDeportivas.removeAllItems();
			comboBoxClases.removeAllItems();
			comboBoxNombreInstitucion.removeAllItems();
			
		}
		
		
		
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
			textPaneInfoClase.setText("");
			if(this.comboBoxClases.getSelectedItem() != null) {
				DtClase cls = icon.obtenerClase(this.comboBoxClases.getSelectedItem().toString());
				String datos = "INFORMACION CLASE" + "\n\nNombre:  " + cls.getNombre() + "\nHora de Inicio:  " + cls.getHoraInicio() + "\nUrl:  " + cls.getUrl() + "\nFecha Registro:  " + cls.getFechaReg();
				textPaneInfoClase.setText(datos);
			}
			 
		};
		
		
		public void inicializarComboBoxes() {
			DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(icon.listarInstitutos());
			comboBoxNombreInstitucion.setModel(modelInstitutos);
			actualizarComboBoxActividades();
			/*if(comboBoxActividadesDeportivas.getSelectedItem() == null) {
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
			}*/
		}
}
	
	


