package Vista;

import Modelo.ComboBoxEstado;
import Modelo.TablaPersona;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

/**
 *
 * @author Randy
 */
public class Ventana extends JFrame {

    //Panel Base
    JPanel panelBase;

    //Otros paneles
    JPanel panelCentral;
    JPanel panelInferiorPadre;
    JPanel panelCampos;
    JPanel panelComboBox;
    JPanel panelBotones;

    //Base
    BorderLayout layoutBase;

    //Otros layouts
    GridLayout layoutInferiorPadre;
    SpringLayout layoutCampos;
    GridLayout layoutBotones;

    //TABLA
    public static JTable tablaPersona;

    //lavels
    JLabel lblId;
    JLabel lblNombre;
    JLabel lblApellido;
    JLabel lblEdad;
    JLabel lblEstado;

    //textfields
    public JTextField txtId;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtEdad;

    //Botones
    public JButton btnGuardar;
    public JButton btnEliminar;
    public JButton btnActualizar;
    public JButton btnLimpiar;

    //COMBOBOX
    public static JComboBox cmbEstado;

    //Pruebas
    TablaPersona tp = new TablaPersona();
    ComboBoxEstado cbe = new ComboBoxEstado();

    public Ventana(String title) throws HeadlessException {
        super(title);

        iniciarComponentes();
        this.setLocationRelativeTo(null);
        this.setContentPane(panelBase);

    }

    private void iniciarComponentes() {
        //Iniciar la base
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 700);

        //Otros componentes
        //Paneles
        iniciarPanelBase();
        iniciarPanelCentral();
        iniciarPanelPadreInferior();
        iniciarPanelCampos();
        iniciarPanelComboBox();
        iniciarPanelBotones();

        //iniciar tabla
        iniciarTablaPersona();

        //INICIAR CAMPOS
        iniciarLavels();
        iniciarTextFields();

        //INICIAR COMBOBOX
        iniciarCmbEstado();

        //INICIAR BOTOTONES
        iniciarBotones();

        //Manejo layouut
        iniciarManejoLayoutCampos();

        //Otros
        tp.cagarTabla();
        cbe.cagarComboBox();

    }

    private void iniciarPanelBase() {
        layoutBase = new BorderLayout();
        panelBase = new JPanel(layoutBase);
        this.add(panelBase);
    }

    private void iniciarPanelCentral() {
        panelCentral = new JPanel();
        panelBase.add(panelCentral, BorderLayout.CENTER);
    }

    private void iniciarPanelPadreInferior() {
        layoutInferiorPadre = new GridLayout(1, 3, 0, 0);
        panelInferiorPadre = new JPanel(layoutInferiorPadre);
        panelBase.add(panelInferiorPadre, BorderLayout.SOUTH);
    }

    private void iniciarTablaPersona() {
        tablaPersona = new JTable();
        //

        panelCentral.add(tablaPersona);

        JScrollPane scrollPane = new JScrollPane(tablaPersona,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelCentral.add(scrollPane);
    }

    private void iniciarPanelCampos() {
        layoutCampos = new SpringLayout();
        panelCampos = new JPanel(layoutCampos);
        panelInferiorPadre.add(panelCampos);
    }

    private void iniciarPanelComboBox() {
        panelComboBox = new JPanel();
        panelInferiorPadre.add(panelComboBox);
    }

    private void iniciarPanelBotones() {
        layoutBotones = new GridLayout(4, 1, 10, 10);
        panelBotones = new JPanel(layoutBotones);
        panelInferiorPadre.add(panelBotones);
    }

    private void iniciarLavels() {
        lblId = new JLabel("Id:");
        lblNombre = new JLabel("Nombre:");
        lblApellido = new JLabel("Apellido:");
        lblEdad = new JLabel("Edad:");

        //Lavel estado
        lblEstado = new JLabel("Estado");

        panelCampos.add(lblId);
        panelCampos.add(lblNombre);
        panelCampos.add(lblApellido);
        panelCampos.add(lblEdad);

        //otro panel
        panelComboBox.add(lblEstado);
    }

    private void iniciarTextFields() {
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtEdad = new JTextField();

        panelCampos.add(txtId);
        panelCampos.add(txtNombre);
        panelCampos.add(txtApellido);
        panelCampos.add(txtEdad);
    }

    private void iniciarCmbEstado() {
        cmbEstado = new JComboBox();

        panelComboBox.add(cmbEstado);
    }

    private void iniciarBotones() {
        btnGuardar = new JButton("GUARDAR");
        btnActualizar = new JButton("ACTUALIZAR");
        btnEliminar = new JButton("ELIMINAR");
        btnLimpiar = new JButton("LIMPIAR");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

    }

    private void iniciarManejoLayoutCampos() {
        int margenSuperior = 10;
        layoutCampos.putConstraint(SpringLayout.WEST, lblId, 6, SpringLayout.WEST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, lblId, margenSuperior, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, txtId, margenSuperior, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.EAST, txtId, 0, SpringLayout.EAST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.WEST, txtId, 60, SpringLayout.WEST, lblId);

        layoutCampos.putConstraint(SpringLayout.WEST, lblNombre, 6, SpringLayout.WEST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, lblNombre, margenSuperior * 4, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, txtNombre, margenSuperior * 4, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.EAST, txtNombre, 0, SpringLayout.EAST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.WEST, txtNombre, 60, SpringLayout.WEST, lblNombre);

        layoutCampos.putConstraint(SpringLayout.WEST, lblApellido, 6, SpringLayout.WEST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, lblApellido, margenSuperior * 7, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, txtApellido, margenSuperior * 7, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.EAST, txtApellido, 0, SpringLayout.EAST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.WEST, txtApellido, 60, SpringLayout.WEST, lblApellido);

        layoutCampos.putConstraint(SpringLayout.WEST, lblEdad, 6, SpringLayout.WEST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, lblEdad, margenSuperior * 10, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.NORTH, txtEdad, margenSuperior * 10, SpringLayout.NORTH, panelCampos);
        layoutCampos.putConstraint(SpringLayout.EAST, txtEdad, 0, SpringLayout.EAST, panelCampos);
        layoutCampos.putConstraint(SpringLayout.WEST, txtEdad, 60, SpringLayout.WEST, lblEdad);

    }

}
