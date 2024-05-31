<?php
try {
    //konektatu sqlitera
    $db = new PDO('sqlite:barHotel.db');

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $nuevo_precio = $_POST['nuevo_precio'];

        //Selecta egin datuak hartzeko  
        $stmt_nombre = $db->prepare("SELECT id_producto, nombre_es FROM productos");
        $stmt = $db->prepare("UPDATE productos SET precio = :precio WHERE id_producto = :id_producto");
        
        
        $stmt_nombre->execute([':id_producto' => $id]);
        $nombre= $stmt_nombre->fetch(PDO::FETCH_ASSOC);
        
        

        
        foreach ($nuevo_precio as $id => $precio) {
            $stmt->execute([':precio' => $precio, ':id_producto' => $id]);
            echo "El precio del producto con ID $id se ha actualizado a $precio.<br>";
        }
    }

    echo "<form action='index.html' method='get'>";
    echo "<button type='submit'>Salir</button>";
    echo "</form>";

    $db = null;
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>