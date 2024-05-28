package donamayor.hotelbar.model;

import javafx.beans.property.ObjectProperty;

import java.sql.*;
import java.time.LocalDateTime;

public class CompraDAO {
    private static final String DATABASE_URL = "jdbc:sqlite:barHotel.db";

    public static void insertCompra(Compra compra) {
        String sql = "INSERT INTO compras (id_habitacion) VALUES (?)";
        String sql3= "Select max(id_compra) as id_compra from compras";
        String sql2 ="INSERT INTO productos_comprados(id_compra,id_producto,cantidad) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt3 = conn.prepareStatement(sql3);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            // insertar registro en la tabla de compras
            pstmt.setInt(1, compra.getIdHabitacion());
            pstmt.executeUpdate();

            // obtener el id de la compra insertada
            ResultSet rs = pstmt3.executeQuery();
            rs.next();
            int id_compra = rs.getInt("id_compra");

            // insertar registros en la tabla de productos comprados
            for(ProductoComprado pc:compra.getPcs()){
                pstmt2.setInt(1,id_compra);
                pstmt2.setInt(2,pc.getProducto().getId_producto());
                pstmt2.setInt(3,pc.getCantidad());
                pstmt2.executeUpdate();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

