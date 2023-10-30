package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

import interfaces.IControlador;
import logica.Clase;

import javax.swing.JLabel;

import datatypes.DtActividadDeportiva;
import java.awt.Font;
import javax.swing.JList;

public class ConsultaActividadDeportiva extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JComboBox<String> comboBoxInstituto;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxClases;
	private JLabel datoDescripcion;
	private JLabel datoDuracion;
	private JLabel datoCosto;
	private JLabel datoFecha;
	private JLabel lblClases;

	public ConsultaActividadDeportiva(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
		setTitle("Consulta Actividad Deportiva");
		this.icon = icon;
		setBounds(120, 120, 368, 424);
		getContentPane().setBackground(new Color(54, 61, 75));
		getContentPane().setLayout(null);
		
		// Campo institucion
		JLabel lblInstitucion = new JLabel("Institucion: ");
		lblInstitucion.setBounds(12, 25, 96, 20);
		lblInstitucion.setForeground(new Color(255, 255, 255));
		getContentPane().add(lblInstitucion);
		
		comboBoxInstituto = new JComboBox<String>();
		comboBoxInstituto.setBounds(163, 25, 156, 24);
		comboBoxInstituto.setBackground(new Color(54, 61, 75));
		comboBoxInstituto.setForeground(new Color(255, 255, 255));
		comboBoxInstituto.addActionListener(this::comboBoxInstitutoActionPerformed);
		getContentPane().add(comboBoxInstituto);
		
		// Campo actividad deportiva
		JLabel lblActividadDeportiva = new JLabel("Actividad Deportiva:");
		lblActividadDeportiva.setBounds(12, 83, 156, 20);
		lblActividadDeportiva.setFont(new Font("Dialog", Font.BOLD, 11));
		lblActividadDeportiva.setForeground(Color.WHITE);
		getContentPane().add(lblActividadDeportiva);
		
		comboBoxActividad = new JComboBox<String>();
		comboBoxActividad.setBounds(163, 80, 156, 24);
		comboBoxActividad.setBackground(new Color(54, 61, 75));
		comboBoxActividad.setForeground(new Color(255, 255, 255));
		comboBoxActividad.addActionListener(this::comboBoxActividadActionPerformed);
		getContentPane().add(comboBoxActividad);
		
		// Campo descripción
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 144, 96, 20);
		lblDescripcion.setForeground(new Color(255, 255, 255));
		getContentPane().add(lblDescripcion);
		
		datoDescripcion = new JLabel("");
		datoDescripcion.setBounds(163, 147, 156, 15);
		datoDescripcion.setForeground(new Color(255, 255, 255));
		getContentPane().add(datoDescripcion);
		
		// Campo duracion
		JLabel lblDuracion = new JLabel("Duracion:");
		lblDuracion.setBounds(12, 194, 96, 20);
		lblDuracion.setForeground(Color.WHITE);
		getContentPane().add(lblDuracion);
		
		datoDuracion = new JLabel("");
		datoDuracion.setBounds(163, 197, 156, 15);
		datoDuracion.setForeground(Color.WHITE);
		getContentPane().add(datoDuracion);
		
		// Campo costo
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(12, 244, 96, 20);
		lblCosto.setForeground(Color.WHITE);
		getContentPane().add(lblCosto);
		
		datoCosto = new JLabel("");
		datoCosto.setBounds(163, 247, 156, 15);
		datoCosto.setForeground(Color.WHITE);
		getContentPane().add(datoCosto);
		
		// Campo fecha
		JLabel lblFecha = new JLabel("Fecha Registro:");
		lblFecha.setBounds(12, 294, 111, 20);
		lblFecha.setForeground(Color.WHITE);
		getContentPane().add(lblFecha);
		
		datoFecha = new JLabel("");
		datoFecha.setBounds(163, 297, 156, 15);
		datoFecha.setForeground(Color.WHITE);
		getContentPane().add(datoFecha);
		
		// Campo clases
		lblClases = new JLabel("Clases:");
		lblClases.setForeground(Color.WHITE);
		lblClases.setBounds(12, 346, 111, 20);
		getContentPane().add(lblClases);
		
		comboBoxClases = new JComboBox<String>();
		comboBoxClases.setForeground(Color.WHITE);
		comboBoxClases.setBackground(new Color(54, 61, 75));
		comboBoxClases.setBounds(163, 344, 156, 24);
		getContentPane().add(comboBoxClases);
	}
	
	
	private void comboBoxInstitutoActionPerformed(ActionEvent evt) {
	    String nombre = (String) comboBoxInstituto.getSelectedItem();
	    if (nombre != null) {
	    	DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<String>(icon.listarActividadesDeportivas(nombre));
	    	comboBoxActividad.setModel(modelActividad);
	    	comboBoxActividadActionPerformed(null);
	    }
	}
	
	private void comboBoxActividadActionPerformed(ActionEvent evt) {
		String actividad = (String) comboBoxActividad.getSelectedItem();
		String instituto = (String) comboBoxInstituto.getSelectedItem();
	    if (actividad != null) {
	        DtActividadDeportiva dtA = icon.obtenerActividad(instituto, actividad);
	        datoDescripcion.setText(dtA.getDescripcion());
	        datoDuracion.setText(String.valueOf(dtA.getDuracion()));
	        datoCosto.setText(String.valueOf(dtA.getCosto()));
	        datoFecha.setText(dtA.getFecReg().toString());
	       
	        DefaultComboBoxModel<String> modelClases = new DefaultComboBoxModel<String>(icon.listarClases(instituto, actividad));
	        comboBoxClases.setModel(modelClases);
	    }
	}
	
	public void inciarComboBoxActividadDeportiva() {
		DefaultComboBoxModel<String> modelInstitutos = new DefaultComboBoxModel<String>(icon.listarInstitutos());
		comboBoxInstituto.setModel(modelInstitutos);
		comboBoxInstitutoActionPerformed(null);
	}
}
