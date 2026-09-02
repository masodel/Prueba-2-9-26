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
        colNombreCompleto.setCellValueFactory(dato ->
                new SimpleStringProperty(dato.getValue().getNombreCompleto()));
        colDepartamento.setCellValueFactory(dato ->
                new SimpleStringProperty(dato.getValue().getDepartamento()));
        colCurso.setCellValueFactory(dato ->
                new SimpleStringProperty(dato.getValue().getCurso()));
        colModalidad.setCellValueFactory(dato ->
                new SimpleStringProperty(dato.getValue().getModalidad()));
        colHorario.setCellValueFactory(dato ->
                new SimpleStringProperty(dato.getValue().getHorario()));
        colFechaNacimiento.setCellValueFactory(dato ->
                new SimpleObjectProperty<>(dato.getValue().getFechaNacimiento()));

        colFechaNacimiento.setCellFactory(columna -> new TableCell<>() {
            private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate fecha, boolean vacio) {
                super.updateItem(fecha, vacio);
                setText(vacio || fecha == null ? null : formato.format(fecha));
            }
        });

        tblEstudiantes.setPlaceholder(new Label("Todavia no hay estudiantes registrados"));
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (estudianteEnEdicion != null) {
            AlertUtil.informacion("Registro en edición",
                    "Hay un estudiante cargado. Use Actualizar o pulse Limpiar para crear uno nuevo.");
            return;
        }

        Estudiante nuevo = crearEstudianteDesdeFormulario(null);
        if (nuevo == null) {
            return;
        }

        estudiantes.add(nuevo);
        AlertUtil.informacion("Matrícula guardada",
                "El estudiante " + nuevo.getNombreCompleto() + " fue registrado correctamente.");
        limpiarFormulario();
        actualizarEstado();
    }

    @FXML
    private void actualizar(ActionEvent event) {
        if (estudianteEnEdicion == null) {
            AlertUtil.error("Sin seleccion",
                    "Seleccione un estudiante y haga doble clic, o use Editar en el menu contextual.");
            return;
        }

        Estudiante datosActualizados = crearEstudianteDesdeFormulario(estudianteEnEdicion);
        if (datosActualizados == null) {
            return;
        }

        copiarDatos(datosActualizados, estudianteEnEdicion);
        tblEstudiantes.refresh();
        AlertUtil.informacion("Matrícula actualizada", "Los cambios se guardaron correctamente.");
        limpiarFormulario();
        actualizarEstado();
    }

    @FXML
    private void limpiar(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Estudiante seleccionado = tblEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertUtil.error("Sin seleccion", "Seleccione el registro que desea eliminar.");
            return;
        }

        boolean confirmado = AlertUtil.confirmar("Confirmar eliminación",
                "¿Desea eliminar la matrícula de " + seleccionado.getNombreCompleto() + "?");
        if (confirmado) {
            estudiantes.remove(seleccionado);
            limpiarFormulario();
            actualizarEstado();
        }
    }

    @FXML
    private void dobleClicTabla(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            cargarSeleccionado();
        }
    }

    @FXML
    private void editarSeleccionado(ActionEvent event) {
        cargarSeleccionado();
    }

    @FXML
    private void manejarTeclado(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            limpiarFormulario();
            event.consume();
            return;
        }

        if (event.getCode() == KeyCode.ENTER && !(event.getTarget() instanceof Button)) {
            if (estudianteEnEdicion == null) {
                guardar(new ActionEvent(event.getSource(), event.getTarget()));
            } else {
                actualizar(new ActionEvent(event.getSource(), event.getTarget()));
            }
            event.consume();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        if (AlertUtil.confirmar("Salir", "¿Desea cerrar el sistema de matrícula?")) {
            Stage stage = (Stage) lblEstado.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void acercaDe(ActionEvent event) {
        AlertUtil.informacion("Acerca del sistema",
                "Sistema de matrícula - Proyecto 1\n" +
                        "Centro Nicaragüense de Formación Tecnológica\n" +
                        "Desarrollado con JavaFX, FXML y Scene Builder.");
    }

    private Estudiante crearEstudianteDesdeFormulario(Estudiante registroActual) {
        List<String> errores = validarFormulario(registroActual);
        if (!errores.isEmpty()) {
            AlertUtil.error("Revise los datos", String.join("\n", errores));
            return null;
        }

        return new Estudiante(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                txtUsuario.getText().trim(),
                txtContrasena.getText(),
                dpFechaNacimiento.getValue(),
                cbDepartamento.getValue(),
                lvCurso.getSelectionModel().getSelectedItem(),
                obtenerModalidad(),
                obtenerHorarios(),
                chkNormas.isSelected()
        );
    }

    private List<String> validarFormulario(Estudiante registroActual) {
        List<String> errores = new ArrayList<>();

        if (ValidacionUtil.estaVacio(txtNombres.getText())) {
            errores.add("- Ingrese los nombres.");
        }
        if (ValidacionUtil.estaVacio(txtApellidos.getText())) {
            errores.add("- Ingrese los apellidos.");
        }
        if (ValidacionUtil.estaVacio(txtUsuario.getText())) {
            errores.add("- Ingrese el usuario.");
        } else if (ValidacionUtil.longitudMenorQue(txtUsuario.getText(), 5)) {
            errores.add("- El usuario debe tener al menos 5 caracteres.");
        } else if (usuarioRepetido(txtUsuario.getText().trim(), registroActual)) {
            errores.add("- Ese nombre de usuario ya esta registrado.");
        }
        if (ValidacionUtil.estaVacio(txtContrasena.getText())) {
            errores.add("- Ingrese la contraseña.");
        } else if (ValidacionUtil.longitudMenorQue(txtContrasena.getText(), 8)) {
            errores.add("- La contraseña debe tener al menos 8 caracteres.");
        }
        if (dpFechaNacimiento.getValue() == null) {
            errores.add("- Seleccione la fecha de nacimiento.");
        } else if (ValidacionUtil.fechaFutura(dpFechaNacimiento.getValue())) {
            errores.add("- La fecha de nacimiento no puede ser futura.");
        }
        if (cbDepartamento.getValue() == null) {
            errores.add("- Seleccione el departamento.");
        }
        if (lvCurso.getSelectionModel().getSelectedItem() == null) {
            errores.add("- Seleccione un curso.");
        }
        if (grupoModalidad.getSelectedToggle() == null) {
            errores.add("- Seleccione una modalidad.");
        }
        if (obtenerHorarios().isEmpty()) {
            errores.add("- Seleccione al menos un horario.");
        }
        if (!chkNormas.isSelected()) {
            errores.add("- Debe aceptar las normas del centro.");
        }

        return errores;
    }

    private boolean usuarioRepetido(String usuario, Estudiante registroActual) {
        return estudiantes.stream()
                .anyMatch(e -> e != registroActual && e.getUsuario().equalsIgnoreCase(usuario));
    }

    private String obtenerModalidad() {
        if (rbPresencial.isSelected()) {
            return "Presencial";
        }
        if (rbVirtual.isSelected()) {
            return "Virtual";
        }
        return "";
    }

    private List<String> obtenerHorarios() {
        List<String> horarios = new ArrayList<>();
        if (chkMatutino.isSelected()) horarios.add("Matutino");
        if (chkVespertino.isSelected()) horarios.add("Vespertino");
        if (chkSabatino.isSelected()) horarios.add("Sabatino");
        return horarios;
    }

    private void cargarSeleccionado() {
        Estudiante seleccionado = tblEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            AlertUtil.error("Sin seleccion", "Seleccione un estudiante de la tabla.");
            return;
        }

        estudianteEnEdicion = seleccionado;
        txtNombres.setText(seleccionado.getNombres());
        txtApellidos.setText(seleccionado.getApellidos());
        txtUsuario.setText(seleccionado.getUsuario());
        txtContrasena.setText(seleccionado.getContrasena());
        dpFechaNacimiento.setValue(seleccionado.getFechaNacimiento());
        cbDepartamento.setValue(seleccionado.getDepartamento());
        lvCurso.getSelectionModel().select(seleccionado.getCurso());

        if ("Presencial".equals(seleccionado.getModalidad())) {
            rbPresencial.setSelected(true);
        } else {
            rbVirtual.setSelected(true);
        }

        List<String> horarios = seleccionado.getHorarios();
        chkMatutino.setSelected(horarios.contains("Matutino"));
        chkVespertino.setSelected(horarios.contains("Vespertino"));
        chkSabatino.setSelected(horarios.contains("Sabatino"));
        chkNormas.setSelected(seleccionado.isNormasAceptadas());

        cambiarModoEdicion(true);
        txtNombres.requestFocus();
    }

    private void copiarDatos(Estudiante origen, Estudiante destino) {
        destino.setNombres(origen.getNombres());
        destino.setApellidos(origen.getApellidos());
        destino.setUsuario(origen.getUsuario());
        destino.setContrasena(origen.getContrasena());
        destino.setFechaNacimiento(origen.getFechaNacimiento());
        destino.setDepartamento(origen.getDepartamento());
        destino.setCurso(origen.getCurso());
        destino.setModalidad(origen.getModalidad());
        destino.setHorarios(origen.getHorarios());
        destino.setNormasAceptadas(origen.isNormasAceptadas());
    }

    private void limpiarFormulario() {
        txtNombres.clear();
        txtApellidos.clear();
        txtUsuario.clear();
        txtContrasena.clear();
        dpFechaNacimiento.setValue(null);
        cbDepartamento.setValue(null);
        lvCurso.getSelectionModel().clearSelection();
        grupoModalidad.selectToggle(null);
        chkMatutino.setSelected(false);
        chkVespertino.setSelected(false);
        chkSabatino.setSelected(false);
        chkNormas.setSelected(false);
        tblEstudiantes.getSelectionModel().clearSelection();
        estudianteEnEdicion = null;
        cambiarModoEdicion(false);
        txtNombres.requestFocus();
    }

    private void cambiarModoEdicion(boolean editando) {
        btnActualizar.setDisable(!editando);
        btnEliminar.setDisable(estudiantes.isEmpty());
        lblEstado.setText(editando
                ? "Editando: " + estudianteEnEdicion.getNombreCompleto()
                : estudiantes.size() + " estudiante(s) registrado(s)");
    }

    private void actualizarEstado() {
        lblEstado.setText(estudiantes.size() + " estudiante(s) registrado(s)");
        btnEliminar.setDisable(estudiantes.isEmpty());
    }
}
