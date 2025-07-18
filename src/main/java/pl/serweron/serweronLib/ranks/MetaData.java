package pl.serweron.serweronLib.ranks;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a metadata of player or Rank.
 * Including prefix, prefix weight, suffix and suffix weight.
 */
@Data
@AllArgsConstructor
public class MetaData {
    private String prefix, suffix;
    private int prefixWeight, suffixWeight;
}