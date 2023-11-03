package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import interfaces.IControlador;

public class RankingActividadDeportiva extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JComboBox<String> comboBoxActividad;
	private JLabel datoDescripcion;
	private JLabel datoCosto;
	private JLabel datoCantidadClases;

	public RankingActividadDeportiva(IControlador icon,JDialog dialogoPadre) {
		
		setTitle("Ranking de Actividad Deportiva");
		this.icon=icon;
		getContentPane().setBackground(new Color(54, 61, 75));
		getContentPane().setLayout(null);
		
		
		JLabel lblActividad = new JLabel("Actividad");
		lblActividad.setForeground(new Color(255, 255, 255));
		lblActividad.setBackground(new Color(255, 255, 255));
		lblActividad.setBounds(12, 35, 76, 15);
		getContentPane().add(lblActividad);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBackground(new Color(255, 255, 255));
		lblDescripcion.setBounds(12, 103, 100, 15);
		getContentPane().add(lblDescripcion);
		
		comboBoxActividad = new JComboBox<String>();
		comboBoxActividad.setBounds(94, 30, 167, 24);
		comboBoxActividad.setForeground(Color.WHITE);
		comboBoxActividad.setBackground(new Color(54, 61, 75));
		comboBoxActividad.addActionListener(this::comboBoxActividadActionPerformed);
		getContentPane().add(comboBoxActividad);
		
		datoDescripcion = new JLabel("");
		datoDescripcion.setForeground(Color.WHITE);
		datoDescripcion.setBounds(119, 103, 167, 15);
		getContentPane().add(datoDescripcion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setForeground(Color.WHITE);
		lblCosto.setBackground(Color.WHITE);
		lblCosto.setBounds(12, 143, 100, 15);
		getContentPane().add(lblCosto);
		
		datoCosto = new JLabel("");
		datoCosto.setForeground(Color.WHITE);
		datoCosto.setBounds(119, 143, 167, 15);
		getContentPane().add(datoCosto);
		
		JLabel lblCantidadClases = new JLabel("Cant. de Clases Asociadas");
		lblCantidadClases.setForeground(Color.WHITE);
		lblCantidadClases.setBackground(Color.WHITE);
		lblCantidadClases.setBounds(12, 181, 192, 15);
		getContentPane().add(lblCantidadClases);
		
		datoCantidadClases = new JLabel("");
		datoCantidadClases.setForeground(Color.WHITE);
		datoCantidadClases.setBounds(216, 181, 91, 15);
		getContentPane().add(datoCantidadClases);
	}
	private void comboBoxActividadActionPerformed(ActionEvent evt){
		if(comboBoxActividad.getSelectedItem()!=null) {
			String actividad = (String) comboBoxActividad.getSelectedItem();
			DtActividadDeportiva dtc = icon.obtenerActividad(icon.obtenerInstitucionActividad(actividad),actividad);
			datoCosto.setText(Float.toString(dtc.getCosto()));
			datoDescripcion.setText(dtc.getDescripcion());
			datoCantidadClases.setText(Integer.toString(icon.obtenerActividadR(icon.obtenerInstitucionActividad(actividad), actividad).getClases().size()));
		}
	}
	public void iniciarComboBoxRankingActividad() {
		DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<String>(icon.obtenerRankingActividadesDeportivas());
		comboBoxActividad.setModel(modelActividad);
		comboBoxActividadActionPerformed(null);
	}
}


