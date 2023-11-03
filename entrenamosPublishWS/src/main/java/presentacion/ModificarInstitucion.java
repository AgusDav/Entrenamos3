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
import javax.swing.border.LineBorder;

import datatypes.DtInstitucionDeportiva;
import interfaces.IControlador;
import javax.swing.JTextField;

public class ModificarInstitucion extends JInternalFrame{
	
	private IControlador icon;
	private JDialog dialogoPadre;
	private JComboBox<String> comboBoxInstituicion;
	private JLabel datoURL;
	private JLabel datoDescripcion;

	private static final long serialVersionUID = 1L;
	private JTextField NuevaDesc;
	private JTextField nuevaUrl;
	
	
	public ModificarInstitucion(IControlador icon, JDialog dialogoPadre){
		setTitle("Modificar Usuario");
		this.icon = icon;
		this.dialogoPadre = dialogoPadre;
		setBounds(100, 100, 504, 533);
		getContentPane().setBackground(new Color(54, 61, 75));
		getContentPane().setLayout(null);
		
		JLabel lblInstitucion = new JLabel("Institucion: ");
		lblInstitucion.setForeground(new Color(255, 255, 255));
		lblInstitucion.setBounds(23, 18, 106, 20);
		getContentPane().add(lblInstitucion);
		
		JLabel lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBounds(23, 72, 106, 20);
		getContentPane().add(lblDescripcion);
		
		datoDescripcion = new JLabel("");
		datoDescripcion.setForeground(new Color(255, 255, 255));
		datoDescripcion.setBounds(185, 72, 181, 20);
		getContentPane().add(datoDescripcion);
		
		JLabel lblURL = new JLabel("URL: ");
		lblURL.setForeground(new Color(255, 255, 255));
		lblURL.setBounds(23, 130, 106, 20);
		getContentPane().add(lblURL);
		
		datoURL = new JLabel("");
		datoURL.setForeground(new Color(255, 255, 255));
		datoURL.setBounds(185, 130, 181, 20);
		getContentPane().add(datoURL);
		
		comboBoxInstituicion = new JComboBox<String>();
		comboBoxInstituicion.setBackground(new Color(54, 61, 75));
		comboBoxInstituicion.setForeground(new Color(255, 255, 255));
		comboBoxInstituicion.setBounds(174, 16, 135, 24);
		comboBoxInstituicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarInfoInstitucion();
			}
		});
		getContentPane().add(comboBoxInstituicion);
		
		// Botón aceptar
        JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarInstitucionActionPerformed(e);
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
		btnAceptar.setBounds(23, 348, 124, 37);
		getContentPane().add(btnAceptar);
		
		// Botón cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarInstitucionCancelar(e);
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
		btnCancelar.setBounds(174, 348, 117, 37);
		getContentPane().add(btnCancelar);
		
		JLabel lblNuevaDesc = new JLabel("Nueva Descripcion:");
		lblNuevaDesc.setForeground(Color.WHITE);
		lblNuevaDesc.setBounds(23, 187, 142, 20);
		getContentPane().add(lblNuevaDesc);
		
		JLabel lblnuevaUrl = new JLabel("Nueva URL: ");
		lblnuevaUrl.setForeground(Color.WHITE);
		lblnuevaUrl.setBounds(23, 240, 106, 20);
		getContentPane().add(lblnuevaUrl);
		
		NuevaDesc = new JTextField();
		NuevaDesc.setBounds(185, 188, 124, 19);
		getContentPane().add(NuevaDesc);
		NuevaDesc.setColumns(10);
		
		nuevaUrl = new JTextField();
		nuevaUrl.setColumns(10);
		nuevaUrl.setBounds(185, 241, 124, 19);
		getContentPane().add(nuevaUrl);
	}
	
	public void inciarComboBoxInstitucion() {
		DefaultComboBoxModel<String> modelInstitucion = new DefaultComboBoxModel<String>(icon.listarInstitutos());
		comboBoxInstituicion.setModel(modelInstitucion);
		if(this.comboBoxInstituicion.getSelectedItem()!=null)
			actualizarInfoInstitucion();
	}
	
	public void actualizarInfoInstitucion() {
		DtInstitucionDeportiva institucion;
		String nombre = this.comboBoxInstituicion.getSelectedItem().toString();
		institucion = icon.obtenerInstitucion(nombre);
		datoURL.setText(institucion.getUrl());
		datoDescripcion.setText(institucion.getDescripcion());
	}
	
	public void modificarInstitucionActionPerformed(ActionEvent arg0) {
		if (this.comboBoxInstituicion.getSelectedItem() != null) {
			String nombre = this.comboBoxInstituicion.getSelectedItem().toString();
			String url = this.nuevaUrl.getText();
			String desc = this.NuevaDesc.getText();
			if(!desc.isEmpty() && !url.isEmpty()) {
				icon.ModificarInstitucion(nombre,desc,url);
				JOptionPane.showMessageDialog(this, "La Institucion se ha modificado con éxito", "Modificar Institucion ", JOptionPane.INFORMATION_MESSAGE);
	    		limpiar();
	    		dialogoPadre.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar Institucion", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this, "No hay Instituciones", "Modificar Institucion", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void modificarInstitucionCancelar(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
	
	public void limpiar() {
		this.datoDescripcion.setText("");
		this.datoURL.setText("");
		this.NuevaDesc.setText("");
		this.nuevaUrl.setText("");
	}
}

	
