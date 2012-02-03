/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author riadh
 */
public class DataBase {

    Connection connection;
    private String Table = "Person";
    private String DATABASE = "TP3";
    private String USERNAME = "prog3";
    private String PASS = "";
    String dbUrl = "jdbc:mysql://localhost/" + DATABASE;
    String dbClass = "com.mysql.jdbc.Driver";

    public DataBase() {
        try {
            Class.forName(dbClass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(dbUrl, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer insert(String name, Integer age, String address) {
        Integer executeUpdate = null;

        try {
            Statement stmt = connection.createStatement();
            executeUpdate = stmt.executeUpdate("INSERT INTO "
                    + Table
                    + "(Name, Age, Address) "
                    + "VALUES "
                    + "( "
                    + "'" + name + "'"
                    + ", "
                    + age
                    + ", "
                    + "'" + address + "'"
                    + ");");

            close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            return executeUpdate;
        }
    }

    public ResultSet selectByID(Integer ID) {
        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM "
                    + Table
                    + " WHERE "
                    + "ID = " + ID
                    + ";";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return resultSet;
        }
    }

    public ResultSet selectByName(String name) {
        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM "
                    + Table
                    + " WHERE "
                    + "Name LIKE "
                    + "'%" + name + "%'"
                    + ";";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return resultSet;
        }
    }

    public ResultSet selectByAge(Integer age) {
        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM "
                    + Table
                    + " WHERE "
                    + "Age = " + age
                    + ";");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return resultSet;
        }
    }

    public ResultSet selectByAddress(String address) {
        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM "
                    + Table
                    + " WHERE "
                    + "Address LIKE "
                    + "'%" + address + "%'"
                    + ";");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return resultSet;
        }
    }

       public ResultSet selectAll() {
        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM "
                    + Table
                    + ";");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return resultSet;
        }
    }

    public static TableModel toTableModel(ResultSet resultSet) {
        TableModel tableModel = null;

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
            Vector rows = new Vector();

            while (resultSet.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(resultSet.getObject(i));
                }

                rows.addElement(newRow);
            }

            tableModel = new DefaultTableModel(rows, columnNames);
        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return tableModel;
        }
    }
}
