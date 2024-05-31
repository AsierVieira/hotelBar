
<?php
// Produktu berria datu basean sortu

try {
    //sqlitera konektatu
    $db = new PDO('sqlite:barHotel.db');
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $nombre_es = $_POST['nombre_es'];
        $nombre_en = $_POST['nombre_en'];
        $tipo = $_POST['tipo'];
        $precio = $_POST['precio'];
        $subtipo = $_POST['subtipo'];
        $descripcion_es = $_POST['descripcion_es'];
        $descripcion_en = $_POST['descripcion_en'];
        //inserta sortu:
        $stmt = $db->prepare("INSERT INTO productos (nombre_es, nombre_en, tipo, precio, subtipo, descripcion_es, descripcion_en) VALUES (:nombre_es, :nombre_en, :tipo, :precio, :subtipo, :descripcion_es, :descripcion_en)");
        $stmt->execute([':nombre_es' => $nombre_es, ':nombre_en' => $nombre_en, ':tipo' => $tipo, ':precio' => $precio, ':subtipo' => $subtipo, ':descripcion_es' => $descripcion_es, ':descripcion_en' => $descripcion_en ]);

        if ($stmt->rowCount() > 0) {
            //agertuko den mezua
            echo "<div class='container'>";
            $precio = $precio ."€";//prezio aldagaia sortzen dut € espaziobarik sartu ahal izateko
            echo "<p>El producto $nombre_es, de tipo $tipo, con precio $precio ha sido añadido a la base de datos.</p>";
            
            echo "<form action='index.html' method='get'>";
            echo "<button type='submit'>Salir</button>";
            echo "</form>";
            echo "</div>";
        } else {
            //ezin bada produktua sortu
            echo "<div class='container'>";
            echo "<p>No se pudo añadir el producto '$nombre_es' a la base de datos.</p>";

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

