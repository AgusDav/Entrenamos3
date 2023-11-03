package presentacion;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import datatypes.DtActividadDeportiva;
import datatypes.DtUsuario;
import excepciones.ActividadDeportivaRepetidaException;
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

public class AgregarActividadDeportiva extends JInternalFrame {
	
	
	private static final long serialVersionUID = 1L;

	private IControlador icon;
	private JDialog dialogoPadre;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JComboBox<String> comboBoxNombreInstitucion;
	private JDateChooser fechaSistemaLabel;
	
	
	public AgregarActividadDeportiva(IControlador icon, JDialog dialogoPadre) {
		
		setTitle("Agregar Actividad Deportiva");
		this.dialogoPadre = dialogoPadre;
		this.icon = icon;
		setBounds(100, 100, 337, 300);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(54, 61, 75));;
		
		// Campo institucion
		JLabel lblInstitucion = new JLabel("Institucion");
		lblInstitucion.setForeground(Color.WHITE);
		lblInstitucion.setBackground(Color.WHITE);
		lblInstitucion.setBounds(12, 30, 95, 15);
		getContentPane().add(lblInstitucion);
		
		comboBoxNombreInstitucion = new JComboBox<String>();
		comboBoxNombreInstitucion.setBounds(188, 28, 114, 19);
		getContentPane().add(comboBoxNombreInstitucion);
		
		// Campo nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(12, 60, 70, 15);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(188, 58, 114, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		// Campo descripcion
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setBackground(Color.WHITE);
		lblDescripcion.setBounds(12, 90, 95, 15);
		getContentPane().add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(188, 89, 114, 19);
		getContentPane().add(textFieldDescripcion);
		
		// Campo duracion
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setForeground(Color.WHITE);
		lblDuracion.setBackground(Color.WHITE);
		lblDuracion.setBounds(12, 120, 82, 15);
		getContentPane().add(lblDuracion);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(188, 120, 114, 19);
		getContentPane().add(textFieldDuracion);
		
		// Campo costo
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setForeground(Color.WHITE);
		lblCosto.setBackground(Color.WHITE);
		lblCosto.setBounds(12, 150, 60, 15);
		getContentPane().add(lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(188, 148, 114, 19);
		getContentPane().add(textFieldCosto);
		
		// Campo fecha de alta
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta");
		lblFechaDeAlta.setForeground(new Color(255, 255, 255));
		lblFechaDeAlta.setBounds(12, 180, 107, 15);
		getContentPane().add(lblFechaDeAlta);
		
		fechaSistemaLabel = new JDateChooser();
		fechaSistemaLabel.setBounds(188, 179, 114, 19);
        getContentPane().add(fechaSistemaLabel);
		
//		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta");
//		lblFechaDeAlta.setForeground(Color.WHITE);
//		lblFechaDeAlta.setBackground(Color.WHITE);
//		lblFechaDeAlta.setBounds(12, 180, 107, 15);
//		getContentPane().add(lblFechaDeAlta);
		
//		JLabel fechaSistemaLabel = new JLabel(LocalDate.now().toString());
//		fechaSistemaLabel.setOpaque(true); // Hacer que el fondo sea opaco
//		fechaSistemaLabel.setBackground(Color.WHITE); // Establecer el fondo en blanco
//
//		fechaSistemaLabel.setForeground(Color.BLACK); // Usar el color de texto negro
//		fechaSistemaLabel.setBounds(123, 180, 114, 19); // Ajustar el alto a 19 para que coincida con la altura de los campos de texto
//		getContentPane().add(fechaSistemaLabel);
		
		// Boton aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarActividadDeportivaAceptarActionPerformed(e);
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
		btnAceptar.setBounds(12, 219, 124, 37);
		getContentPane().add(btnAceptar);
		
		// Botón cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarActividadDeportivaCancelar(e);
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
		btnCancelar.setBounds(185, 219, 117, 37);
		getContentPane().add(btnCancelar);
	
	}
	
	public void iniciarlizarComboBoxes() {
		DefaultComboBoxModel<String> modelinstitutos = new DefaultComboBoxModel<String>(icon.listarInstitutos());
		comboBoxNombreInstitucion.setModel(modelinstitutos);
	}
	
	protected void agregarActividadDeportivaAceptarActionPerformed(ActionEvent arg0) {
		String nombre= this.textFieldNombre.getText();
		String descripcion= this.textFieldDescripcion.getText();
		String strDur= this.textFieldDuracion.getText();
		String strCosto= this.textFieldCosto.getText();
		DtActividadDeportiva dt = null;
		if(checkFormulario()){
            try {
        		String instituto = this.comboBoxNombreInstitucion.getSelectedItem().toString();
            	int duracion = Integer.parseInt(strDur);
            	float costo = Float.parseFloat(strCosto);
            	dt = new DtActividadDeportiva(nombre,descripcion,duracion,costo,this.fechaSistemaLabel.getDate());
                this.icon.altaActividadDeportiva(instituto,dt);
                JOptionPane.showMessageDialog(this, "La Actividad Deportiva se ha creado con éxito", "Agregar Actividad Deportiva ", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                dialogoPadre.dispose();
            } catch (ActividadDeportivaRepetidaException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Actividad Deportiva", JOptionPane.ERROR_MESSAGE);
            }
        }	
	}

	//Cancela el caso de uso
	protected void agregarActividadDeportivaCancelar(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
		
	private void limpiar() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		fechaSistemaLabel.setDate(null);
	}
		
	private boolean checkFormulario() {
		String nombre= this.textFieldNombre.getText();
		String descripcion= this.textFieldDescripcion.getText();
	    String duracion = this.textFieldDuracion.getText();
	    String costo = this.textFieldCosto.getText();
	    if (comboBoxNombreInstitucion.getSelectedItem() == null) {
	    	JOptionPane.showMessageDialog(this, "No hay instituto seleccionado", "Agregar Actividad Deportiva",
	 	    JOptionPane.ERROR_MESSAGE);
	 	    return false;
	    }
	    if (nombre.isEmpty() || descripcion.isEmpty()||duracion.isEmpty() || costo.isEmpty()) {
	    	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Actividad Deportiva",
	        JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    try {
	    	Integer.parseInt(duracion);
	    } catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(this, "La duracion debe ser un numero", "Agregar Actividad Deportiva",
	        JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    try {
	    	Float.parseFloat(costo);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "El costo debe ser un numero", "Agregar Actividad Deportiva",
	        JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}
}
