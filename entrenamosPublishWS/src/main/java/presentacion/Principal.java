package presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import interfaces.Fabrica;
import interfaces.IControlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Insets;
import persistencia.Conexion;
import publicadores.ControladorPublish;

public class Principal {
    private JFrame frame;
    private JPanel panelDeTarjetas;
    private CardLayout cardLayout;
    private JDialog agregarInstitutoDialog;
    private AgregarInstitucion agregarInstitutoInternalFrame;
    private AgregarUsuario agregarUsuarioInternalFrame;
    private JDialog agregarUsuarioDialog;
    private AgregarActividadDeportiva agregarActividadDeportivaInternalFrame;
    private JDialog agregarActividadDeportivaDialog;
    private AltaDictadoClase altaDictadoClaseInternalFrame;
    private JDialog altaDictadoClaseDialog;
    private ConsultaUsuario consultaUsuarioInternalFrame;
    private JDialog consultaUsuarioDialog;
    private ConsultaActividadDeportiva consultaActividadInternalFrame;
    private JDialog consultaActividadDialog;
    private RegistroADictadoClase registroADictadoClaseInternalFrame;
    private JDialog registroADictadoClaseDialog;
    private RankingClase rankingClaseInternalFrame;
    private JDialog rankingClaseDialog;
    private ModificarUsuario modificarUsuarioInternalFrame;
    private JDialog modificarUsuarioDialog;
    private JDialog consultaDictadoClaseDialog;
    private ConsultaDictadoClase consultaDictadoClaseInternalFrame;
    private ModificarActividadDeportiva modificarActividadDeportivaInternalFrame;
    private JDialog modificarActividadDeportivaDialog;
    private ModificarInstitucion modificarInstitucionInternalFrame;
    private JDialog modificarInstitucionDialog;
    private RankingActividadDeportiva rankingActividadDeportivaInternalFrame;
    private JDialog rankingActividadDeportivaDialog;
    private LogIn LogInInternalFrame;
    private JDialog LogInDialog;
    


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal ventana = new Principal();
                    ventana.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        ControladorPublish cp = new ControladorPublish();
        cp.publicar();

        iniciar();
        Fabrica fabrica = Fabrica.getInstancia();
        IControlador icon = fabrica.getIControlador();

        modificarInstitucionDialog = new JDialog(frame, "Modificar Institución", true);
        modificarInstitucionDialog.setSize(350, 450);
        modificarInstitucionDialog.setLocationRelativeTo(frame);
        modificarInstitucionDialog.getContentPane().setLayout(new BorderLayout());

        modificarInstitucionInternalFrame = new ModificarInstitucion(icon, modificarInstitucionDialog);
        modificarInstitucionDialog.getContentPane().add(modificarInstitucionInternalFrame);
        modificarInstitucionDialog.setLocationRelativeTo(null);
        modificarInstitucionDialog.setVisible(false);
        modificarInstitucionInternalFrame.setVisible(true);
        
        LogInDialog = new JDialog(frame, "Log In", true);
        LogInDialog.setSize(350, 450);
        LogInDialog.setLocationRelativeTo(frame);
        LogInDialog.getContentPane().setLayout(new BorderLayout());

        LogInInternalFrame = new LogIn(icon, LogInDialog);
        LogInDialog.getContentPane().add(LogInInternalFrame);
        LogInDialog.setLocationRelativeTo(null);
        LogInDialog.setVisible(false);
        LogInInternalFrame.setVisible(true);
        
        agregarInstitutoDialog = new JDialog(frame, "Agregar Institución", true);
        agregarInstitutoDialog.setSize(340, 310);
        agregarInstitutoDialog.setLocationRelativeTo(frame);
        agregarInstitutoDialog.getContentPane().setLayout(new BorderLayout());

        agregarInstitutoInternalFrame = new AgregarInstitucion(icon, agregarInstitutoDialog);
        agregarInstitutoDialog.getContentPane().add(agregarInstitutoInternalFrame);
        agregarInstitutoDialog.setLocationRelativeTo(null);
        agregarInstitutoDialog.setVisible(false);
        agregarInstitutoInternalFrame.setVisible(true);

        agregarUsuarioDialog = new JDialog(frame, "Agregar Usuario", true);
        agregarUsuarioDialog.setSize(350, 450);
        agregarUsuarioDialog.setLocationRelativeTo(frame);
        agregarUsuarioDialog.getContentPane().setLayout(new BorderLayout());

        agregarUsuarioInternalFrame = new AgregarUsuario(icon, agregarUsuarioDialog);
        agregarUsuarioDialog.getContentPane().add(agregarUsuarioInternalFrame);
        agregarUsuarioDialog.setLocationRelativeTo(null);
        agregarUsuarioDialog.setVisible(false);
        agregarUsuarioInternalFrame.setVisible(true);
        
        agregarActividadDeportivaDialog = new JDialog(frame, "Agregar Actividad Deportiva", true);
        agregarActividadDeportivaDialog.setSize(320, 340);
        agregarActividadDeportivaDialog.setLocationRelativeTo(frame);
        agregarActividadDeportivaDialog.getContentPane().setLayout(new BorderLayout());

        agregarActividadDeportivaInternalFrame = new AgregarActividadDeportiva(icon, agregarActividadDeportivaDialog);
        agregarActividadDeportivaDialog.getContentPane().add(agregarActividadDeportivaInternalFrame);
        agregarActividadDeportivaDialog.setLocationRelativeTo(null);
        agregarActividadDeportivaDialog.setVisible(false);
        agregarActividadDeportivaInternalFrame.setVisible(true);
        
        altaDictadoClaseDialog = new JDialog(frame, "Agregar Clase", true);
        altaDictadoClaseDialog.setSize(345, 420);
        altaDictadoClaseDialog.setLocationRelativeTo(frame);
        altaDictadoClaseDialog.getContentPane().setLayout(new BorderLayout());

        altaDictadoClaseInternalFrame = new AltaDictadoClase(icon, altaDictadoClaseDialog);
        altaDictadoClaseDialog.getContentPane().add(altaDictadoClaseInternalFrame);
        altaDictadoClaseDialog.setLocationRelativeTo(null);
        altaDictadoClaseDialog.setVisible(false);
        altaDictadoClaseInternalFrame.setVisible(true);
        
        consultaUsuarioDialog = new JDialog(frame, "Consulta Usuario", true);
        consultaUsuarioDialog.setSize(700, 400);
        consultaUsuarioDialog.setLocationRelativeTo(frame);
        consultaUsuarioDialog.getContentPane().setLayout(new BorderLayout());

        consultaUsuarioInternalFrame = new ConsultaUsuario(icon, consultaUsuarioDialog);
        consultaUsuarioDialog.getContentPane().add(consultaUsuarioInternalFrame);
        consultaUsuarioDialog.setLocationRelativeTo(null);
        consultaUsuarioDialog.setVisible(false);
        consultaUsuarioInternalFrame.setVisible(true);
        
        consultaActividadDialog = new JDialog(frame, "Consulta Actividad Deportiva", true);
        consultaActividadDialog.setSize(400, 480);
        consultaActividadDialog.setLocationRelativeTo(frame);
        consultaActividadDialog.getContentPane().setLayout(new BorderLayout());

        consultaActividadInternalFrame = new ConsultaActividadDeportiva(icon, consultaActividadDialog);
        consultaActividadDialog.getContentPane().add(consultaActividadInternalFrame);
        consultaActividadDialog.setLocationRelativeTo(null);
        consultaActividadDialog.setVisible(false);
        consultaActividadInternalFrame.setVisible(true);
        
        registroADictadoClaseDialog = new JDialog(frame, "Registro a Dictado Clase", true);
        registroADictadoClaseDialog.setSize(390, 420);
        registroADictadoClaseDialog.setLocationRelativeTo(frame);
        registroADictadoClaseDialog.getContentPane().setLayout(new BorderLayout());

        registroADictadoClaseInternalFrame = new RegistroADictadoClase(icon, registroADictadoClaseDialog);
        registroADictadoClaseDialog.getContentPane().add(registroADictadoClaseInternalFrame);
        registroADictadoClaseDialog.setLocationRelativeTo(null);
        registroADictadoClaseDialog.setVisible(false);
        registroADictadoClaseInternalFrame.setVisible(true);
        
        rankingClaseDialog = new JDialog(frame, "Ranking de Clases", true);
        rankingClaseDialog.setSize(300, 210);
        rankingClaseDialog.setLocationRelativeTo(frame);
        rankingClaseDialog.getContentPane().setLayout(new BorderLayout());
        
        rankingClaseInternalFrame = new RankingClase(icon, rankingClaseDialog);
        rankingClaseDialog.getContentPane().add(rankingClaseInternalFrame);
        rankingClaseDialog.setLocationRelativeTo(null);
        rankingClaseDialog.setVisible(false);
        rankingClaseInternalFrame.setVisible(true);
        
        modificarUsuarioDialog = new JDialog(frame, "Modificar Usuario", true);
        modificarUsuarioDialog.setSize(350, 550);
        modificarUsuarioDialog.setLocationRelativeTo(frame);
        modificarUsuarioDialog.getContentPane().setLayout(new BorderLayout());
        
        modificarUsuarioInternalFrame = new ModificarUsuario(icon, modificarUsuarioDialog);
        modificarUsuarioDialog.getContentPane().add(modificarUsuarioInternalFrame);
        modificarUsuarioDialog.setLocationRelativeTo(null);
        modificarUsuarioDialog.setVisible(false);
        modificarUsuarioInternalFrame.setVisible(true);
        
        consultaDictadoClaseDialog = new JDialog(frame, "Consulta Dictado Clase", true);
        consultaDictadoClaseDialog.setSize(390, 450);
        consultaDictadoClaseDialog.setLocationRelativeTo(frame);
        consultaDictadoClaseDialog.getContentPane().setLayout(new BorderLayout());
        
        consultaDictadoClaseInternalFrame = new ConsultaDictadoClase(icon, consultaDictadoClaseDialog);
        consultaDictadoClaseDialog.getContentPane().add(consultaDictadoClaseInternalFrame);
        consultaDictadoClaseDialog.setLocationRelativeTo(null);
        consultaDictadoClaseDialog.setVisible(false);
        consultaDictadoClaseInternalFrame.setVisible(true);
        
        modificarActividadDeportivaDialog = new JDialog(frame, "Consulta Dictado Clase", true);
        modificarActividadDeportivaDialog.setSize(390, 450);
        modificarActividadDeportivaDialog.setLocationRelativeTo(frame);
        modificarActividadDeportivaDialog.getContentPane().setLayout(new BorderLayout());
        
        modificarActividadDeportivaInternalFrame = new ModificarActividadDeportiva(icon, modificarActividadDeportivaDialog);
        modificarActividadDeportivaDialog.getContentPane().add(modificarActividadDeportivaInternalFrame);
        modificarActividadDeportivaDialog.setLocationRelativeTo(null);
        modificarActividadDeportivaDialog.setVisible(false);
        modificarActividadDeportivaInternalFrame.setVisible(true);
        
        
        rankingActividadDeportivaDialog = new JDialog(frame, "Consulta Dictado Clase", true);
        rankingActividadDeportivaDialog.setSize(390, 450);
        rankingActividadDeportivaDialog.setLocationRelativeTo(frame);
        rankingActividadDeportivaDialog.getContentPane().setLayout(new BorderLayout());
        
        rankingActividadDeportivaInternalFrame = new RankingActividadDeportiva(icon, rankingActividadDeportivaDialog);
        rankingActividadDeportivaDialog.getContentPane().add(rankingActividadDeportivaInternalFrame);
        rankingActividadDeportivaDialog.setLocationRelativeTo(null);
        rankingActividadDeportivaDialog.setVisible(false);
        rankingActividadDeportivaInternalFrame.setVisible(true);
    }

    public void iniciar() {
    	// Título de la ventana
        frame = new JFrame();
        frame.setTitle("Entrenamos.uy");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imagen = new ImageIcon(getClass().getResource("Icon.jpg"));
        frame.setIconImage(imagen.getImage());
        frame.setResizable(false);

        cardLayout = new CardLayout();
        panelDeTarjetas = new JPanel(cardLayout);

        // Crear Panel de Bienvenida con GIF y Botón "Entrar"
        JPanel panelBienvenida = crearPanelBienvenida();
        panelDeTarjetas.add(panelBienvenida, "bienvenida");

        // Crear Panel de Otro Menú
        JPanel panelPrincipal = crearPanelPrincipal();
        panelDeTarjetas.add(panelPrincipal, "otroMenu");

        // Establecer la vista inicial en la pantalla de bienvenida
        cardLayout.show(panelDeTarjetas, "bienvenida");

        // Agregar el panelDeTarjetas al frame
        frame.getContentPane().add(panelDeTarjetas);

        frame.setVisible(true);
    }

    private JPanel crearPanelBienvenida() {
        JPanel panelBienvenida = new JPanel(null); // Usar layout nulo para controlar la posición manualmente

        // Cargar el GIF y crear un JLabel para mostrarlo
        ImageIcon iconoGif = new ImageIcon(getClass().getResource("Presentacion.gif"));
        JLabel gif = new JLabel(iconoGif);
        gif.setBounds(0, 0, 800, 600);
        gif.setText("Bienvenido a Entrenamos.uy");
        gif.setHorizontalTextPosition(JLabel.CENTER);
        gif.setVerticalTextPosition(JLabel.TOP);
        gif.setForeground(Color.WHITE);
        gif.setFont(new Font("MV Boli", Font.PLAIN, 40));
        gif.setIconTextGap(-100);

        // Crear botones "Entrar" y "Salir"
        final JButton botonEntrar = new JButton("Entrar");
        botonEntrar.setForeground(Color.WHITE);
        botonEntrar.setBackground(new Color(54, 61, 75));
        botonEntrar.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        botonEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonEntrar.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
                botonEntrar.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });

        // Agregar ActionListener para cambiar a otro menú con animación
        botonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAnimacion(panelDeTarjetas, -800, "otroMenu");
            }
        });

        final JButton botonSalir = new JButton("Salir");
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setBackground(new Color(54, 61, 75));
        botonSalir.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonSalir.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
                botonSalir.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });

        // Agregar ActionListener para cerrar el programa
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Establecer tamaño preferido para los botones
        Dimension tamanioBoton = new Dimension(120, 40);
        botonEntrar.setPreferredSize(tamanioBoton);
        botonSalir.setPreferredSize(tamanioBoton);

        // Crear un JPanel para los botones con FlowLayout
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 80));
        panelBotones.setBounds(0, 600, 800, 200);
        panelBotones.setBorder(null);
        panelBotones.setBackground(new Color(54, 61, 75));
        panelBotones.add(botonEntrar);
        panelBotones.add(botonSalir);

        // Agregar componentes al panelBienvenida
        panelBienvenida.setBackground(new Color(54, 61, 75));
        panelBienvenida.setLayout(null);
        panelBienvenida.add(gif);
        panelBienvenida.add(panelBotones);

        return panelBienvenida;
    }
    
 // Método para realizar la animación de deslizamiento
    private void realizarAnimacion(final JPanel panel, final int destino, final String proximoPanel) {
        final int velocidad = 10; // Velocidad de la animación
        final Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int posicionY = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (posicionY > destino) {
                    panel.setBounds(0, posicionY, 800, 800);
                    posicionY -= velocidad;
                    
                } else {
                    timer.stop();
                    cardLayout.show(panelDeTarjetas, proximoPanel);
                    panel.setBounds(0, 0, 800, 800); // Restaurar la posición
                }
            }
        });
        timer.start();
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(54, 61, 75));
        panelPrincipal.setLayout(null);
        
        ImageIcon icon = new ImageIcon(Principal.class.getResource("/presentacion/transparencia.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconoInfo = new ImageIcon(image);
        
        ImageIcon iconMas = new ImageIcon(Principal.class.getResource("/presentacion/mas.png"));
        Image imageMas = iconMas.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconoMas = new ImageIcon(imageMas);
        
        // Botón salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(54, 61, 75));
        btnSalir.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSalir.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnSalir.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        // Botón alta institución
        JButton btnAltaInstitucion = new JButton("Alta Institucion");
        btnAltaInstitucion.setIcon(iconoMas);
        btnAltaInstitucion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		agregarInstitutoDialog.setVisible(true);
        	}
        });
        btnAltaInstitucion.setForeground(Color.WHITE);
        btnAltaInstitucion.setBackground(new Color(54, 61, 75));
        btnAltaInstitucion.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnAltaInstitucion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAltaInstitucion.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAltaInstitucion.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnAltaInstitucion.setBounds(83, 276, 273, 53);
        panelPrincipal.add(btnAltaInstitucion);
        btnSalir.setBounds(319, 681, 158, 45);
        panelPrincipal.add(btnSalir);
        
        // Boton informacion
        JButton btnInformacionUsuario = new JButton("Informacion Usuario");
        btnInformacionUsuario.setIcon(iconoInfo);
        btnInformacionUsuario.setSelected(true);
        btnInformacionUsuario.setSize(new Dimension(9, 10));
        btnInformacionUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		consultaUsuarioInternalFrame.inciarComboBoxUsuario();
        		consultaUsuarioDialog.setVisible(true);
        	}
        });
        btnInformacionUsuario.setForeground(Color.WHITE);
        btnInformacionUsuario.setBackground(new Color(54, 61, 75));
        btnInformacionUsuario.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnInformacionUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnInformacionUsuario.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnInformacionUsuario.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnInformacionUsuario.setBounds(387, 195, 273, 53);
        panelPrincipal.add(btnInformacionUsuario);

        // Boton alta usuario
        JButton btnAltaUsuario = new JButton("Alta Usuario");
        btnAltaUsuario.setIcon(iconoMas);
        btnAltaUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		agregarUsuarioInternalFrame.inicializarComboBoxes();
        		agregarUsuarioDialog.setVisible(true);
        	}
        });
        btnAltaUsuario.setForeground(Color.WHITE);
        btnAltaUsuario.setBackground(new Color(54, 61, 75));
        btnAltaUsuario.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnAltaUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAltaUsuario.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAltaUsuario.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnAltaUsuario.setBounds(83, 136, 273, 53);
        panelPrincipal.add(btnAltaUsuario);
        
     // Boton alta usuario
        JButton btnLogIn = new JButton("Log In");
        btnLogIn.setIcon(iconoMas);
        btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LogInDialog.setVisible(true);
        	}
        });
        btnLogIn.setForeground(Color.WHITE);
        btnLogIn.setBackground(new Color(54, 61, 75));
        btnLogIn.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnLogIn.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnLogIn.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnLogIn.setBounds(83, 211, 273, 53);
        panelPrincipal.add(btnLogIn);
        
        // Boton alta actividad deportiva
        JButton btnAltaActividadDeportiva = new JButton("Alta Actividad Deportiva");
        btnAltaActividadDeportiva.setIcon(iconoMas);
        btnAltaActividadDeportiva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		agregarActividadDeportivaInternalFrame.iniciarlizarComboBoxes();
        		agregarActividadDeportivaDialog.setVisible(true);
        	}
        });
        btnAltaActividadDeportiva.setForeground(Color.WHITE);
        btnAltaActividadDeportiva.setBackground(new Color(54, 61, 75));
        btnAltaActividadDeportiva.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnAltaActividadDeportiva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAltaActividadDeportiva.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAltaActividadDeportiva.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnAltaActividadDeportiva.setBounds(83, 355, 273, 53);
        panelPrincipal.add(btnAltaActividadDeportiva);
        
        // Boton alta dictado clase
        JButton btnAltaDictadoDeClase = new JButton("Alta Dictado De Clase");
        btnAltaDictadoDeClase.setIcon(iconoMas);
        btnAltaDictadoDeClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//if(!altaDictadoClaseInternalFrame.checkearComboBoxes()) {
        			altaDictadoClaseInternalFrame.inicializarComboBoxes();
            		altaDictadoClaseDialog.setVisible(true);
        		//}
        	}
        });
        btnAltaDictadoDeClase.setForeground(Color.WHITE);
        btnAltaDictadoDeClase.setBackground(new Color(54, 61, 75));
        btnAltaDictadoDeClase.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnAltaDictadoDeClase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnAltaDictadoDeClase.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnAltaDictadoDeClase.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnAltaDictadoDeClase.setBounds(83, 436, 273, 53);
        panelPrincipal.add(btnAltaDictadoDeClase);
        
        JButton btnModificarUsuario = new JButton("Modificar Usuario");
        btnModificarUsuario.setIcon(iconoMas);
        btnModificarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//if(!altaDictadoClaseInternalFrame.checkearComboBoxes()) {
        			modificarUsuarioInternalFrame.inciarComboBoxUsuario();
            		modificarUsuarioDialog.setVisible(true);
        		//}
        	}
        });
        btnModificarUsuario.setForeground(Color.WHITE);
        btnModificarUsuario.setBackground(new Color(54, 61, 75));
        btnModificarUsuario.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnModificarUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnModificarUsuario.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnModificarUsuario.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnModificarUsuario.setBounds(387, 276, 273, 53);  
        panelPrincipal.add(btnModificarUsuario);
        
        
        ////////////////////
        
        JButton btnConsultaClase = new JButton("Consulta Dictado Clase");
        btnConsultaClase.setIcon(iconoInfo);
        btnConsultaClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//if(!altaDictadoClaseInternalFrame.checkearComboBoxes()) {
        			consultaDictadoClaseInternalFrame.inicializarComboBoxes();
        			consultaDictadoClaseDialog.setVisible(true);
        		//}
        	}
        });
        btnConsultaClase.setForeground(Color.WHITE);
        btnConsultaClase.setBackground(new Color(54, 61, 75));
        btnConsultaClase.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnConsultaClase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnConsultaClase.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnConsultaClase.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnConsultaClase.setBounds(387, 436, 273, 53);
        panelPrincipal.add(btnConsultaClase);
        
        JButton btnModificarInstitucion = new JButton("Modificar Institucion");
        btnModificarInstitucion.setIcon(iconoInfo);
        btnModificarInstitucion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			modificarInstitucionInternalFrame.inciarComboBoxInstitucion();
        			modificarInstitucionDialog.setVisible(true);
        	}
        });
        btnModificarInstitucion.setForeground(Color.WHITE);
        btnModificarInstitucion.setBackground(new Color(54, 61, 75));
        btnModificarInstitucion.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnModificarInstitucion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnModificarInstitucion.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnModificarInstitucion.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnModificarInstitucion.setBounds(387, 593, 273, 53);
        panelPrincipal.add(btnModificarInstitucion);
        
        
        
        //////////////////////////
        
        JButton btnRankingActividad = new JButton("Ranking Actividad Deportiva");
        btnRankingActividad.setIcon(iconoInfo);
        btnRankingActividad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			rankingActividadDeportivaInternalFrame.iniciarComboBoxRankingActividad();
        			rankingActividadDeportivaDialog.setVisible(true);
        	}
        });
        btnRankingActividad.setForeground(Color.WHITE);
        btnRankingActividad.setBackground(new Color(54, 61, 75));
        btnRankingActividad.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnRankingActividad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnRankingActividad.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnRankingActividad.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnRankingActividad.setBounds(387, 620, 273, 53);
        panelPrincipal.add(btnRankingActividad);
        
        
        JButton btnModificarActividadDeportiva = new JButton("Modificar Actividad Deportiva");
        btnModificarActividadDeportiva.setIcon(iconoMas);
        btnModificarActividadDeportiva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//if(!altaDictadoClaseInternalFrame.checkearComboBoxes()) {
        			modificarActividadDeportivaInternalFrame.inciarComboBoxUsuario();
            		modificarActividadDeportivaDialog.setVisible(true);
        		//}
        	}
        });
        btnModificarActividadDeportiva.setForeground(Color.WHITE);
        btnModificarActividadDeportiva.setBackground(new Color(54, 61, 75));
        btnModificarActividadDeportiva.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnModificarActividadDeportiva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnModificarActividadDeportiva.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnModificarActividadDeportiva.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        btnModificarActividadDeportiva.setBounds(387, 515, 273, 53);  
        panelPrincipal.add(btnModificarActividadDeportiva);
        
        
        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenu.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 70));
        lblMenu.setForeground(new Color(255, 255, 255));
        lblMenu.setBackground(new Color(255, 255, 255));
        lblMenu.setBounds(250, 22, 282, 148);
        panelPrincipal.add(lblMenu);
        
        // Botón Informaciónn deportiva
        JButton btnInformacionActividadDeportiva = new JButton("Informacion Actividad Deportiva");
        btnInformacionActividadDeportiva.setIcon(iconoInfo);
        btnInformacionActividadDeportiva.setSize(new Dimension(9, 10));
        btnInformacionActividadDeportiva.setSelected(true);
        btnInformacionActividadDeportiva.setForeground(Color.WHITE);
        btnInformacionActividadDeportiva.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnInformacionActividadDeportiva.setBackground(new Color(54, 61, 75));
        btnInformacionActividadDeportiva.setBounds(387, 355, 273, 53); 
        btnInformacionActividadDeportiva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		consultaActividadInternalFrame.inciarComboBoxActividadDeportiva();
        		consultaActividadDialog.setVisible(true);
        	}
        });
        btnInformacionActividadDeportiva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnInformacionActividadDeportiva.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            	//moveButtonRandomly(btnAltaDictadoDeClase, frame);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnInformacionActividadDeportiva.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        panelPrincipal.add(btnInformacionActividadDeportiva);
        
        // Botón Registro a dictado clase
        JButton btnRegistroDictadoClase = new JButton("Registro A Dictado De Clase");
        btnRegistroDictadoClase.setIcon(iconoMas);
        btnRegistroDictadoClase.setSelected(true);
        btnRegistroDictadoClase.setForeground(Color.WHITE);
        btnRegistroDictadoClase.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnRegistroDictadoClase.setBackground(new Color(54, 61, 75));
        btnRegistroDictadoClase.setBounds(83, 515, 273, 53);
        btnRegistroDictadoClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registroADictadoClaseInternalFrame.inicializarComboBoxes();
        		registroADictadoClaseDialog.setVisible(true);
        	}
        });
        btnRegistroDictadoClase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnRegistroDictadoClase.setBackground(new Color(69, 78, 95)); // Lighter blue when hovering
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnRegistroDictadoClase.setBackground(new Color(54, 61, 75)); // Original color when not hovering
            }
        });
        panelPrincipal.add(btnRegistroDictadoClase);
        
        // Boton Ranking de Clases
        JButton btnRankingClases = new JButton("Ranking de Clases");
        btnRankingClases.setIcon(iconoMas);
        btnRankingClases.setSelected(true);
        btnRankingClases.setForeground(Color.WHITE);
        btnRankingClases.setBorder(new LineBorder(new Color(33, 37, 43), 3, true));
        btnRankingClases.setBackground(new Color(54, 61, 75));
        btnRankingClases.setBounds(83, 593, 273, 53);
        btnRankingClases.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rankingClaseInternalFrame.iniciarComboBoxClase();
        		rankingClaseDialog.setVisible(true);
        	}
        });
        panelPrincipal.add(btnRankingClases);
        
        return panelPrincipal;
    } 
    
    
    
    //La funcion del trolleo
    public void moveButtonRandomly(JButton button, JFrame frame) {
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        int maxX = frameWidth - buttonWidth;
        int maxY = frameHeight - buttonHeight;

        int randomX = (int) (Math.random() * maxX);
        int randomY = (int) (Math.random() * maxY);

        button.setLocation(randomX, randomY);
    }
}





