
package aplicacionpaint;


//Librerias
import java.awt.BasicStroke;
import java.awt.BorderLayout;

import java.awt.Graphics;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Insets;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.QuadCurve2D;
import javafx.scene.layout.Border;
import javafx.scene.shape.CubicCurve;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Damian Bautista
 */

public class Paint extends JFrame implements ActionListener, MouseListener {

    // Variables para los menús y elementos de menú
    JMenu archivo, dibujar, ayuda;
    JMenuItem acerca, salir, nuevo, guardar, abrir, color;
    JRadioButtonMenuItem linea, rectangulo, elipse;
    JCheckBoxMenuItem relleno;
    JColorChooser colorChooser = new JColorChooser();
    ButtonGroup btn;
    MiPanel miPanel;
    
    // Botones de la interfaz gráfica
    JButton botonLapiz, btnLinea, btnTrian, btnCuadrado, btnpent, btnHexagono, btnHeptag, btnOcta, btnCircu, btnGoma,btnComex;
    JButton btnCorazon, btnCurva, btnGuardar, btnSalir, btnNuevo, btnAbrirArchivo;
    JButton Cnegro, Cgriz, CRojo, Cnaranja, Camarillo, Cverde, CazulC, CazulM, Cmorado, Cblanco, Crosa, Ccarmin,todo;
    JTextField campo;
    
    // ComboBox para seleccionar el tipo de fuente
    static javax.swing.JComboBox<String> jComboBox1= new javax.swing.JComboBox<>();


    
    /**
     * Constructor de la clase Paint.
     * Crea una instancia de la aplicación Paint.
     */
    public Paint() {
        // Crear el menú
        crearMenu();
        // Crear el panel de dibujo
        miPanel = new MiPanel();
        // Configurar el diseño del contenedor principal
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        // Agregar el panel superior al contenedor
        contenedor.add(panelSuperiorPrin(), BorderLayout.NORTH);
        // Agregar el panel inferior (panel de dibujo) al contenedor
        contenedor.add(panelInferiorPrin(), BorderLayout.CENTER);

        // Configurar propiedades de la ventana principal
        this.setSize(1700, 1025); 
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Aplicación Paint");
        // Establecer el ícono de la ventana
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pintura2.png")).getImage());
        // Agregar escuchadores de eventos
        addListeners();
    }

    /**
     * Crea y devuelve el panel superior principal.
     *
     * @return JPanel que representa el panel superior principal.
     */
    public JPanel panelSuperiorPrin() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(new Color(231, 208, 255));
        // Agregar el panel superior secundario al oeste del panel principal
        panel1.add(panelSuperiorS(), BorderLayout.WEST);
        
        // Agregar espacios en blanco en las regiones este, norte y sur del panel principal
        panel1.add(new JLabel("   "), BorderLayout.EAST);
        panel1.add(new JLabel("   "), BorderLayout.NORTH);
        panel1.add(new JLabel("   "), BorderLayout.SOUTH);

        return panel1;
    }

    /**
     * Crea y devuelve el panel superior secundario.
     *
     * @return JPanel que representa el panel superior secundario.
     */
    public JPanel panelSuperiorS() {
        JPanel panel1 = new JPanel();
        Color panel8 = this.miPanel.getColorActual();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(231, 208, 255));
        
        // Agregar etiquetas y paneles relacionados al panel principal
        panel1.add(new JLabel("   "));
        panel1.add(panelTexto());
        panel1.add(new JLabel("   "));
        panel1.add(PanelFigura());
        panel1.add(new JLabel("   "));
        panel1.add(panelGros());
        panel1.add(new JLabel("   "));
        panel1.add(panelCuadri());
        panel1.add(new JLabel("   "));
        panel1.add(panelColores());
        panel1.add(new JLabel("   "));
        panel1.add(panelDatos());
        
        return panel1;
    }

    JButton BotonTexto;
    boolean activado = false;
    JButton BotonN,btncubeta;
    JButton BotonS, BotonSet, botonApl;
    JSpinner jSpinner1;
    

    /**
     * Crea y devuelve el panel de texto.
     *
     * @return JPanel que representa el panel de texto.
     */
    public JPanel panelTexto() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(245, 246, 248));
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(239, 195, 254));
        
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.setBackground(new Color(245, 246, 248));
        
        // Crear componentes y paneles relacionados al panel de texto
        campo = new JTextField(20);
        panel4.add(campo, BorderLayout.WEST);
        
        // Configurar el JComboBox para seleccionar el tipo de fuente
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Times New Roman", "Garamond", "Cambria", "Georgia", "Palatino", "Arial", "Calibri", "Helvetica", "Verdana", "Trebuchet MS", "Brush Script", "Zapfino", "Lucida Handwriting", "Courier New", "Consolas", "American Typewriter" }));
        jComboBox1.setBackground(new Color(231, 208, 255));
        jComboBox1.setBorder(null);
        panel1.add(jComboBox1);
        
        // Configurar el botón "N"
        BotonN = new JButton(" N ");
        BotonN.setBackground(new Color(231, 208, 255));
        BotonN.setBorder(null);
        BotonN.setMaximumSize(new Dimension(1000, 1000));
        BotonN.setFont(new Font("Century", Font.PLAIN, 15));
        BotonN.setHorizontalAlignment(JButton.CENTER);
        panel1.add(BotonN);
        
        // Configurar el botón "S"
        BotonS = new JButton();
        String sub = "<html><u> S </u></html>";
        BotonS.setText(sub);
        BotonS.setBackground(new Color(231, 208, 255));
        BotonS.setBorder(null);
        BotonS.setFont(new Font("ARIAL", Font.PLAIN, 15));
        BotonS.setHorizontalAlignment(JButton.CENTER);
        panel1.add(BotonS);
        
        // Configurar el JSpinner para seleccionar el tamaño de fuente
        int min = 4;
        int max = 200;
        int step = 1;
        int i = 30;
        SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
        jSpinner1 = new JSpinner(value);
        Dimension d = jSpinner1.getPreferredSize();
        d.width = 50;
        jSpinner1.setPreferredSize(d);
        panel1.add(jSpinner1);
        panel1.add(new JLabel("   "));
        
        
        todo = new JButton();
        URL rutita = Paint.class.getResource("/imagenes/RGB.png");
        Image ima = new ImageIcon(rutita).getImage();//redimencion
        ImageIcon image = new ImageIcon(ima.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        todo.setBackground(new Color(245, 246, 248));
        todo.setBorder(null);
        todo.setIcon(image);
        panel1.add(todo);
        
        /*-------------------*/
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));
        
        

        JLabel text = new JLabel("EDITOR DE TEXTO");
        text.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(text, BorderLayout.SOUTH);

        panel3.add(campo, BorderLayout.AFTER_LAST_LINE);
        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel2, BorderLayout.NORTH);

        return panel3;
        
        
        
    }
    
    
    /**
     * Crea y devuelve el panel de datos.
     *
     * @return JPanel que representa el panel de datos.
     */
    public JPanel panelDatos() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(245, 246, 248));

        // Configurar botón "Guardar"
        btnGuardar = new JButton();
        URL rutita = Paint.class.getResource("/imagenes/guardar.png");
        Image ima = new ImageIcon(rutita).getImage();//redimencion
        ImageIcon image = new ImageIcon(ima.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(image);
        btnGuardar.setBackground(new Color(245, 246, 248));
        btnGuardar.setBorder(null);
        panel1.add(btnGuardar);
        
        // Configurar botón "Abrir archivo"
        btnAbrirArchivo = new JButton();
        rutita = Paint.class.getResource("/imagenes/abrirArchivo.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        btnAbrirArchivo.setIcon(image);
        btnAbrirArchivo.setBackground(new Color(245, 246, 248));
        btnAbrirArchivo.setBorder(null);
        panel1.add(btnAbrirArchivo);
        
        // Configurar botón "Nuevo"
        btnNuevo = new JButton();
        rutita = Paint.class.getResource("/imagenes/nuevo.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        btnNuevo.setIcon(image);
        btnNuevo.setBackground(new Color(245, 246, 248));
        btnNuevo.setBorder(null);
        panel1.add(btnNuevo);
        
        // Configurar botón "Salir"
        btnSalir = new JButton();
        rutita = Paint.class.getResource("/imagenes/salir.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        btnSalir.setIcon(image);
        btnSalir.setBackground(new Color(245, 246, 248));
        btnSalir.setBorder(null);
        panel1.add(btnSalir);
        

         /*-------------------*/
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));

        JLabel text = new JLabel("Archivo");
        text.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(text, BorderLayout.SOUTH);
        //-----------------------
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(245, 246, 248));

        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel1, BorderLayout.EAST); //PONERLOS EN HORIZONTAL TODOS
        panel3.add(panel2, BorderLayout.BEFORE_FIRST_LINE);

        return panel3;
    }
    
    /**
     * Crea y devuelve el panel de colores.
     *
     * @return JPanel que representa el panel de colores.
     */
    public JPanel panelColores() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(245, 246, 248));
        
        
        // Configurar botones de colores
        Cnegro = new JButton();
        URL rutita = Paint.class.getResource("/colores/negro-100.png");
        Image ima = new ImageIcon(rutita).getImage();//redimencion
        ImageIcon image = new ImageIcon(ima.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        Cnegro.setIcon(image);
        Cnegro.setBackground(new Color(245, 246, 248));
        Cnegro.setBorder(null);
        panel1.add(Cnegro);
        
        Cgriz = new JButton();
        rutita = Paint.class.getResource("/colores/gris-100.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        Cgriz.setIcon(image);
        Cgriz.setBackground(new Color(245, 246, 248));
        Cgriz.setBorder(null);
        panel1.add(Cgriz);
        
        CRojo = new JButton();
        CRojo.setBackground(new Color(241, 23, 44));
        CRojo.setPreferredSize(new Dimension(20, 20));
        panel1.add(CRojo);
        
        Cnaranja = new JButton();
        Cnaranja.setBackground(new Color(254, 128, 33));
        Cnaranja.setPreferredSize(new Dimension(20, 20));
        panel1.add(Cnaranja);
        
        Camarillo = new JButton();
        Camarillo.setBackground(new Color(248, 245, 1));
        Camarillo.setPreferredSize(new Dimension(20, 20));
        panel1.add(Camarillo);
        
        Cverde = new JButton();
        Cverde.setBackground(new Color(32, 177, 80));
        Cverde.setPreferredSize(new Dimension(20, 20));
        panel1.add(Cverde);
        
        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout());
        panel11.setBackground(new Color(245, 246, 248));
        
        Cblanco = new JButton();
        Cblanco.setBackground(new Color(255, 255, 255));
        Cblanco.setPreferredSize(new Dimension(20, 20));
        panel1.add(Cblanco);
        
        Crosa = new JButton();
        Crosa.setBackground(new Color(255, 170, 205));
        Crosa.setPreferredSize(new Dimension(20, 20));
        panel1.add(Crosa);
        
        CazulC = new JButton();
        CazulC.setBackground(new Color(0, 163, 231));
        CazulC.setPreferredSize(new Dimension(20, 20));
        panel1.add(CazulC);
        
        CazulM = new JButton();
        CazulM.setBackground(new Color(68, 69, 206));
        CazulM.setPreferredSize(new Dimension(20, 20));
        panel1.add(CazulM);
        
        Cmorado = new JButton();
        Cmorado.setBackground(new Color(162, 73, 164));
        Cmorado.setPreferredSize(new Dimension(20, 20));
        panel1.add(Cmorado);
        
        Ccarmin = new JButton();
        Ccarmin.setBackground(new Color(113, 12, 31));
        Ccarmin.setPreferredSize(new Dimension(20, 20));
        panel1.add(Ccarmin);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));
        
        JLabel text = new JLabel("Colores");
        text.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(text, BorderLayout.CENTER);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(245, 246, 248));

        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel11, BorderLayout.SOUTH);
        panel3.add(panel2, BorderLayout.BEFORE_FIRST_LINE);
        
        return panel3;
    }

    public JPanel PanelFigura() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(245, 246, 248));

        botonLapiz = new JButton();
        URL rutita = Paint.class.getResource("/imagenes/Lapiz.png");
        Image ima = new ImageIcon(rutita).getImage();//redimencion
        ImageIcon image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        botonLapiz.setIcon(image);
        botonLapiz.setBackground(new Color(245, 246, 248));
        botonLapiz.setBorder(null);
        panel1.add(botonLapiz);

        btnGoma = new JButton();
        rutita = Paint.class.getResource("/imagenes/goma.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnGoma.setIcon(image);
        btnGoma.setBackground(new Color(245, 246, 248));
        btnGoma.setBorder(null);
        panel1.add(btnGoma);

        btnLinea = new JButton();
        rutita = Paint.class.getResource("/imagenes/linea.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnLinea.setIcon(image);
        btnLinea.setBackground(new Color(245, 246, 248));
        btnLinea.setBorder(null);
        panel1.add(btnLinea);

        btnTrian = new JButton();
        rutita = Paint.class.getResource("/imagenes/triangulo.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnTrian.setIcon(image);
        btnTrian.setBackground(new Color(245, 246, 248));
        btnTrian.setBorder(null);
        panel1.add(btnTrian);

        btnCuadrado = new JButton();
        rutita = Paint.class.getResource("/imagenes/cuadrado.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnCuadrado.setIcon(image);
        btnCuadrado.setBackground(new Color(245, 246, 248));
        btnCuadrado.setBorder(null);

        panel1.add(btnCuadrado);
        //-----------
        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout());
        panel11.setBackground(new Color(245, 246, 248));

        btnpent = new JButton();
        rutita = Paint.class.getResource("/imagenes/pentagono.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnpent.setIcon(image);
        btnpent.setBackground(new Color(245, 246, 248));
        btnpent.setBorder(null);
        panel11.add(btnpent);

        btnHexagono = new JButton();
        rutita = Paint.class.getResource("/imagenes/hexagon.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnHexagono.setIcon(image);
        btnHexagono.setBackground(new Color(245, 246, 248));
        btnHexagono.setBorder(null);
        panel11.add(btnHexagono);

        btnOcta = new JButton();
        rutita = Paint.class.getResource("/imagenes/rombo.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnOcta.setIcon(image);
        btnOcta.setBackground(new Color(245, 246, 248));
        btnOcta.setBorder(null);
        panel11.add(btnOcta);

        btnCircu = new JButton();
        rutita = Paint.class.getResource("/imagenes/circulo.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnCircu.setIcon(image);
        btnCircu.setBackground(new Color(245, 246, 248));
        btnCircu.setBorder(null);
        panel11.add(btnCircu);
        
        
        
        btnCorazon = new JButton();
        rutita = Paint.class.getResource("/imagenes/corazon.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnCorazon.setIcon(image);
        btnCorazon.setBackground(new Color(245, 246, 248));
        btnCorazon.setBorder(null);
        panel1.add(btnCorazon);
        
        btnCurva = new JButton();
        rutita = Paint.class.getResource("/imagenes/curva.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btnCurva.setIcon(image);
        btnCurva.setBackground(new Color(245, 246, 248));
        btnCurva.setBorder(null);
        panel1.add(btnCurva);
        
        btncubeta = new JButton();
        rutita = Paint.class.getResource("/imagenes/bote.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        btncubeta.setIcon(image);
        btncubeta.setBackground(new Color(245, 246, 248));
        btncubeta.setBorder(null);
        //panel11.add(btncubeta);
        
        BotonTexto = new JButton(" TEXTO ");
        BotonTexto.setBackground(new Color(245, 246, 248));
        BotonTexto.setBorder(null);
        BotonTexto.setMaximumSize(new Dimension(1000, 1000));
        BotonTexto.setFont(new Font("Century", Font.PLAIN, 23));
        BotonTexto.setHorizontalAlignment(JButton.CENTER);
        panel11.add(BotonTexto);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));
        JLabel text = new JLabel("Figuras");
        text.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(text, BorderLayout.SOUTH);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(245, 246, 248));

        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel11, BorderLayout.EAST); //PONERLOS EN HORIZONTAL TODOS
        panel3.add(panel2, BorderLayout.BEFORE_FIRST_LINE);

        return panel3;
    }

    public JPanel panelInferiorPrin() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        panel1.setBackground(new Color(212, 221, 236));
        JLabel b0 = new JLabel("  ");
        b0.setFont(new Font("Arial", Font.PLAIN, 5));
        JLabel b1 = new JLabel("  ");
        b1.setFont(new Font("Arial", Font.PLAIN, 5));

        panel1.add(new JLabel("   "), BorderLayout.WEST);
        panel1.add(new JLabel("   "), BorderLayout.EAST);
        panel1.add(miPanel, BorderLayout.CENTER);
        panel1.add(b0, BorderLayout.NORTH);
        panel1.add(b1, BorderLayout.SOUTH);

        return panel1;
    }

    JButton btndelgado, btnmediog, btgruesoss;

    public JPanel panelGros() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(new Color(245, 246, 248));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(245, 246, 248));

        btndelgado = new JButton();
        URL rutita = Paint.class.getResource("/imagenes/linea.png");
        Image ima = new ImageIcon(rutita).getImage();//redimencion
        ImageIcon image = new ImageIcon(ima.getScaledInstance(50, 27, Image.SCALE_SMOOTH));
        btndelgado.setIcon(image);
        btndelgado.setBackground(new Color(245, 246, 248));
        btndelgado.setBorder(null);
        panel1.add(btndelgado, BorderLayout.WEST);

        btnmediog = new JButton();
        rutita = Paint.class.getResource("/imagenes/linea2.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(50, 27, Image.SCALE_SMOOTH));
        btnmediog.setIcon(image);
        btnmediog.setBackground(new Color(245, 246, 248));
        btnmediog.setBorder(null);
        panel1.add(btnmediog, BorderLayout.CENTER);

        btgruesoss = new JButton();
        rutita = Paint.class.getResource("/imagenes/linea3.png");
        ima = new ImageIcon(rutita).getImage();//redimencion
        image = new ImageIcon(ima.getScaledInstance(50, 27, Image.SCALE_SMOOTH));
        btgruesoss.setIcon(image);
        btgruesoss.setBackground(new Color(245, 246, 248));
        btgruesoss.setBorder(null);
        panel1.add(btgruesoss, BorderLayout.EAST);
        /*-------------------*/
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));

        JLabel text = new JLabel("Grosor");
        text.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(text, BorderLayout.SOUTH);

        panel3.add(panel1, BorderLayout.CENTER);
        panel3.add(panel2, BorderLayout.BEFORE_FIRST_LINE);

        return panel3;
    }

    JCheckBox cuadriculado, rellenarr;

    public JPanel panelCuadri() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(new Color(245, 246, 248));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setBackground(new Color(245, 246, 248));

        cuadriculado = new JCheckBox();

        cuadriculado.setText("Cuadriculado");
        cuadriculado.setBackground(new Color(245, 246, 248));
        //panel1.add(cuadriculado, BorderLayout.CENTER);

        rellenarr = new JCheckBox();

        rellenarr.setText("Rellenar");
        rellenarr.setBackground(new Color(245, 246, 248));
        panel1.add(rellenarr, BorderLayout.SOUTH);
        /*-------------------*/
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(new Color(245, 246, 248));

        JLabel text = new JLabel("Grosor");
        text.setHorizontalAlignment(JLabel.CENTER);

        panel2.add(text, BorderLayout.SOUTH);

        panel3.add(panel1, BorderLayout.NORTH);

        return panel3;
    }

    private void addListeners() {
        acerca.addActionListener(this);
        salir.addActionListener(this);
        nuevo.addActionListener(this);
        guardar.addActionListener(this);
        abrir.addActionListener(this);
        color.addActionListener(this);
        rellenarr.addActionListener(this);

        Cnegro.addActionListener(this);
        Cgriz.addActionListener(this);
        CRojo.addActionListener(this);
        Cnaranja.addActionListener(this);
        Camarillo.addActionListener(this);
        Cverde.addActionListener(this);
        Cblanco.addActionListener(this);
        Crosa.addActionListener(this);
        CazulC.addActionListener(this);
        CazulM.addActionListener(this);
        Cmorado.addActionListener(this);
        Ccarmin.addActionListener(this);
        todo.addActionListener(this);

        botonLapiz.addActionListener(this);
        btnCuadrado.addActionListener(this);
        btnLinea.addActionListener(this);
        btnCircu.addActionListener(this);
        btnTrian.addActionListener(this);
        btnpent.addActionListener(this);
        btnHexagono.addActionListener(this);
        btnOcta.addActionListener(this);
        btnCorazon.addActionListener(this); //NUEVO
        btnCurva.addActionListener(this); //NUEVO
        btnGoma.addActionListener(this); //NUEVO
        
        //VOTONES QUE PERMITIRAN GURADAR ABRIR CREAR UN NUEVO DOCUMENTO O CERRAR 
        
        btnGuardar.addActionListener(this);
        btnSalir.addActionListener(this);
        btnNuevo.addActionListener(this);
        btnAbrirArchivo.addActionListener(this);
        
        
    
        btndelgado.addActionListener(this);
        btnmediog.addActionListener(this);
        btgruesoss.addActionListener(this);
        BotonTexto.addActionListener(this);
        btncubeta.addActionListener(this);

        BotonS.addActionListener(this);
        BotonN.addActionListener(this);
        
        cuadriculado.addActionListener(this);
        jComboBox1.addActionListener(this);

    }

    public void crearMenu() {
        JMenuBar menu = new JMenuBar();
        archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir existente...");
        guardar = new JMenuItem("Guardar como...");
        salir = new JMenuItem("Salir");
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(salir);
        menu.add(archivo);

        dibujar = new JMenu(".");

        btn = new ButtonGroup();

        linea = new JRadioButtonMenuItem("Linea");
        rectangulo = new JRadioButtonMenuItem("Rectangulo");
        elipse = new JRadioButtonMenuItem("Elipse");

        btn.add(linea);
        btn.add(rectangulo);
        btn.add(elipse);

        btn.setSelected(linea.getModel(), true);

        relleno = new JCheckBoxMenuItem("Relleno");
        color = new JMenuItem("Mas colores");

        dibujar.add(color);
        menu.add(dibujar);

        
        ayuda = new JMenu("");
        acerca = new JMenuItem("");
        ayuda.add(acerca);
        menu.add(ayuda);
        //this.setJMenuBar(menu);
        
    }
    
    

    public static void main(String[] args) {
        Paint p = new Paint();
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mouseExited(MouseEvent evento) {

    }

    public void mouseEntered(MouseEvent evento) {

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btndelgado) {
            miPanel.grosor = 2;
        }
        if (e.getSource() == btnmediog) {
            miPanel.grosor = 7;
        }
        if (e.getSource() == btgruesoss) {
            miPanel.grosor = 14;
        }
        if (e.getSource() == Cnegro) {
            this.miPanel.setColorActual(new Color(1, 1, 1));
        }
        if (e.getSource() == Cgriz) {
            this.miPanel.setColorActual(new Color(127, 127, 127));
        }
        if (e.getSource() == CRojo) {
            this.miPanel.setColorActual(new Color(241, 23, 44));
        }
        if (e.getSource() == Cnaranja) {
            this.miPanel.setColorActual(new Color(254, 128, 33));
        }
        if (e.getSource() == Camarillo) {
            
            this.miPanel.setColorActual(new Color(248, 245, 1));
        }
        if (e.getSource() == Cverde) {
            this.miPanel.setColorActual(new Color(32, 177, 80));
        }
        if (e.getSource() == Cblanco) {
            this.miPanel.setColorActual(new Color(255, 255, 255));
        }
        if (e.getSource() == Crosa) {
            this.miPanel.setColorActual(new Color(255, 170, 205));
        }
        if (e.getSource() == CazulC) {
            this.miPanel.setColorActual(new Color(0, 163, 231));
        }
        if (e.getSource() == CazulM) {
            this.miPanel.setColorActual(new Color(68, 69, 206));
        }
        if (e.getSource() == Cmorado) {
            this.miPanel.setColorActual(new Color(162, 73, 164));
        }
        if (e.getSource() == Ccarmin) {
            this.miPanel.setColorActual(new Color(113, 12, 31));
        }
        if (e.getSource() == todo) {
            Color colorSeleccionado = JColorChooser.showDialog(this, "Seleccionar Color", null);
            if (colorSeleccionado != null) {
                this.miPanel.setColorActual(colorSeleccionado);
            }
        }
        if (e.getSource() == btnNuevo) {
            miPanel.resetAll();
        }
        if (e.getSource() == btnAbrirArchivo) {
            miPanel.abrir();
        }
        if (e.getSource() == btnGuardar) {
            miPanel.guardar();
        }
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
        if (e.getSource() == btnGoma) {
            miPanel.goma = true;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.circulo = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == btnLinea) {
            miPanel.goma = false;
            miPanel.linea = true;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.circulo = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == botonLapiz) {
            miPanel.lapiz = true;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.circulo = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
        }

        if (e.getSource() == btnCircu) {
            miPanel.circulo = true;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
        }

        if (e.getSource() == btnCuadrado) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = true;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == btnTrian) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = true;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == btnpent) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = true;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == btnOcta) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.rombo = true;
            miPanel.goma = false;
            miPanel.Texto = false;
        }
        if (e.getSource() == btnHexagono) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = true;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
            
            
        }
        if (e.getSource() == btnCorazon) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = true;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = false;
            
            
        }
        
        if (e.getSource() == btnCurva) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = true;
            miPanel.goma = false;
            miPanel.Texto = false;
            
            
        }
        
        if (e.getSource() == BotonTexto) {
            miPanel.circulo = false;
            miPanel.linea = false;
            miPanel.rectangulo = false;
            miPanel.lapiz = false;
            miPanel.triangulo = false;
            miPanel.pentagono = false;
            miPanel.rombo = false;
            miPanel.hexagno = false;
            miPanel.corazon = false;
            miPanel.curva = false;
            miPanel.goma = false;
            miPanel.Texto = true;

            miPanel.letraT = 20;
            //ACUTUALIZAR
            int valor = (int) jSpinner1.getValue();
            miPanel.letraT = valor;
            miPanel.textoC = campo.getText();

        }
        

        if (e.getSource() == BotonN) {
            if (miPanel.negritas) {
                miPanel.negritas = false;
                
                //ACUTUALIZAR
            int valor = (int) jSpinner1.getValue();
            miPanel.letraT = valor;
            miPanel.textoC = campo.getText();

            } else {
                miPanel.negritas = true;
            }
        }


        if (e.getSource() == BotonS) {
            if (miPanel.lineas) {
                miPanel.lineas = false;

            } else {
                miPanel.lineas = true;
            }

        }

        if (e.getSource() == rellenarr) {
            if (miPanel.relleno) {
                miPanel.relleno = false;
            } else {
                miPanel.relleno = true;
            }

        }

        if (e.getSource() == cuadriculado) {
            if (miPanel.cuad) {
                
                //miPanel.cuad = false;
            } else {
                //miPanel.cuad = true;
            }

        }
        if (e.getSource() == color) {
            Color color = JColorChooser.showDialog(this, "Eliga un color", this.miPanel.getColorActual());
            this.miPanel.setColorActual(color);
        }
        if (e.getSource() == acerca) {
            JOptionPane.showMessageDialog(null, "");

        }
        
        if(e.getSource()==btncubeta){
            Color colorfondo = JColorChooser.showDialog(this, "Eliga el color del lienzo", this.miPanel.getColorActual());
            miPanel.ccccc();
            
        }

    }
    
   
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}



class MiPanel extends JPanel {

    Point p1;
    Point p2;
    Shape figura;
    Random R = new Random();
    public Color coloractual = Color.BLACK;
    BufferedImage myImage;
    Graphics2D g2d;

    int letraT;

    boolean negritas = false;
    boolean lineas = false;
    
    

    boolean cuad = false;

    boolean rectangulo = false;
    boolean linea = false;
    boolean lapiz = true;
    boolean circulo = false;
    boolean triangulo = false;
    boolean pentagono = false;
    boolean hexagno = false;
    boolean rombo = false;
    boolean corazon = false;
    boolean curva = false;
    boolean goma = false;
    boolean cubo = false;

    String textoC = "";

    boolean Texto = false;
    int grosor = 2;
    boolean relleno = false;
    Graphics2D g2D;
    private Point curPoint = new Point();

    public MiPanel() {
        OyenteDeRaton miOyente = new OyenteDeRaton();
        OyenteDeMovimiento miOyente2 = new OyenteDeMovimiento();
        this.setBackground(Color.WHITE);

        addMouseListener(miOyente);
        addMouseMotionListener(miOyente2);

    }

    public void ccccc() {
        this.setBackground(coloractual);
        repaint();
    }

    public Color getColorActual() {
        return coloractual;
    }

    public void setColorActual(Color color) {
        coloractual = color;
    }

    public Graphics2D crearGraphics2D() {
        Graphics2D g2 = null;

        if (myImage == null || myImage.getWidth() != getSize().width || myImage.getHeight() != getSize().height) {
            myImage = (BufferedImage) createImage(getSize().width, getSize().height);

        }
        if (myImage != null) {
            g2 = myImage.createGraphics();
            g2.setColor(coloractual);
            g2.setBackground(getBackground());

        }
        g2.clearRect(0, 0, getSize().width, getSize().height);

        return g2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (myImage == null) {
            g2d = crearGraphics2D();
        }
        if (figura != null) {
            if (relleno) {
                g2d.setColor(coloractual);
                g2d.draw(figura);
                g2d.fill(figura);
                g2d.setBackground(Color.WHITE);
            } else {
                g2d.setColor(coloractual);
                g2d.draw(figura);
            }
            if (myImage != null && isShowing()) {
                g.drawImage(myImage, 0, 0, this);
            }

            figura = null;
        }
        g.drawImage(myImage, 0, 0, this);
    }

    public void resetAll() {
        myImage = null;

        repaint();
    }

    class OyenteDeRaton extends MouseAdapter {

        public void mousePressed(MouseEvent evento) {
            MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            p1 = evento.getPoint();
            curPoint = evento.getPoint();
            curPoint.setLocation(evento.getPoint());
            if (Texto) {

                curPoint = evento.getPoint();

                fuente(curPoint, evento);
                //fuente2(curPoint, evento);
                repaint();
            }
        }

        public void mouseReleased(MouseEvent evento) {
            MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            curPoint = evento.getPoint();
            if (lapiz) {
                curPoint = evento.getPoint();

                manoAls(curPoint, evento);
                repaint();
            } else if (rectangulo) {
                p2 = evento.getPoint();
                figura = crearRectangulo(p1, p2);
                repaint();
            } else if (linea) {
                p2 = evento.getPoint();
                figura = crearLinea(p1, p2);
                repaint();
            } else if (circulo) {
                p2 = evento.getPoint();
                figura = crearElipse(p1, p2);
                repaint();
            } else if (triangulo) {
                p2 = evento.getPoint();
                figura = crearTriangulo(p1, p2);
                repaint();
            } else if (pentagono) {
                p2 = evento.getPoint();
                figura = crearPentagono(p1, p2);
                repaint();
            } else if (rombo) {
                p2 = evento.getPoint();
                figura = crearRombo(p1, p2);
                repaint();
            } else if (hexagno) {
                p2 = evento.getPoint();
                figura = crearHexagono(p1, p2);
                repaint();
             
            } else if (corazon) {
                p2 = evento.getPoint();
                figura = crearCorazon(p1, p2);
                repaint();
            }else if (curva) {
                p2 = evento.getPoint();
                figura = crearCurva(p1, p2);
                repaint();
            }
            else if (goma) {

                curPoint = evento.getPoint();

                g2D.setColor(Color.WHITE);
                g2d.setColor(Color.WHITE);
                goma(curPoint, evento);
                repaint();
            }

            repaint();
        }
    }

    /**
 * Método que abre una imagen desde un archivo seleccionado por el usuario y la muestra en el programa.
 * Utiliza un objeto JFileChooser para mostrar el cuadro de diálogo de apertura y permite al usuario seleccionar un archivo de imagen.
 * La imagen seleccionada se lee y se asigna al objeto myImage, luego se actualiza el objeto g2d para reflejar los cambios y se repinta el panel.
 */
    public void abrir() {
        try {
            // Crea un objeto JFileChooser utilizando el método createJFileChooser()
            JFileChooser jfc = createJFileChooser();
            // Muestra el cuadro de diálogo de apertura y espera a que el usuario seleccione un archivo
            jfc.showOpenDialog(this);
            // Obtiene el archivo seleccionado por el usuario
            File file = jfc.getSelectedFile();
            // Si no se seleccionó ningún archivo, se retorna sin realizar ninguna acción
            if (file == null) {
                return;
            }
            // Lee la imagen desde el archivo y la asigna al objeto myImage
            myImage = javax.imageio.ImageIO.read(file);
            // Obtiene el ancho y el alto de la imagen
            int w = myImage.getWidth(null);
            int h = myImage.getHeight(null);
            // Verifica si el tipo de imagen es diferente de BufferedImage.TYPE_INT_RGB
            // Si es diferente, crea una nueva imagen BufferedImage del tipo BufferedImage.TYPE_INT_RGB
            // y dibuja la imagen original en la nueva imagen
            if (myImage.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(myImage, 0, 0, null);
            }
            // Actualiza el objeto g2d para reflejar los cambios de la imagen
            g2d = (Graphics2D) myImage.getGraphics();
            // Repinta el panel para mostrar la imagen cargada
            repaint();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    
    /**
 * Método que guarda la imagen actual en un archivo seleccionado por el usuario.
 * Utiliza un objeto JFileChooser para mostrar el cuadro de diálogo de guardado y
 * permite al usuario seleccionar la ubicación y el nombre del archivo.
 */
    public void guardar() {
        try {
            // Crea un objeto JFileChooser utilizando el método createJFileChooser()
            JFileChooser jfc = createJFileChooser();
            // Muestra el cuadro de diálogo de guardado y espera a que el usuario seleccione un archivo
            jfc.showSaveDialog(this);
            // Obtiene el archivo seleccionado por el usuario
            File file = jfc.getSelectedFile();
            // Si no se seleccionó ningún archivo, se retorna sin realizar ninguna acción
            if (file == null) {
                return;
            }
            // Obtiene el filtro de archivo seleccionado por el usuario
            javax.swing.filechooser.FileFilter ff = jfc.getFileFilter();
            // Obtiene el nombre del archivo y la extensión predeterminada
            String fileName = file.getName();
            String extension = "jpg";
            // Si el filtro de archivo es de tipo MyFileFilter, obtiene la extensión correspondiente
            if (ff instanceof MyFileFilter) {
                extension = ((MyFileFilter) ff).getExtension();
            }
            // Concatena la extensión al nombre del archivo
            fileName = fileName + "." + extension;
            // Crea un nuevo objeto File con la ruta del directorio y el nombre del archivo actualizados
            file = new File(file.getParent(), fileName);
            // Guarda la imagen actual en el archivo utilizando la extensión especificada
            javax.imageio.ImageIO.write(myImage, extension, file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
 * Método que crea un objeto JFileChooser configurado para mostrar un cuadro de diálogo
 * para seleccionar archivos con filtros basados en los formatos de archivo admitidos.
 * @return Objeto JFileChooser configurado.
 */
    public JFileChooser createJFileChooser() {
        JFileChooser jfc = new JFileChooser();
        // Desactiva el filtro de "Todos los archivos" por defecto
        jfc.setAcceptAllFileFilterUsed(false);
        // Obtiene los formatos de archivo admitidos
        String[] fileTypes = getFormats();
        // Agrega un filtro personalizado para cada formato admitido
        for (int i = 0; i < fileTypes.length; i++) {
            jfc.addChoosableFileFilter(new MyFileFilter(fileTypes[i], 
                    fileTypes[i] + " file"));
        }
        return jfc;
    }

    /**
 * Método que obtiene los formatos de archivo admitidos para guardar imágenes.
 * @return Array de strings que contiene los formatos de archivo admitidos.
 */
    public String[] getFormats() {
        String[] formats = javax.imageio.ImageIO.getWriterFormatNames();
        java.util.TreeSet<String> formatSet = new java.util.TreeSet<String>();
        
        // Convierte los formatos a minúsculas y los agrega al TreeSet para eliminar duplicados
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
        }
        // Convierte el TreeSet a un array y lo devuelve
        return formatSet.toArray(new String[0]);
    }

    /**
     * Clase que implementa la interfaz FileFilter para filtrar los archivos en un diálogo de selección de archivos.
     */
    class MyFileFilter extends javax.swing.filechooser.FileFilter {
        private String extension;
        private String description;
        
        /**
         * Constructor de la clase MyFileFilter.
         * @param extension Extensión de archivo permitida por este filtro.
         * @param description Descripción del tipo de archivo permitido por este filtro.
         */
        public MyFileFilter(String extension, String description) {
            this.extension = extension;
            this.description = description;
        }
        
        /**
     * Método que determina si un archivo es aceptado por este filtro.
     * @param f Archivo que se va a evaluar.
     * @return true si el archivo es aceptado, false en caso contrario.
     */
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith("." + extension) || f.isDirectory();
        }
        
        /**
     * Método que devuelve la descripción del filtro.
     * @return Descripción del filtro.
     */
        public String getDescription() {
            return description;
        }
        
        /**
     * Método que devuelve la extensión de archivo permitida por este filtro.
     * @return Extensión de archivo permitida.
     */
        public String getExtension() {
            return extension;
        }
    }

    /**
     * Clase que actúa como oyente de eventos de movimiento del ratón.
     */
    class OyenteDeMovimiento extends MouseMotionAdapter {
        /**
         * Método que se ejecuta cuando se presiona el botón del ratón.
         * @param e Evento de ratón que contiene información sobre la acción.
         */
        public void mousePressed(MouseEvent e) {//Click en el raton
            if (lapiz) {
                curPoint.setLocation(e.getPoint());//Contiene la posicion donde se presiona el Raton
            }
            if (Texto) {
                curPoint.setLocation(e.getPoint());//Contiene la posicion donde se presiona el Raton
                if (figura != null) {
                    g2D = myImage.createGraphics();
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.setStroke(new BasicStroke(grosor));
                }
                g2D = myImage.createGraphics();
                g2D.setColor(coloractual);
                g2D.setStroke(new BasicStroke(grosor));
                fuente(curPoint, e);

            }
        }

        /**
         * Método que se ejecuta cuando se arrastra el ratón.
         * @param evento Evento de ratón que contiene información sobre la acción.
         */
        public void mouseDragged(MouseEvent evento) {
            if (lapiz) {
                if (figura != null) {

                    g2D = myImage.createGraphics();
                    g2D.setColor(coloractual);
                    g2d.setColor(coloractual);
                    g2D.setXORMode(MiPanel.this.getBackground());

                    g2D.setStroke(new BasicStroke(grosor));
                    repaint();
                }

                g2D = myImage.createGraphics();
                g2D.setColor(coloractual);
                g2d.setColor(coloractual);

                g2D.setStroke(new BasicStroke(grosor));

                manoAls(curPoint, evento);
                repaint();
            } else if (rectangulo) {
                // Código para dibujar un rectángulo
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearRectangulo(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } 
            else if (linea) {
                // Código para dibujar una línea
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearLinea(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } else if (triangulo) {
                // Código para dibujar un triángulo
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearTriangulo(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } 
            else if (pentagono) {
                // Código para dibujar un pentágono
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearPentagono(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } 
            else if (rombo) {
                // Código para dibujar un rombo
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearRombo(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } else if (hexagno) {
                // Código para dibujar un hexágono
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearHexagono(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            } 
            else if (circulo) {
                // Código para dibujar un círculo
                if (figura != null) {
                    g2D = (Graphics2D) MiPanel.this.getGraphics();
                    g2d.setStroke(new BasicStroke(grosor));
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(coloractual);
                    g2D.draw(figura);
                }
                p2 = evento.getPoint();
                figura = crearElipse(p1, p2);
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2d.setStroke(new BasicStroke(grosor));
                g2D.setColor(coloractual);
                g2D.draw(figura);
            }
            if (goma) {
                // Código para usar la goma de borrar
                if (figura != null) {
                    g2D = myImage.createGraphics();
                    g2D.setXORMode(MiPanel.this.getBackground());
                    g2D.setColor(Color.WHITE);
                    g2d.setColor(Color.WHITE);
                    g2D.setStroke(new BasicStroke(20));
                    repaint();
                }
                g2D = myImage.createGraphics();
                g2D.setColor(Color.WHITE);
                g2d.setColor(Color.WHITE);
                g2D.setStroke(new BasicStroke(20));
                goma(curPoint, evento);
                repaint();
            }

        }
    }

    /**
 * Crea un objeto Shape que representa un rectángulo.
 *
 * @param p1 Punto que define la esquina superior izquierda del rectángulo.
 * @param p2 Punto que define la esquina inferior derecha del rectángulo.
 * @return Objeto Shape que representa el rectángulo.
 */
    public Shape crearRectangulo(Point p1, Point p2) {
        // Calcula las coordenadas del rectángulo
        double xInicio = Math.min(p1.getX(), p2.getX());
        double yInicio = Math.min(p1.getY(), p2.getY());
        double ancho = Math.abs(p2.getX() - p1.getX());
        double altura = Math.abs(p2.getY() - p1.getY());
        // Crea un objeto Shape de tipo Rectangle2D.Double utilizando las coordenadas calculadas
        Shape nuevaFigura = new Rectangle2D.Double(xInicio, yInicio, ancho, altura);
        // Devuelve el objeto Shape que representa el rectángulo
        return nuevaFigura;
    }

    /**
     * Crea un objeto Shape que representa una línea recta entre dos puntos.
     *
     * @param p1 Punto de inicio de la línea.
     * @param p2 Punto de fin de la línea.
     * @return Objeto Shape que representa la línea recta.
     */
    public Shape crearLinea(Point p1, Point p2) {
        // Crea un objeto Shape de tipo Line2D.Double utilizando las coordenadas de los puntos p1 y p2.
        Shape nuevaFigura = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        
        // Devuelve el objeto Shape que representa la línea recta.
        return nuevaFigura;
    }

    /**
     * Dibuja una línea en el contexto gráfico utilizando el color y el grosor de trazo especificados.
     *
     * @param curPoint Punto que representa la posición actual del cursor.
     * @param e        Evento de tipo MouseEvent que contiene información sobre el evento del mouse.
     */
    public void manoAls(Point curPoint, MouseEvent e) {
        g2D = myImage.createGraphics();
        // Establece el color de dibujo en el color actual.
        g2D.setColor(coloractual);
        // Establece el grosor del trazo utilizando el valor de la variable 'grosor'.
        g2D.setStroke(new BasicStroke(grosor));
        // Dibuja una línea desde las coordenadas de curPoint hasta las coordenadas de e.
        g2D.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());
        // Actualiza la posición del cursor al punto de e.
        curPoint.setLocation(e.getPoint());

    }

    /**
     * Dibuja una línea blanca con un grosor de trazo de 50 píxeles para simular una goma de borrar en el contexto gráfico.
     *
     * @param curPoint Punto que representa la posición actual del cursor.
     * @param e        Evento de tipo MouseEvent que contiene información sobre el evento del mouse.
     */
    public void goma(Point curPoint, MouseEvent e) {
        g2D = myImage.createGraphics();
        // Configura el grosor del trazo en 50 píxeles.
        g2D.setStroke(new BasicStroke(50));
        // Configura el color del trazo en blanco.
        g2D.setColor(Color.WHITE);
        g2d.setColor(Color.WHITE);
        // Dibuja una línea desde las coordenadas de curPoint hasta las coordenadas de e.
        g2D.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());
        // Actualiza la posición del cursor al punto de e.
        curPoint.setLocation(e.getPoint());
    }

    /**
     * Configura y aplica una fuente de texto al contexto gráfico para dibujar texto en un área determinada.
     *
     * @param curPoint Punto que representa la posición actual del cursor.
     * @param e        Evento de tipo MouseEvent que contiene información sobre el evento del mouse.
     */
    public void fuente(Point curPoint, MouseEvent e) {
        g2D = myImage.createGraphics();
        curPoint.setLocation(e.getPoint());
        g2D.setColor(coloractual);
        if (lineas && negritas) {
            // Crea una fuente en negrita utilizando el tipo de fuente seleccionado en el combo box,
            // el estilo de fuente Font.BOLD y el tamaño de letra letraT.
            Font fuente = new Font(Paint.jComboBox1.getSelectedItem().toString(), Font.BOLD, letraT);
            g2D.setFont(fuente);
            
            // Crea un objeto Map llamado attributes y agrega la propiedad de subrayado (TextAttribute.UNDERLINE_ON).
            Map attributes = g2D.getFont().getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

            // Deriva la fuente actual con los atributos definidos en attributes.
            g2D.setFont(g2D.getFont().deriveFont(attributes));

        } else if (lineas) {
            // Crea una fuente normal utilizando el tipo de fuente seleccionado en el combo box,
            // el estilo de fuente Font.PLAIN y el tamaño de letra letraT.
            Font fuente = new Font(Paint.jComboBox1.getSelectedItem().toString(), Font.PLAIN, letraT);
            g2D.setFont(fuente);
            
            // Crea un objeto Map llamado attributes y agrega la propiedad de subrayado (TextAttribute.UNDERLINE_ON).
            Map attributes = g2D.getFont().getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

            // Deriva la fuente actual con los atributos definidos en attributes.
            g2D.setFont(g2D.getFont().deriveFont(attributes));
            
        } else if (negritas) {
            // Crea una fuente en negrita utilizando el tipo de fuente seleccionado en el combo box,
             // el estilo de fuente Font.BOLD y el tamaño de letra letraT.
            Font fuente = new Font(Paint.jComboBox1.getSelectedItem().toString(), Font.BOLD, letraT);
            g2D.setFont(fuente);

        } else {
            // Crea una fuente normal utilizando el tipo de fuente seleccionado en el combo box,
            // el estilo de fuente Font.PLAIN y el tamaño de letra letraT.
            Font fuente = new Font(Paint.jComboBox1.getSelectedItem().toString(), Font.PLAIN, letraT);
            g2D.setFont(fuente);
        }
        // Dibuja el texto textoC en la posición especificada por las coordenadas curPoint.x y curPoint.y.
        g2D.drawString(textoC, curPoint.x, curPoint.y);

    }
    

    /**
     * Crea un objeto Shape que representa una elipse.
     *
     * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene la elipse.
     * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene la elipse.
     * @return Objeto Shape que representa la elipse.
     */
    public Shape crearElipse(Point p1, Point p2) {
        // Cálculo de las coordenadas del rectángulo que contiene la elipse
        double xInicio = Math.min(p1.getX(), p2.getX());
        double yInicio = Math.min(p1.getY(), p2.getY());
        double ancho = Math.abs(p2.getX() - p1.getX());
        double altura = Math.abs(p2.getY() - p1.getY());
        
        // Creación del objeto Ellipse2D.Double que representa la elipse
        Shape nuevaFigura = new Ellipse2D.Double(xInicio, yInicio, ancho, altura);
        // Devolución del objeto Shape
        return nuevaFigura;
    }

    /**
     * Crea un objeto Shape que representa un triángulo.
     *
     * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene el triángulo.
     * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene el triángulo.
     * @return Objeto Shape que representa el triángulo.
     */
    public Shape crearTriangulo(Point p1, Point p2) {
        // Definición de los arreglos para almacenar las coordenadas de los vértices del triángulo
        int[] xInicio = {(int) p1.getX(), (int) (p1.getX() + p2.getX()) / 2, 
            (int) p2.getX()};
        int[] yInicio = {(int) p2.getY(), (int) p1.getY(), (int) p2.getY()};
        // Creación del objeto Polygon que representa el triángulo
        Shape nuevaFigura = new Polygon(xInicio, yInicio, 3);
        // Devolución del objeto Shape
        return nuevaFigura;
    }

    
    /**
     * Crea un objeto Shape que representa un pentágono.
     *
     * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene el pentágono.
     * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene el pentágono.
     * @return Objeto Shape que representa el pentágono.
     */
    public Shape crearPentagono(Point p1, Point p2) {
        // Calcular las coordenadas de los vértices del pentagono
        int[] xInicio = {(int) (p1.getX() + p2.getX()) / 2, 
            (int) p1.getX(), (int) p1.getX() + 25, 
            (int) p2.getX() - 25, (int) p2.getX()};
        int[] yInicio = {(int) p1.getY(), (int) (p1.getY() + p2.getY()) / 2, 
            (int) p2.getY(), (int) p2.getY(), (int) (p1.getY() + p2.getY()) / 2};
        
        // Crear un objeto Polygon que representa el pentagono
        Shape nuevaFigura = new Polygon(xInicio, yInicio, 5);
        
        // Devolver el objeto Shape que representa el pentagono
        return nuevaFigura;
    }

     /**
     * Crea un objeto Shape que representa un hexágono.
     *
     * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene el hexágono.
     * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene el hexágono.
     * @return Objeto Shape que representa el hexágono.
     */
    public Shape crearHexagono(Point p1, Point p2) {
        // Calcular las coordenadas de los vértices del hexágono
        int[] xInicio = {(int) p1.getX() + 25, (int) p1.getX(), (int) p1.getX() + 25, 
            (int) p2.getX() - 25, (int) p2.getX(), (int) p2.getX() - 25};
        int[] yInicio = {(int) p1.getY(), (int) (p1.getY() + p2.getY()) / 2, 
            (int) p2.getY(), (int) p2.getY(), (int) (p1.getY() + p2.getY()) / 2, 
            (int) p1.getY()};
        
        // Crear un objeto Polygon que representa el hexágono
        Shape nuevaFigura = new Polygon(xInicio, yInicio, 6);
        
        // Devolver el objeto Shape que representa el hexágono
        return nuevaFigura;
    }

    
    /**
     * Crea un objeto Shape que representa un rombo.
     *
     * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene el rombo.
     * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene el rombo.
     * @return Objeto Shape que representa el rombo.
     */
    public Shape crearRombo(Point p1, Point p2) {
        // Calcular las coordenadas de los vértices del rombo
        int[] xInicio = {(int) (p1.getX() + p2.getX()) / 2, (int) p1.getX(),
            (int) (p1.getX() + p2.getX()) / 2, (int) p2.getX()};
        int[] yInicio = {(int) p1.getY(), (int) (p1.getY() + p2.getY()) / 2, 
            (int) p2.getY(), (int) (p1.getY() + p2.getY()) / 2};
        
        // Crear un objeto Polygon que representa el rombo
        Shape nuevaFigura = new Polygon(xInicio, yInicio, 4);
        
        // Devolver el objeto Shape que representa el rombo
        return nuevaFigura;
    }
    
    
/**
 * Crea un objeto Shape que representa un corazón.
 *
 * @param p1 Punto que define la esquina superior izquierda del rectángulo que contiene el corazón.
 * @param p2 Punto que define la esquina inferior derecha del rectángulo que contiene el corazón.
 * @return Objeto Shape que representa el corazón.
 */
    public static Shape crearCorazon(Point p1, Point p2) {
        
        // Calcula las coordenadas del rectángulo que contiene el corazón
        double xInicio = Math.min(p1.getX(), p2.getX());
        double yInicio = Math.min(p1.getY(), p2.getY());
        double ancho = Math.abs(p2.getX() - p1.getX());
        double altura = Math.abs(p2.getY() - p1.getY());

        // Crea un objeto GeneralPath para construir el trazado del corazón
        GeneralPath path = new GeneralPath();

        // Mueve el punto de inicio a la posición inicial de la parte superior del corazón
        path.moveTo(xInicio + ancho / 2, yInicio + altura / 4);
        path.curveTo(xInicio + ancho / 4, yInicio,
                xInicio, yInicio,
                xInicio, yInicio + altura / 4);
        
        // Agrega una curva a la parte inferior izquierda del corazón
        path.curveTo(xInicio, yInicio + altura / 2,
                xInicio + ancho / 4, yInicio + altura,
                xInicio + ancho / 2, yInicio + altura);
        
        // Agrega una curva a la parte inferior derecha del corazón
        path.curveTo(xInicio + ancho * 3 / 4, yInicio + altura,
                xInicio + ancho, yInicio + altura / 2,
                xInicio + ancho, yInicio + altura / 4);
        
        // Agrega una curva a la parte superior derecha del corazón y cierra el trazado
        path.curveTo(xInicio + ancho, yInicio,
                xInicio + ancho * 3 / 4, yInicio,
                xInicio + ancho / 2, yInicio + altura / 4);

        // Devuelve el objeto Shape que representa el corazón
        return path;
    }
    
    
    /**
     * Crea un objeto Shape que representa una curva cúbica.
     *
     * @param p1 Punto de inicio de la curva.
     * @param p2 Punto final de la curva.
     * @return Objeto Shape que representa la curva cúbica.
     */
    public static Shape crearCurva(Point p1, Point p2) {
        
        // Obtener las coordenadas del punto de inicio y punto final
        double xInicio = p1.getX();
        double yInicio = p1.getY();
        double xFin = p2.getX();
        double yFin = p2.getY();
        
        // Calcular las coordenadas de los puntos de control
        double control1X = xInicio + (xFin - xInicio) / 800;
        double control1Y = yInicio;
        double control2X = xInicio + 2 * (xFin - xInicio) / 8000;
        double control2Y = yFin;
        
        // Crear un objeto CubicCurve2D que representa la curva cúbica
        CubicCurve2D curva = new CubicCurve2D.Double(xInicio, yInicio, 
                control1X, control1Y, control2X, control2Y, xFin, yFin);
        
        // Devolver el objeto Shape que representa la curva cúbica
        return curva;
    }
}
