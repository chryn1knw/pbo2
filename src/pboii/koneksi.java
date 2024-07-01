package pboii;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hensu
 */
public class koneksi {

    private static java.sql.Connection koneksi;

    public static java.sql.Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://172.26.154.129:3306/db_penjualan";
                String user = "hnd";
                String password = "sukandi";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                // Print database connection properties for debugging
                System.out.println("Connected to database: " + koneksi.getMetaData().getDatabaseProductName());
            } catch (SQLException t) {
                System.out.println("Error Membuat Koneksi");
            }
        }
        return koneksi;
    }
}
