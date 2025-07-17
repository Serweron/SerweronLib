package pl.serweron.serweronLib.ranks;

import lombok.Data;

@Data
public class MetaData {
    private String prefix, suffix;
    private int prefixWeight, suffixWeight;

    public MetaData(String prefix, int prefixWeight, String suffix, int suffixWeight) {
        this.prefix = prefix;
        this.prefixWeight = prefixWeight;
        this.suffix = suffix;
        this.suffixWeight = suffixWeight;
    }
}