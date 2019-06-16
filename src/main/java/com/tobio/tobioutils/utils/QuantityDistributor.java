package com.tobio.tobioutils.utils;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tobio.tobioutils.collections.CollectionUtils;
import com.tobio.tobioutils.collections.list.ListUtils;

public class QuantityDistributor {

    private QuantityDistributor() {
        // Empty
    }


    /**
     * This method ponderate quantity in the elements of the list.
     *
     * <br><br>
     * Example:
     * <br>
     * <li> QuantityDistributor.distributeQuantity(10, 30, list, QuantityElements::getQuantity, QuantityElements::setQuantity);
     *
     *
     * <br>
     * <br>
     * @param distributionQuantity
     * @param totalQuantity
     * @param listElements
     * @param getQuantityFunction
     * @param setQuantityFunction
     */
    public static <T> void distributeQuantity(Number distributionQuantity, Number totalQuantity, List<T> listElements, Function<? super T, ? extends Number> getQuantityFunction,
            BiConsumer<T, Number> setQuantityFunction) {

        Number totalElementsQauntity = ListUtils.sum(listElements.stream().map(getQuantityFunction).collect(Collectors.toList()));
        Number pendingQuantity = totalElementsQauntity.doubleValue() * distributionQuantity.doubleValue() / totalQuantity.doubleValue();

        int size = listElements.size();
        for (int index = 0; index < size; index++) {

            T element = listElements.get(index);

            Number quantityToAssign = null;
            Number ponderatedQuantity = getQuantityFunction.apply(element).doubleValue() * distributionQuantity.doubleValue() / totalQuantity.doubleValue();

            if (CollectionUtils.isLastElement(index, listElements)) {
                quantityToAssign = pendingQuantity;
                pendingQuantity = 0.0d;
            } else {
                quantityToAssign = ponderatedQuantity;
                pendingQuantity = pendingQuantity.doubleValue() - quantityToAssign.doubleValue();
            }
            setQuantityFunction.accept(element, quantityToAssign);
        }
    }

}
