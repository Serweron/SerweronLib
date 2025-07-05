package pl.serweron.serweronLib.api.databases;

import java.sql.Connection;

public interface SQLDatabase extends Database {
    Connection getConnection();
}
