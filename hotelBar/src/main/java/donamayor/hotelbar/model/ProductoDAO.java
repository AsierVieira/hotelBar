package donamayor.hotelbar.model;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String DATABASE_URL = "jdbc:sqlite:barHotel.db";

    //Método para obtener la conexión a la base de datos

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    //Método para obtener todos los productos

    public static List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_es(rs.getString("nombre_es"));
                producto.setNombre_en(rs.getString("nombre_en"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setTipo(rs.getString("tipo"));
                producto.setSubtipo(rs.getString("subtipo"));
                producto.setDescripcion_es(rs.getString("descripcion_es"));
                producto.setDescripcion_en(rs.getString("descripcion_en"));


                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
    //aquí no tendría que obtener listas nuevas sino que tendría que filtrar el tipo de la lista que ya he obtenido


    public static List<Producto> getProductosPorTipo(String tipo) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE tipo = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_es(rs.getString("nombre_es"));
                producto.setNombre_en(rs.getString("nombre_en"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setTipo(rs.getString("tipo"));
                producto.setSubtipo(rs.getString("subtipo"));
                producto.setDescripcion_es(rs.getString("descripcion_es"));
                producto.setDescripcion_en(rs.getString("descripcion_en"));

                /*Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    byte[] imgBytes = blob.getBytes(1, (int) blob.length());

                    try (ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes)) {
                        BufferedImage img = ImageIO.read(bis);
                        producto.setFoto(img);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                */


                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

}


