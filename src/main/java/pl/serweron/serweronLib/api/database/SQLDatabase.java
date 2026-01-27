package pl.serweron.serweronLib.api.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Extension of the {@link Database} interface that provides SQL-specific functionality.
 * <p>
 * This interface is intended for databases that use JDBC and require access to a {@link Connection} object.
 */
public interface SQLDatabase extends Database {

    /**
     * Retrieves the current JDBC {@link Connection} instance.
     * <p>
     * This connection should be managed internally by the implementing class.
     * Implementations may return {@code null} if not connected.
     *
     * @return the active SQL connection, or {@code null} if not connected
     */
    Connection getConnection();

    /**
     * Establishes a JDBC connection to the database using the provided parameters.
     *
     * @param jdbc     the JDBC connection URL (e.g., "jdbc:mysql://localhost:3306/database")
     * @param username the username for the database
     * @param password the password for the database
     * @throws SQLException if a database access error occurs or the connection fails
     */
    @Override
    void connect(String jdbc, String username, String password) throws SQLException;
}
