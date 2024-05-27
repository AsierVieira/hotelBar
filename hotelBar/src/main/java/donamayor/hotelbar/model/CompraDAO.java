package donamayor.hotelbar.model;

import javafx.beans.property.ObjectProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CompraDAO {
    private static final String DATABASE_URL = "jdbc:sqlite:barHotel.db";

    public static void insertCompra(Compra compra) {
        String sql = "INSERT INTO compras (id_habitacion, id_compra) VALUES (?, ?)";
        String sql2 ="INSERT INTO productos_comprados(id_compra,id_producto,cantidad) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            pstmt.setInt(1, compra.getIdHabitacion());
            pstmt.setInt(2, compra.getIdCompra());
            pstmt.executeUpdate();
            for(ProductoComprado pc:compra.getPcs()){
                pstmt2.setInt(1,compra.getIdCompra());
                pstmt2.setInt(2,pc.getProducto().getId_producto());
                pstmt2.setInt(3,pc.getCantidad());
                pstmt2.executeUpdate();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

