package pl.serweron.serweronLib.api.ranks;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface IRank {

    public String getName();

    // Rank Prefix
    String getPrefix();
    void setPrefix(String prefix, int weight);
    // Rank Suffix
    String getSuffix();
    void setSuffix(String suffix, int weight);

    // Rank weight
    int getWeight();
    void setWeight(int weight);

    // Rank permissions
    void addPermission(String permission);
    void removePermission(String permission);
    boolean hasPermission(String permission);
    List<String> getPermissions();
}
