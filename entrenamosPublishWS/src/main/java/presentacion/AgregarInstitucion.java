package presentacion;

import interfaces.IControlador;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import excepciones.InstitucionDeportivaRepetidaException;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AgregarInstitucion extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;

	private IControlador icon;
	private JDialog dialogoPadre;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldUrl;

	public AgregarInstitucion(IControlador icon, JDialog dialogoPadre) {
		// Título de la ventata
		setTitle("Agregar Institucion");
		this.dialogoPadre = dialogoPadre;
		this.icon = icon;
		setBounds(100, 100, 350, 280);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(54, 61, 75));
		
		// Campo nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(34, 45, 70, 15);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(189, 43, 114, 19);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		// Campo descripción
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBounds(34, 85, 94, 15);
		getContentPane().add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setText("");
		textFieldDescripcion.setBounds(189, 83, 114, 19);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		// Campo url
		JLabel lblUrl = new JLabel("Url:");
		lblUrl.setForeground(new Color(255, 255, 255));
		lblUrl.setBounds(34, 129, 70, 15);
		getContentPane().add(lblUrl);
				
		textFieldUrl = new JTextField();
		textFieldUrl.setText("");
		textFieldUrl.setBounds(189, 127, 114, 19);
		getContentPane().add(textFieldUrl);
		textFieldUrl.setColumns(10);
		
		// Botón aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarInstitucionAceptarActionPerformed(e);
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
		btnAceptar.setBounds(33, 189, 123, 32);
		getContentPane().add(btnAceptar);
		
		// Botón cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarInstitutoCancelar(e);
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
		btnCancelar.setBounds(180, 189, 123, 32);
		getContentPane().add(btnCancelar);
	}
	
	protected void agregarInstitucionAceptarActionPerformed(ActionEvent arg0) {
		String nombre = this.textFieldNombre.getText();
        String desc = this.textFieldDescripcion.getText();
        String url = this.textFieldUrl.getText();
        
        if (nombre.isEmpty() || desc.isEmpty() || url.isEmpty()){
        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Institución", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                this.icon.altaInstitucion(nombre, desc, url);
                JOptionPane.showMessageDialog(this, "La Institucion se ha creado con éxito", "Agregar Institucioón ", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                dialogoPadre.dispose();
            } catch (InstitucionDeportivaRepetidaException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Institución", JOptionPane.ERROR_MESSAGE);
            }
        } 
	}
	
	// Cancela el caso de uso
	protected void agregarInstitutoCancelar(ActionEvent arg0) {
		limpiar();
		dialogoPadre.dispose();
	}
	
	// Limpia los campos donde se insertan los datos
	private void limpiar() {
		textFieldNombre.setText("");
        textFieldDescripcion.setText("");
        textFieldUrl.setText("");
	}
}

