package pl.serweron.serweronLib.api.databases;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLDatabase extends Database {
    Connection getConnection();

    @Override
    void connect(String jdbc, String username, String password) throws SQLException;
}
