package pl.serweron.serweronLib.api.ranks;

import java.util.List;
import java.util.UUID;

public interface IRankManager {
    IRankPlayer getRankPlayer(UUID uuid);
    IRank getPlayerHighestRank(IRankPlayer player);
    boolean playerHasRank(UUID uuid, String rankName);
    boolean playerHasPermission(UUID uuid, String permission);
    String getPlayerPrefix(UUID uuid);
    String getPlayerSuffix(UUID uuid);

    void createRank(IRank rank);
    void deleteRank(IRank rank);

    IRank getRank(String name);
    List<IRank> getAllRanks();
}
