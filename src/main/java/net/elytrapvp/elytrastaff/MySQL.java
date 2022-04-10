package net.elytrapvp.elytrastaff;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private ElytraStaff plugin;
    private Connection connection;
    private final String host;
    private final String database;
    private final String username;
    private final String password;
    private final int port;

    public MySQL(ElytraStaff plugin) {
        this.plugin = plugin;
        host = plugin.getSettingsManager().getConfig().getString("MySQL.host");
        database = plugin.getSettingsManager().getConfig().getString("MySQL.database");
        username = plugin.getSettingsManager().getConfig().getString("MySQL.username");
        password = plugin.getSettingsManager().getConfig().getString("MySQL.password");
        port = plugin.getSettingsManager().getConfig().getInt("MySQL.port");
    }

    /**
     * Close a connection.
     */
    public void closeConnection() {
        if(isConnected()) {
            try {
                connection.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the connection.
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Get if plugin is connected to the database.
     * @return Connected
     */
    private boolean isConnected() {
        return (connection != null);
    }

    /**
     * Open a MySQL Connection
     */
    public void openConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            synchronized(ElytraStaff.class) {
                if (connection != null && !connection.isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false&characterEncoding=utf8", username, password);
            }

            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()-> {
                try {
                    connection.isValid(0);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            },0,20*60*7);
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}