package com.tobio.tobioutils.collections.list;

import java.util.List;
import java.util.Objects;

public class ListUtils {

    public static Number sum(List<Number> list) {
        return list.stream().filter(Objects::nonNull).mapToDouble(Number::doubleValue).sum();
    }
}
