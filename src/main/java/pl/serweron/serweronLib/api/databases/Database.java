package pl.serweron.serweronLib.api.databases;

/**
 * Interface for handling basic database connection operations.
 * Implementations of this interface should manage the connection lifecycle to a database
 * using JDBC (e.g., MySQL, PostgreSQL, etc.).
 */
public interface Database {

    /**
     * Checks if the database is currently connected.
     *
     * @return {@code true} if the connection is active, {@code false} otherwise
     */
    boolean isConnected();

    /**
     * Establishes a connection to the database using the provided credentials and JDBC URL.
     *
     * @param jdbc     the JDBC connection string (e.g., "jdbc:mysql://localhost:3306/dbname")
     * @param username the database username
     * @param password the database password
     * @throws Exception if the connection cannot be established
     */
    void connect(String jdbc, String username, String password) throws Exception;

    /**
     * Closes the database connection, if one is open.
     */
    void disconnect();
}
