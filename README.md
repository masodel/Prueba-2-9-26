# Sistema de matrícula

## Información general

- **Asignatura:** Programación de Aplicaciones de Escritorio
- **Docente:** MSc. José Alejandro Durán García
- **Proyecto:** Proyecto 1 - Sistema de matrícula
- **Integrantes:**
  - Rolando Enrique Mayorga Mena
  - Mauro Engelbert Delgado Saenz

## Descripción del proyecto

En este proyecto desarrollamos una aplicación de escritorio para el Centro Nicaragüense de Formación Tecnológica. La aplicación permite registrar la información de los estudiantes que desean matricularse en los cursos disponibles del centro.

El sistema fue realizado con JavaFX y la interfaz se diseñó mediante FXML para poder trabajarla en Scene Builder. También utilizamos CSS para darle una apariencia más clara, ordenada y agradable.

Los registros se guardan temporalmente en una `ObservableList`, por lo que no se utiliza una base de datos. Al cerrar la aplicación, la información registrada se elimina.

## Objetivo

Crear una interfaz gráfica funcional que permita registrar, mostrar, seleccionar, actualizar y eliminar estudiantes, aplicando los controles, eventos, menús y validaciones estudiados en clase.

## Funciones principales

La aplicación permite realizar las siguientes acciones:

- Registrar nuevos estudiantes.
- Mostrar los estudiantes en un `TableView`.
- Cargar la información de un estudiante haciendo doble clic sobre una fila.
- Actualizar los datos de un registro seleccionado.
- Eliminar registros con una confirmación previa.
- Limpiar todos los campos del formulario.
- Verificar que los datos obligatorios estén completos.
- Utilizar opciones desde el menú principal, la barra de herramientas y el menú contextual.

## Datos registrados

Para cada estudiante se guarda la siguiente información:

- Nombres.
- Apellidos.
- Usuario.
- Contraseña.
- Fecha de nacimiento.
- Departamento.
- Curso seleccionado.
- Modalidad presencial o virtual.
- Uno o varios horarios.
- Aceptación de las normas del centro.

## Validaciones

Antes de guardar o actualizar un estudiante, el sistema verifica que:

- Ningún campo obligatorio esté vacío.
- El usuario tenga al menos 5 caracteres.
- La contraseña tenga al menos 8 caracteres.
- La fecha de nacimiento no sea posterior a la fecha actual.
- Se seleccione un departamento.
- Se seleccione un curso.
- Se seleccione una modalidad.
- Se seleccione al menos un horario.
- Se acepten las normas del centro.
- El nombre de usuario no esté repetido.

Si alguna condición no se cumple, se muestra una alerta indicando lo que debe corregirse.

## Controles utilizados

En la interfaz se utilizaron los controles solicitados para la actividad:

- `Label`
- `Button`
- `TextField`
- `PasswordField`
- `DatePicker`
- `ComboBox`
- `ListView`
- `RadioButton`
- `CheckBox`
- `ImageView`
- `TableView`
- `MenuBar`
- `ToolBar`
- `ContextMenu`

## Eventos implementados

### ActionEvent

Se utiliza en los botones Guardar, Actualizar, Limpiar y Eliminar. También se utiliza en las opciones de los menús.

### MouseEvent

Al hacer doble clic sobre una fila del `TableView`, los datos del estudiante se cargan en el formulario para poder modificarlos.

### KeyEvent

- **Enter:** guarda un registro nuevo o actualiza el que se encuentra en edición.
- **Escape:** limpia el formulario.

## Menús

La aplicación cuenta con los siguientes elementos:

- **MenuBar:** contiene las opciones Nuevo, Salir y Acerca de.
- **ToolBar:** contiene accesos rápidos para Guardar, Limpiar y Eliminar.
- **ContextMenu:** aparece al hacer clic derecho sobre la tabla y permite editar o eliminar un registro.

## Organización del proyecto

```text
Proyecto1_Matricula/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   └── ni/edu/uam/matricula/
│       │       ├── MainApplication.java
│       │       ├── controllers/
│       │       │   └── MatriculaController.java
│       │       ├── models/
│       │       │   └── Estudiante.java
│       │       └── utils/
│       │           ├── AlertUtil.java
│       │           └── ValidacionUtil.java
│       └── resources/
│           └── ni/edu/uam/matricula/
│               ├── matricula-view.fxml
│               ├── styles.css
│                 logo-inatec.png
```

## Tecnologías y herramientas

- Java 21.
- JavaFX 21.0.6.
- IntelliJ IDEA.
- Scene Builder.
- Maven.
- FXML.
- CSS.
- Git y GitHub.

## Cómo ejecutar el proyecto

1. Descargar o clonar el repositorio.
2. Abrir la carpeta del proyecto en IntelliJ IDEA.
3. Verificar que el proyecto utilice Java 21.
4. Esperar que Maven descargue las dependencias de JavaFX.
5. Ejecutar la clase `MainApplication.java`.

También se puede ejecutar desde una terminal ubicada en la carpeta del proyecto:

```bash
mvn clean javafx:run
```

## Uso de la aplicación

1. Completar los datos del estudiante.
2. Seleccionar el departamento y el curso.
3. Elegir la modalidad y al menos un horario.
4. Aceptar las normas del centro.
5. Presionar el botón **Guardar matrícula**.
6. Para modificar un registro, hacer doble clic sobre su fila y presionar **Actualizar**.
7. Para eliminarlo, seleccionarlo y presionar **Eliminar**.

## Diseño de la interfaz

El archivo `matricula-view.fxml` contiene la estructura visual de la aplicación y puede abrirse en Scene Builder.

El archivo `styles.css` contiene los colores, tamaños, bordes y estilos utilizados en los botones, campos, menús y tabla. La imagen institucional se encuentra en la carpeta `images`.

## Conclusión

Con este proyecto pusimos en práctica la creación de interfaces gráficas con JavaFX, la conexión entre FXML y el controlador, el manejo de eventos y el uso de colecciones para mostrar información en una tabla. También reforzamos la importancia de validar los datos antes de procesarlos y de mantener el proyecto organizado por paquetes.
