package com.tobio.tobioutils.collections;

import java.util.List;

public class CollectionUtils {

    protected CollectionUtils() {
        // Empty
    }


    public static boolean isLastElement(int index, List<?> list) {
        return list.get(index) == list.get(list.size() - 1);
    }

}
