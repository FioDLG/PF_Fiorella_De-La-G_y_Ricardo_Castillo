<?php
// mostrar_reservas.php
$servername = "localhost";  
$username = "Ric0Josu18";  
$password = "myrf0424";     
$dbname = "reservas_villa_mon_coeur";  

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
  die("Conexión fallida: " . $conn->connect_error);
}

$sql = "SELECT * FROM reserva";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<table><tr><th>Reserva</th><th>Pasaporte</th><th>Cantidad Personas</th><th>Cantidad Días</th><th>Transporte</th></tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["Nreserva"]. "</td><td>" . $row["passport"]. "</td><td>" . $row["cantidadPersonas"]. "</td><td>" . $row["cantidadDias"]. "</td><td>" . ($row["idTransporte"] == 1 ? "Sí" : "No") . "</td></tr>";
    }
    echo "</table>";
} else {
    echo "No hay reservas.";
}

$conn->close();
?>
