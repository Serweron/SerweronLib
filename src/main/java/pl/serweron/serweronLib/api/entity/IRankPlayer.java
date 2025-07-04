package pl.serweron.serweronLib.api.entity;

import java.util.List;

public interface IRankPlayer extends IUser {
    // Player's own prefix
    String getPrefix();
    void setPrefix(String prefix, int weight);
    // Player's own suffix
    String getSuffix();
    void setSuffix(String suffix, int weight);

    // Player permissions
    List<String> getPermissions();
    void addPermission(String permission);
    void removePermission(String permission);
    boolean hasPermission(String permission);

    // Player ranks
    void setRank(String rank);
    void addRank(String rank);
    void removeRank(String rank);
    boolean hasRank(String rank);
    List<String> getAllRanks();
}