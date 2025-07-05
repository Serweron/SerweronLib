package pl.serweron.serweronLib.api.databases;

public interface Database {
    boolean isConnected();

    void connect();
    void disconnect();
}
}
