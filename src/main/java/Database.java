import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.sql.*;

public class Database {
    int numOfDb;
    public Database(int numOfDb) {
        this.numOfDb = numOfDb;
    }
    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/Lab"+ this.numOfDb;
            String user = "root";
            String password = "12345678";

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void save(String operation, String tableName, double num1, double num2, double result) {
        String sql = "INSERT INTO " + tableName + "(operation, operand1, operand2, result) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, operation);
            pstmt.setDouble(2, num1);
            pstmt.setDouble(3, num2);
            pstmt.setDouble(4, result);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveAbs(String operation, String tableName, double num1, double result) {
        String sql = "INSERT INTO " + tableName + "(operation, operand1, result) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, operation);
            pstmt.setDouble(2, num1);
            pstmt.setDouble(3, result);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertStringData(String tableName, String string1, String string2, int length1, int length2, String concatenated, String isEqual) {
        String sql = "INSERT INTO " + tableName + " (string1, string2, length1, length2, concatenated, isEqual) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, string1);
            pstmt.setString(2, string2);
            pstmt.setInt(3, length1);
            pstmt.setInt(4, length2);
            pstmt.setString(5, concatenated);
            pstmt.setString(6, isEqual);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTables() {
        String sql = "SHOW TABLES";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // Execute the query and obtain the result set

            System.out.println("Таблицы в базе:");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName  + "(operation VARCHAR(255), operand1 DOUBLE, operand2 DOUBLE, result DOUBLE)";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании таблицы: " + e.getMessage());
        }
    }
    public void createStringTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY, string1 VARCHAR(255), string2 VARCHAR(255), length1 INT, length2 INT, concatenated VARCHAR(510), isEqual VARCHAR(255))";
        try (Connection conn = this.connect(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void exportToExcel(String tableNameForExport) {
        String sql = "SELECT * FROM " + tableNameForExport;

        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(tableNameForExport);

            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Creating header row
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(metaData.getColumnName(i));
            }

            // Writing data rows
            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);

                for (int i = 1; i <= columnCount; i++) {
                    row.createCell(i - 1).setCellValue(rs.getString(i));
                }
            }

            // Writing to disk
            try (FileOutputStream outputStream = new FileOutputStream(tableNameForExport+".xlsx")) {
                workbook.write(outputStream);
                workbook.close();
            }

            System.out.println("Данные экспортированы");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
