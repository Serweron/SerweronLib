package pl.serweron.serweronLib.api.managers;

import pl.serweron.serweronLib.api.entity.IRankPlayer;
import pl.serweron.serweronLib.api.entity.IRank;

import java.util.List;
import java.util.UUID;

public interface IRankManager {

    // Player's options
    IRankPlayer getRankPlayer(UUID uuid);
    IRank getPlayerHighestRank(IRankPlayer player);
    boolean playerHasRank(UUID uuid, String rankName);
    boolean playerHasPermission(UUID uuid, String permission);
    // Player prefix/suffix (by weight)
    String getPlayerPrefix(UUID uuid);
    String getPlayerSuffix(UUID uuid);

    void createRank(IRank rank);
    void deleteRank(IRank rank);

    IRank getRank(String name);
    List<IRank> getAllRanks();
}
