package com.tobio.tobioutils.collections.list;

import java.util.List;
import java.util.Objects;

import com.tobio.tobioutils.collections.CollectionUtils;

public class ListUtils extends CollectionUtils {

    private ListUtils() {
        super();
    }


    public static Number sum(List<Number> list) {
        if (list == null) {
            return 0.0d;
        }
        return list.stream().filter(Objects::nonNull).mapToDouble(Number::doubleValue).sum();
    }
}
