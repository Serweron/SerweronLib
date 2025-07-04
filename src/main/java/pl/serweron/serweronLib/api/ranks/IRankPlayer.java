package pl.serweron.serweronLib.api.ranks;

import pl.serweron.serweronLib.api.entity.IUser;

import java.util.List;

public interface IRankPlayer extends IUser {
    String getPrefix();
    String setPrefix(String prefix, int weight);
    String getSuffix();
    String setSuffix(String suffix, int weight);

    List<String> getPermissions();
    void addPermission(String permission);
    void removePermission(String permission);
    boolean hasPermission(String permission);

    void setRank(String rank);
    void addRank(String rank);
    void removeRank(String rank);
    List<String> getAllRanks();
}