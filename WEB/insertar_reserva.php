<?php
// insertar_reserva.php

$servername = "localhost";  // Servidor de la base de datos
$username = "Ric0Josu18";  // Tu nombre de usuario
$password = "myrf0424";     // Tu contraseña de MySQL
$dbname = "reservas_villa_mon_coeur";  // Nombre de la base de datos

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
  die("Conexión fallida: " . $conn->connect_error);
}

// Recoger datos del formulario
$passport = $_POST['passport'];
$cantidadPersonas = $_POST['cantidadPersonas'];
$cantidadDias = $_POST['cantidadDias'];
$requiereTransporte = $_POST['requiereTransporte'];

$aerolinea = $requiereTransporte == 1 ? $_POST['aerolinea'] : NULL;
$nvuelo = $requiereTransporte == 1 ? $_POST['nvuelo'] : NULL;
$horaLlegada = $requiereTransporte == 1 ? $_POST['horaLlegada'] : NULL;
$lugarLlegada = $requiereTransporte == 1 ? $_POST['lugarLlegada'] : NULL;
$horaSalida = $requiereTransporte == 1 ? $_POST['horaSalida'] : NULL;
$lugarSalida = $requiereTransporte == 1 ? $_POST['lugarSalida'] : NULL;

// Iniciar transacción
$conn->begin_transaction();

try {
    // Insertar reserva
    $sql = "INSERT INTO reserva (passport, cantidadPersonas, cantidadDias, idTransporte) VALUES (?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    $idTransporte = $requiereTransporte == 1 ? 1 : 0;  // Si requiere transporte, idTransporte será 1, sino 0
    $stmt->bind_param("siii", $passport, $cantidadPersonas, $cantidadDias, $idTransporte);
    $stmt->execute();
    $stmt->close();

    // Si requiere transporte, insertar información de vuelo
    if ($requiereTransporte == 1) {
        $sqlVuelo = "INSERT INTO infovuelo (Nreserva, Aerolinea, Nvuelo, HoraLlegada, LugarLlegada, HoraSalida, LugarSalida) VALUES (?, ?, ?, ?, ?, ?, ?)";
        $stmtVuelo = $conn->prepare($sqlVuelo);
        $stmtVuelo->bind_param("issssss", $conn->insert_id, $aerolinea, $nvuelo, $horaLlegada, $lugarLlegada, $horaSalida, $lugarSalida);
        $stmtVuelo->execute();
        $stmtVuelo->close();
    }

    // Confirmar la transacción
    $conn->commit();
    echo "Reserva realizada exitosamente!";
} catch (Exception $e) {
    // Si hay error, revertir cambios
    $conn->rollback();
    echo "Error: " . $e->getMessage();
}

// Cerrar la conexión
$conn->close();
?>
