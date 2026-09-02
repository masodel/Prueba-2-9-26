module ni.edu.uam.prueba2926 {
    requires javafx.controls;
    requires javafx.fxml;

    exports ni.edu.uam.prueba2926;
    exports ni.edu.uam.prueba2926.controllers;
    exports ni.edu.uam.prueba2926.modelos;
    exports ni.edu.uam.prueba2926.utils;

    opens ni.edu.uam.prueba2926.controllers to javafx.fxml;
    opens ni.edu.uam.prueba2926.modelos to javafx.base;
}