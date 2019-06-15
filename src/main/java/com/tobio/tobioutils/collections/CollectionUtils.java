package com.tobio.tobioutils.collections;

import java.util.List;

public class CollectionUtils {

    public static boolean isLastElement(int index, List<?> list) {
        return index == list.indexOf(list.get(list.size() - 1)) && list.get(index) == list.get(list.size() - 1);
    }

}
