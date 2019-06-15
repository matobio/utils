package com.tobio.tobioutils.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tobio.tobioutils.collections.CollectionUtils;
import com.tobio.tobioutils.collections.list.ListUtils;

public class QuantityDistributor {

    public static <T> Map<T, Number> distributeQuantity(Number distributionQuantity, Number totalQuantity, List<T> listElements, Function<? super T, ? extends Number> functionToGetQuantity) {

        Map<T, Number> result = new HashMap<>();

        Number totalElementsQauntity = ListUtils.sum(listElements.stream().map(functionToGetQuantity).collect(Collectors.toList()));
        Number pendingQuantity = totalElementsQauntity.doubleValue() * distributionQuantity.doubleValue() / totalQuantity.doubleValue();

        int size = listElements.size();
        for (int index = 0; index < size; index++) {

            T element = listElements.get(index);

            Number quantityToAssign = null;
            Number ponderatedQuantity = functionToGetQuantity.apply(element).doubleValue() * distributionQuantity.doubleValue() / totalQuantity.doubleValue();

            if (CollectionUtils.isLastElement(index, listElements)) {
                quantityToAssign = pendingQuantity;
                pendingQuantity = 0.0d;
            } else {
                quantityToAssign = ponderatedQuantity;
                pendingQuantity = pendingQuantity.doubleValue() - quantityToAssign.doubleValue();
            }

            result.put(element, quantityToAssign);
        }

        return result;
    }

}
