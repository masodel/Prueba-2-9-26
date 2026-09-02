package ni.edu.uam.prueba2926.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ni.edu.uam.prueba2926.modelos.Estudiante;
import ni.edu.uam.prueba2926.utils.AlertUtil;
import ni.edu.uam.prueba2926.utils.ValidacionUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MatriculaController {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cbDepartamento;
    @FXML private ListView<String> lvCurso;
    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVirtual;
    @FXML private ToggleGroup grupoModalidad;
    @FXML private CheckBox chkMatutino;
    @FXML private CheckBox chkVespertino;
    @FXML private CheckBox chkSabatino;
    @FXML private CheckBox chkNormas;

    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombreCompleto;
    @FXML private TableColumn<Estudiante, String> colDepartamento;
    @FXML private TableColumn<Estudiante, String> colCurso;
    @FXML private TableColumn<Estudiante, String> colModalidad;
    @FXML private TableColumn<Estudiante, String> colHorario;
    @FXML private TableColumn<Estudiante, LocalDate> colFechaNacimiento;
    @FXML private Label lblEstado;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;

    private final ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    private Estudiante estudianteEnEdicion;

    @FXML
    public void initialize() {
        cbDepartamento.setItems(FXCollections.observableArrayList(
                "Boaco", "Carazo", "Chinandega", "Chontales", "Costa Caribe Norte",
                "Costa Caribe Sur", "Estelí", "Granada", "Jinotega", "León",
                "Madriz", "Managua", "Masaya", "Matagalpa", "Nueva Segovia",
                "Río San Juan", "Rivas"
        ));

        lvCurso.setItems(FXCollections.observableArrayList(
                "Programación", "Excel", "Redes", "Diseño gráfico"
        ));

        configurarTabla();
        tblEstudiantes.setItems(estudiantes);
        actualizarEstado();
        cambiarModoEdicion(false);
    }

    private void configurarTabla() {
    }

    @FXML
    private void guardar(ActionEvent event) {
    }

    @FXML
    private void actualizar(ActionEvent event) {
    }

    @FXML
    private void limpiar(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void editarSeleccionado(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) {
    }


    private List<String> obtenerHorarios() {
        return null;
    }

    private void cargarSeleccionado() {
    }

    private void copiarDatos(Estudiante origen, Estudiante destino) {
    }

    private void limpiarFormulario() {
    }

    private void cambiarModoEdicion(boolean editando) {
    }

    private void actualizarEstado() {
    }
}
