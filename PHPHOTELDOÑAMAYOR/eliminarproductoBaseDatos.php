<?php
try {
    //konektatu datubasera
    $db = new PDO('sqlite:barHotel.db');
    
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $producto_id = $_POST['producto_id'];

        // produktuaren izena hartu ezabatu baino lehen
        $stmt = $db->prepare("SELECT nombre_es FROM productos WHERE id_producto = :id_producto");
        $stmt->execute([':id_producto' => $producto_id]);
        $producto = $stmt->fetch(PDO::FETCH_ASSOC);
        $nombre_es = $producto['nombre_es'];

        // produktua ezabatu
        $stmt = $db->prepare("DELETE FROM productos WHERE id_producto = :id_producto");
        $stmt->execute([':id_producto' => $producto_id]);

        if ($stmt->rowCount() > 0) {
            //ezabatzen bada agertuko den mezua
            echo "<div class='container'>";
            echo "<p>El producto con ID $producto_id y nombre $nombre_es ha sido eliminado.</p>";
            echo "<form action='index.html' method='get'>";

            echo "<button type='submit'>Salir</button>";
            echo "</form>";
            echo "</div>";
        } else {
            //Ezin badu produktua ezabatu, agetuko den mezua
            echo "<div class='container'>";
            echo "<p>No se pudo eliminar el producto con ID $producto_id.</p>";
            echo "<form action='index.html' method='get'>";

            echo "<button type='submit'>Salir</button>";
            echo "</form>";
            echo "</div>";
        }
    }
} catch (PDOException $e) {
    echo "<div class='container'>";
    echo "<p>Error: " . $e->getMessage() . "</p>";

    echo "<form action='index.html' method='get'>";
    echo "<button type='submit'>Salir</button>";
    echo "</form>";
    echo "</div>";
}
?>

