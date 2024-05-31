<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_habitacion = $_POST['id_habitacion'];
    $fecha_entrada = $_POST['fecha_entrada'];
    $fecha_salida = $_POST['fecha_salida'];

    // Conectar a la base de datos SQLite
    try {
        $db = new PDO('sqlite:barHotel.db'); 
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Consulta para obtener los productos comprados con sus precios, cantidades y fechas
        $stmt = $db->prepare("SELECT pc.id_compra, p.nombre_es, p.precio, pc.cantidad, c.fecha FROM productos_comprados pc
                              INNER JOIN productos p ON pc.id_producto = p.id_producto
                              INNER JOIN compras c ON pc.id_compra = c.id_compra
                              WHERE c.id_habitacion = :id_habitacion AND c.fecha BETWEEN :fecha_entrada AND :fecha_salida");
        $stmt->bindParam(':id_habitacion', $id_habitacion, PDO::PARAM_INT);
        $stmt->bindParam(':fecha_entrada', $fecha_entrada);
        $stmt->bindParam(':fecha_salida', $fecha_salida);
        $stmt->execute();

        $resultados = $stmt->fetchAll(PDO::FETCH_ASSOC);

        if ($resultados) {
            echo "<h1>Productos Comprados</h1>";
            echo "<table id='productosTable' border='1'>
                    <tr>
                        <th>Producto</th>
                        <th>Precio Unitario</th>
                        <th>Cantidad</th>
                        <th>Fecha</th>
                        <th>Total</th>
                        <th>Eliminar</th>
                    </tr>";
            $total = 0;
            foreach ($resultados as $fila) {
                $total_producto = $fila['precio'] * $fila['cantidad'];
                echo "<tr>
                        <td>" . htmlspecialchars($fila['nombre_es']) . "</td>
                        <td>" . number_format($fila['precio'], 2) . "€</td>
                        <td><input type='number' value='" . htmlspecialchars($fila['cantidad']) . "' onchange='actualizarPrecio(this)'></td>
                        <td>" . htmlspecialchars($fila['fecha']) . "</td>
                        <td class='total_producto'>" . number_format($total_producto, 2) . "€</td>
                        <td><button onclick='eliminarProducto(this)'>X</button></td>
                      </tr>";
                $total += $total_producto;
            }
            echo "</table>";
            echo "<p id='total'>Total: " . number_format($total, 2) . "€</p>";
        } else {
            echo "No se encontraron resultados para la consulta.";
        }
        
        echo "<form action='index.html' method='get'>";
    echo "<button type='submit'>Salir</button>";
    echo "</form>";
    } catch (PDOException $e) {
        echo "Error en la conexión: " . $e->getMessage();
    }
} else {
    echo "Método no permitido.";
}
?>
<script>
function eliminarProducto(button) {
    var row = button.parentNode.parentNode;
    var table = row.parentNode;
    var totalCell = table.rows[0].cells.length - 1;
    var totalCellIndex = totalCell - 1;

    var totalProducto = parseFloat(row.cells[totalCellIndex].textContent.replace('€', '').replace(',', '.'));
    var total = parseFloat(document.getElementById('total').textContent.replace('Total: ', '').replace('€', '').replace(',', '.'));

    if (!isNaN(totalProducto) && !isNaN(total)) {
        total -= totalProducto;
    }

    row.parentNode.removeChild(row);
    document.getElementById('total').textContent = 'Total: ' + total.toFixed(2) + "€";
}

function actualizarPrecio(input) {
    var row = input.parentNode.parentNode;
    var precio = parseFloat(row.cells[1].textContent.replace('€', '').replace(',', '.'));
    var cantidad = parseInt(input.value);
    var totalCell = row.querySelector('.total_producto'); // Buscar la celda por su clase

    if (!isNaN(precio) && !isNaN(cantidad) && totalCell) {
        var total_producto = precio * cantidad;
        totalCell.textContent = number_format(total_producto, 2) + "€";
    }
}


function number_format(number, decimals, decPoint, thousandsSep) {
    decimals = decimals || 0;
    number = parseFloat(number);
    if (!decPoint || !thousandsSep) {
        decPoint = '.';
        thousandsSep = ',';
    }
    var roundedNumber = Math.round(Math.abs(number) * ('1e' + decimals)) + '';
    var numbersString = decimals ? roundedNumber.slice(0, decimals * -1) : roundedNumber;
    var decimalsString = decimals ? roundedNumber.slice(decimals * -1) : '';
    var formattedNumber = '';
    while (numbersString.length > 3) {
        formattedNumber += thousandsSep + numbersString.slice(-3);
        numbersString = numbersString.slice(0, -3);
    }
    return (number < 0 ? '-' : '') + numbersString + formattedNumber + (decimalsString ? (decPoint + decimalsString) : '');
}
</script>
