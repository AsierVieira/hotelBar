<?php
try {
    $db = new PDO('sqlite:barHotel.db');

    $nombre_producto = $_GET['nombre_producto'];
    $stmt = $db->prepare("SELECT id_producto, nombre_es, precio FROM productos WHERE nombre_es LIKE :nombre_es");
    $stmt->execute([':nombre_es' => "%$nombre_producto%"]);

    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if (count($result) > 0) {
        //Aukeratzeko zein produktu nahi duzun ezabatu
        echo "<h2>Productos encontrados</h2>";
        echo "<form action='eliminarproductoBaseDatos.php' method='post'>";
        echo "<table border='1'><tr><th>Seleccionar</th><th>ID</th><th>Nombre</th><th>Precio</th></tr>";
        foreach ($result as $row) {
            echo "<tr>";
            echo "<td><input type='radio' name='producto_id' value='" . $row["id_producto"] . "' required></td>";
            echo "<td>" . $row["id_producto"] . "</td>";
            echo "<td>" . $row["nombre_es"] . "</td>";
            echo "<td>" . $row["precio"] . "</td>";
            echo "</tr>";
        }

        echo "</table>";
        echo "<button type='submit'>Eliminar Producto</button>";
        echo "</form>";
        
        echo "<form action='index.html' method='get'>";
        echo "<button type='submit'>Salir</button>";
        echo "</form>";

    } else {
        echo "No se encontraron productos.";
    }

    $db = null;
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>

