package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;


import datatypes.DtActividadDeportiva;
import interfaces.IControlador;

public class ModificarActividadDeportiva extends JInternalFrame {
	private IControlador icon;
	private JDialog dialogoPadre;
	private JComboBox<String> comboBoxActividadDeportiva;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextField textFieldDescripcion;
	private JTextPane textPaneActividadInfo;
	private static final long serialVersionUID = 1L;
	private JLabel lblDescripcion;
	
	
	public ModificarActividadDeportiva(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
				setTitle("Modificar Actividad Deportiva");
				this.icon = icon;
				this.dialogoPadre = dialogoPadre;
				setBounds(100, 100, 390, 426);
				getContentPane().setBackground(new Color(54, 61, 75));
				getContentPane().setLayout(null);
				
				JLabel lblActividadDeportiva = new JLabel("Actividades Deportivas: ");
				lblActividadDeportiva.setForeground(new Color(255, 255, 255));
				lblActividadDeportiva.setBounds(23, 18, 179, 20);
				getContentPane().add(lblActividadDeportiva);
				
				comboBoxActividadDeportiva = new JComboBox<String>();
				comboBoxActividadDeportiva.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actualizarInfoActividad();
					}
				});
				comboBoxActividadDeportiva.setBackground(new Color(54, 61, 75));
				comboBoxActividadDeportiva.setForeground(new Color(255, 255, 255));
				comboBoxActividadDeportiva.setBounds(203, 16, 135, 24);
				getContentPane().add(comboBoxActividadDeportiva);
				
				// Campo nombre
				JLabel lblDuracion = new JLabel("Duracion:");
				lblDuracion.setForeground(new Color(255, 255, 255));
				lblDuracion.setBounds(22, 264, 70, 15);
				getContentPane().add(lblDuracion);
				
				textFieldDuracion = new JTextField();
		        textFieldDuracion.setBounds(177, 262, 150, 19);
		        getContentPane().add(textFieldDuracion);
		        textFieldDuracion.setColumns(10);
				
		        // Campo apellido
				JLabel lblCosto = new JLabel("Costo:");
				lblCosto.setForeground(new Color(255, 255, 255));
				lblCosto.setBounds(22, 237, 70, 15);
				getContentPane().add(lblCosto);
				
				textFieldCosto = new JTextField();
		        textFieldCosto.setBounds(177, 235, 150, 19);
		        getContentPane().add(textFieldCosto);
		        textFieldCosto.setColumns(10);
				
				// Botón cancelar
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarActividadSalir(e);
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
				btnCancelar.setBounds(184, 310, 117, 37);
				getContentPane().add(btnCancelar);
				
				// Botón aceptar
		        JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarActividadDeportivaActionPerformed(e);
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
				btnAceptar.setBounds(35, 310, 124, 37);
				getContentPane().add(btnAceptar);
				
				textPaneActividadInfo = new JTextPane();
				textPaneActividadInfo.setForeground(new Color(255, 255, 255));
				textPaneActividadInfo.setBackground(new Color(54, 61, 75));
				textPaneActividadInfo.setBounds(43, 50, 258, 138);
				getContentPane().add(textPaneActividadInfo);
				
				lblDescripcion = new JLabel("Descripcion:");
				lblDescripcion.setForeground(Color.WHITE);
				lblDescripcion.setBounds(20, 210, 139, 15);
				getContentPane().add(lblDescripcion);
				
				textFieldDescripcion = new JTextField();
				textFieldDescripcion.setColumns(10);
				textFieldDescripcion.setBounds(177, 208, 150, 19);
				getContentPane().add(textFieldDescripcion);
	}
	
	public void inciarComboBoxUsuario() {
		DefaultComboBoxModel<String> modelActividades = new DefaultComboBoxModel<String>(icon.obtenerTodasActividadesDeportivas());
		comboBoxActividadDeportiva.setModel(modelActividades);
		actualizarInfoActividad();
	}
    
	private void actualizarInfoActividad() { 
		textPaneActividadInfo.setText("");
		if(this.comboBoxActividadDeportiva.getSelectedItem() != null) {
			DtActividadDeportiva act = icon.obtenerActividad(icon.obtenerInstitucionActividad(this.comboBoxActividadDeportiva.getSelectedItem().toString()) , this.comboBoxActividadDeportiva.getSelectedItem().toString());
			String datos = "INFORMACION ACT. DEPORTIVA" + "\n\nNombre:  " + act.getNombre() + "\nDescripcion:  " + act.getDescripcion() + "\nDuracion:  " + act.getDuracion() + "\nCosto:  " + act.getCosto();
			textPaneActividadInfo.setText(datos);
		}
		 
	}
	
	protected void modificarActividadDeportivaActionPerformed(ActionEvent arg0) {
		if(this.comboBoxActividadDeportiva.getSelectedItem() != null) {
			String actividad = this.comboBoxActividadDeportiva.getSelectedItem().toString();
			String descripcion = this.textFieldDescripcion.getText();
			String strDur = this.textFieldDuracion.getText();
			String strCosto = this.textFieldCosto.getText();
			if(descripcion.isEmpty() || strDur.isEmpty() || strCosto.isEmpty()){
	        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar Actividad Deportiva", JOptionPane.ERROR_MESSAGE);
			}else {
				int duracion = Integer.parseInt(strDur);
            	float costo = Float.parseFloat(strCosto);
				icon.ModificarActividadDeportiva(actividad, descripcion, duracion, costo);
	    		JOptionPane.showMessageDialog(this, "La actividad Deportiva se ha modificado con éxito", "Modificar Actividad Deportiva", JOptionPane.INFORMATION_MESSAGE);
	    		limpiar();
	    		dialogoPadre.dispose();
	            
	    	}
		}else
			JOptionPane.showMessageDialog(this, "No hay Actividad", "Modificar Actividad Deportiva", JOptionPane.ERROR_MESSAGE);
	}
    
	
	private void limpiar() {
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		textFieldDescripcion.setText("");
		textPaneActividadInfo.setText("");
	}
	
	// Cancela el caso de uso
	protected void modificarActividadSalir(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
}
