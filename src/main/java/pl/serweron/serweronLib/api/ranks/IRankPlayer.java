package pl.serweron.serweronLib.api.ranks;

import pl.serweron.serweronLib.api.entity.IUser;

import java.util.List;

public interface IRankPlayer extends IUser {
    // Player's own prefix
    String getPrefix();
    String setPrefix(String prefix, int weight);
    // Player's own suffix
    String getSuffix();
    String setSuffix(String suffix, int weight);

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