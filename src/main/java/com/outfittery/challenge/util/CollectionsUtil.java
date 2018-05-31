package com.outfittery.challenge.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionsUtil {

    public static Set<String> toLowerCase(Set<String> list) {
        Set<String> result = Optional.ofNullable(list).orElse(new HashSet<>());
        return result.stream().map(s -> s.trim().toLowerCase()).collect(Collectors.toSet());
    }
}
