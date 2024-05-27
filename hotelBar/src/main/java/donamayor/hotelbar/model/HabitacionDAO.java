package donamayor.hotelbar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

    private static final String DATABASE_URL = "jdbc:sqlite:barHotel.db";

    // Método para obtener la conexión a la base de datos
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Método para obtener todas las habitaciones
    public List<String> getAllHabitaciones() {
        List<String> habitaciones = new ArrayList<>();
        String sql = "SELECT id_habitacion FROM habitacion";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                habitaciones.add(rs.getString("id_habitacion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitaciones;
    }
}

