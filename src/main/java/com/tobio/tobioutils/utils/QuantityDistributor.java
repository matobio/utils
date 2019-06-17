package com.tobio.tobioutils.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;
import java.util.stream.Collectors;

import com.tobio.tobioutils.collections.CollectionUtils;
import com.tobio.tobioutils.collections.list.ListUtils;

public class QuantityDistributor {

    private QuantityDistributor() {
        // Empty
    }


    /**
     * @param <T>
     * @param distributionQuantity
     * @param totalDistributionQuantity
     * @param elements
     * @param getQuantityFunction
     * @return
     */
    public static <T> List<Number> distributeQuantity(Number distributionQuantity, Number totalDistributionQuantity, List<T> elements, Function<? super T, ? extends Number> getQuantityFunction) {

        List<Number> result = new ArrayList<>();

        if (elements == null) {
            return result;
        } else if (distributionQuantity == null || distributionQuantity.doubleValue() == 0.0d || totalDistributionQuantity == null || totalDistributionQuantity.doubleValue() == 0.0d) {
            elements.forEach(el -> result.add(0.0d));
            return result;
        }

        Number totalQuantity = ListUtils.sum(elements.stream().map(getQuantityFunction).collect(Collectors.toList()));
        Number pendingQuantity = totalQuantity.doubleValue() * distributionQuantity.doubleValue() / totalDistributionQuantity.doubleValue();

        int size = elements.size();
        for (int index = 0; index < size; index++) {

            T element = elements.get(index);

            Number quantityToAssign = null;
            Number ponderatedQuantity = getQuantityFunction.apply(element).doubleValue() * distributionQuantity.doubleValue() / totalDistributionQuantity.doubleValue();

            if (CollectionUtils.isLastElement(index, elements)) {
                quantityToAssign = pendingQuantity;
                pendingQuantity = 0.0d;
            } else {
                quantityToAssign = ponderatedQuantity;
                pendingQuantity = pendingQuantity.doubleValue() - quantityToAssign.doubleValue();
            }
            result.add(quantityToAssign);
        }

        return result;
    }


    /**
     * This method distribute quantity in the elements of the list.
     *
     * <br><br>
     * Example:
     * <br>
     * <li> QuantityDistributor.distributeQuantity(10, 30, lots, T::getCantidad, T::setCantidad);
     *
     * <br>
     * <br>
     * @param distributionQuantity
     * @param totalDistributionQuantity
     * @param elements
     * @param getQuantityFunction
     * @param setQuantityFunction
     */
    public static <T> void distributeQuantity(Number distributionQuantity, Number totalDistributionQuantity, List<T> elements, Function<? super T, ? extends Number> getQuantityFunction,
            ObjDoubleConsumer<T> setQuantityFunction) {

        List<Number> result = QuantityDistributor.distributeQuantity(distributionQuantity, totalDistributionQuantity, elements, getQuantityFunction);

        for (int i = 0; i < result.size(); i++) {
            setQuantityFunction.accept(elements.get(i), result.get(i).doubleValue());
        }
    }


    /**
     * This method ponderate the details quantity between the list elements.
     *
     * <br><br>
     *
     * <li> <b>Example</b>:
     * Si tenemos una lista de elementos con cantidades {10,20,30} y queremos ponderar para cada uno de estos elementos una lista de detalles con
     * cantidades {1,2,3,4}. Este metodo devuelve en el resultado una lista de objetos Pair formados por:
     * <li> El primer elemento es la cantidad ponderada.
     * <li> El segundo elemento es una lista con las poderaciones que le corresponden a ese elemento para las cantidades de los detalles a poderar.
     *
     * <br><br>
     * Para el ejemplo mencionado, tenemos una cantidad total de (1+2+3+4) 10 unidades para ponderar entre los elementos con cantidades 10, 20 y 30.
     * Este metodo devolvera una lista de objetos Pair tal que:
     * <li> Primer elemento: Pair< 10*10/60, {1*10/60, 2*10/60, 3*10/60, 4*10/60} >
     * <li> Segundo elemento: Pair< 10*20/60, {1*20/60, 2*20/60, 3*20/60, 4*20/60} >
     * <li> Tercer elemento: Pair< 10*30/60, {1*30/60, 2*30/60, 3*30/60, 4*30/60} >
     *
     * @param <T>
     * @param <S>
     * @param elements
     * @param detailElements
     * @param getQuantityFunction
     * @param getQuantityFunction2
     * @param precision
     * @return
     */
    public static <T, S> List<Pair<Number, List<Number>>> distributeQuantity(List<T> elements, List<S> detailElements, Function<? super T, ? extends Number> getQuantityFunction,
            Function<? super S, ? extends Number> getQuantityFunction2) {

        List<Pair<Number, List<Number>>> result = new ArrayList<>();

        if (elements == null || detailElements == null) {
            return result;
        }

        Number totalQuantity = ListUtils.sum(elements.stream().map(getQuantityFunction).collect(Collectors.toList()));
        Number totalDetailsQuantity = ListUtils.sum(detailElements.stream().map(getQuantityFunction2).collect(Collectors.toList()));

        Number pendingQuantity = totalDetailsQuantity;
        Number pendingDetailQuantity = totalDetailsQuantity;

        int size = elements.size();
        for (int i = 0; i < size; i++) {

            T element = elements.get(i);

            Number quantityToAssign = null;
            Number ponderatedQuantity = getQuantityFunction.apply(element).doubleValue() * totalDetailsQuantity.doubleValue() / totalQuantity.doubleValue();

            if (CollectionUtils.isLastElement(i, elements)) {
                quantityToAssign = pendingQuantity;
                pendingQuantity = 0.0d;
            } else {
                quantityToAssign = ponderatedQuantity;
                pendingQuantity = pendingQuantity.doubleValue() - quantityToAssign.doubleValue();
            }

            List<Number> detailPonderatedQuantities = new ArrayList<>();
            for (int j = 0; j < detailElements.size(); j++) {

                S detailElement = detailElements.get(j);

                Number quantityDetailToAssign = null;
                Number ponderatedDetailQuantity = getQuantityFunction2.apply(detailElement).doubleValue() * getQuantityFunction.apply(element).doubleValue() / totalQuantity.doubleValue();

                if (CollectionUtils.isLastElement(i, elements) && CollectionUtils.isLastElement(j, detailElements)) {
                    quantityDetailToAssign = pendingDetailQuantity;
                    pendingDetailQuantity = 0.0d;
                } else {
                    quantityDetailToAssign = ponderatedDetailQuantity;
                    pendingDetailQuantity = pendingDetailQuantity.doubleValue() - quantityDetailToAssign.doubleValue();
                }

                detailPonderatedQuantities.add(quantityDetailToAssign);
            }

            result.add(new Pair<>(quantityToAssign, detailPonderatedQuantities));
        }

        QuantityDistributor.fixQuantities(detailElements, getQuantityFunction2, result);

        return result;
    }


    /**
     * Si a causa de decimales se ha anhadido mas cantidad de la total, es decir, que la suma de las cantidades ponderadas supera a la cantidad a ponderar,
     * resta la diferencia a las cantidades ponderadas.
     *
     * @param <T>
     * @param detailElements
     * @param getQuantityFunction
     * @param result
     */
    protected static <T> void fixQuantities(List<T> detailElements, Function<? super T, ? extends Number> getQuantityFunction, List<Pair<Number, List<Number>>> result) {

        for (int i = 0; i < detailElements.size(); i++) {

            T detailElement = detailElements.get(i);

            Double quantity = getQuantityFunction.apply(detailElement).doubleValue();

            Double totalPonderatedQuantity = 0.0d;
            for (Pair<Number, List<Number>> pair : result) {
                totalPonderatedQuantity = totalPonderatedQuantity.doubleValue() + pair.getSecond().get(i).doubleValue();
            }

            Double diff = totalPonderatedQuantity - quantity;
            while (diff > 0.0d) {
                for (Pair<Number, List<Number>> pair : result) {
                    Double quantityPonderated = pair.getSecond().get(i).doubleValue();
                    if (quantityPonderated > 0) {
                        Double newQuantity = Math.min(quantityPonderated, diff);
                        pair.getSecond().set(i, quantityPonderated - newQuantity);
                        diff = diff - newQuantity;
                        break;
                    }
                }
            }
        }
    }

}
