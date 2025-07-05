package pl.serweron.serweronLib.api.databases;

public interface Database {
    boolean isConnected();

    void connect(String jdbc, String username, String password) throws Exception;
    void disconnect();
}
