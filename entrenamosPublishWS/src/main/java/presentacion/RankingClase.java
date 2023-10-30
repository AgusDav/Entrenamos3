package presentacion;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import datatypes.DtClase;
import interfaces.IControlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

public class RankingClase extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private IControlador icon;
	private JComboBox<String> comboBoxClase;
	private JLabel datoFecha;
	private JLabel datoURL;
	
	public RankingClase(IControlador icon, JDialog rankingClaseDialog) {
		
		setTitle("Ranking de Clases");
		this.icon=icon;
		getContentPane().setBackground(new Color(54, 61, 75));
		getContentPane().setLayout(null);
		
		
		JLabel lblClase = new JLabel("Clase");
		lblClase.setForeground(new Color(255, 255, 255));
		lblClase.setBackground(new Color(255, 255, 255));
		lblClase.setBounds(12, 35, 61, 15);
		getContentPane().add(lblClase);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setBackground(new Color(255, 255, 255));
		lblFecha.setBounds(12, 70, 61, 15);
		getContentPane().add(lblFecha);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setForeground(new Color(255, 255, 255));
		lblUrl.setBackground(new Color(255, 255, 255));
		lblUrl.setBounds(12, 103, 61, 15);
		getContentPane().add(lblUrl);
		
		comboBoxClase = new JComboBox<String>();
		comboBoxClase.setBounds(94, 30, 167, 24);
		comboBoxClase.setForeground(Color.WHITE);
		comboBoxClase.setBackground(new Color(54, 61, 75));
		comboBoxClase.addActionListener(this::comboBoxClaseActionPerformed);
		getContentPane().add(comboBoxClase);
		
		datoFecha = new JLabel("");
		datoFecha.setForeground(Color.WHITE);
		datoFecha.setBounds(104, 70, 157, 15);
		getContentPane().add(datoFecha);
		
		datoURL = new JLabel("");
		datoURL.setForeground(Color.WHITE);
		datoURL.setBounds(94, 103, 167, 15);
		getContentPane().add(datoURL);
	}
	private void comboBoxClaseActionPerformed(ActionEvent evt){
		String clase = (String) comboBoxClase.getSelectedItem();
		if (clase!=null) {
			DtClase dtc = icon.obtenerClase(clase);
			datoFecha.setText(dtc.getFecha().toString());
			datoURL.setText(dtc.getUrl());
			
		}
	}
	public void iniciarComboBoxClase() {
		DefaultComboBoxModel<String> modelClases = new DefaultComboBoxModel<String>(icon.listarClases2());
		comboBoxClase.setModel(modelClases);
		comboBoxClaseActionPerformed(null);
	}
} 