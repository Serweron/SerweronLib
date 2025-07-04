package pl.serweron.serweronLib.api.ranks;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface IRank {

    public String getName();
    String getPrefix();
    void setPrefix(String prefix, int weight);
    String getSuffix();
    void setSuffix(String suffix, int weight);

    int getWeight();
    void setWeight(int weight);

    void addPermission(String permission);
    void removePermission(String permission);
    boolean hasPermission(String permission);
}
