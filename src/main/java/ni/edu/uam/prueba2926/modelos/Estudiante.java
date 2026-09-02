package ni.edu.uam.prueba2926.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Estudiante {

    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private LocalDate fechaNacimiento;
    private String departamento;
    private String curso;
    private String modalidad;
    private List<String> horarios;
    private boolean normasAceptadas;

    public Estudiante(String nombres, String apellidos, String usuario, String contrasena,
                      LocalDate fechaNacimiento, String departamento, String curso,
                      String modalidad, List<String> horarios, boolean normasAceptadas) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.departamento = departamento;
        this.curso = curso;
        this.modalidad = modalidad;
        this.horarios = new ArrayList<>(horarios);
        this.normasAceptadas = normasAceptadas;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public String getHorario() {
        return String.join(", ", horarios);
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public List<String> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = new ArrayList<>(horarios);
    }

    public boolean isNormasAceptadas() {
        return normasAceptadas;
    }

    public void setNormasAceptadas(boolean normasAceptadas) {
        this.normasAceptadas = normasAceptadas;
    }
}
