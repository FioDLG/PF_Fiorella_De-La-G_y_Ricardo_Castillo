<?php
// eliminar_reserva.php
if(isset($_GET['id'])) {
    $idReserva = $_GET['id'];

    $conn = new mysqli("localhost", "Ric0Josu18", "myrf0424", "reservas_villa_mon_coeur");

    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }

    $sql = "DELETE FROM reserva WHERE Nreserva = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $idReserva);
    $stmt->execute();

    echo "Reserva eliminada exitosamente.";
    $stmt->close();
    $conn->close();
}
?>
