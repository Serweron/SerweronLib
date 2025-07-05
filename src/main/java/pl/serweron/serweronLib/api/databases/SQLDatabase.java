package pl.serweron.serweronLib.api.databases;

import java.sql.Connection;

public interface SQLDatabase {
    Connection getConnection();
    boolean isConnected();

    void connect();
    void disconnect();
}
