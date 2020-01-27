package com.tobio.tobioutils.collections.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    /**
     * Group a list by one or various classifiers.
     * <br><br>
     * <b>Example:</b>
     * <br><br>
     * Group a list of TestObject grouping by two attributes, length and width. Return a map with:
     * <li> <b>keys:</b> a list with the grouping attributes.
     * <li> <b>values:</b> a list of TestObject.
     * <br><br>
     * {@code Map<List<Object>, List<TestObject>> map = ListUtil.groupMultiple(list, TestObject::getLength, TestObject::getWidth); }
     *
     * @param list : A list
     * @param classifiers : An array of functions to grouping the list.
     * @return
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T, S> Map<T, List<S>> groupMultiple(List<S> list, Function<S, Object>... classifiers) {

        Function<S, List<Object>> compositeKey = e -> IntStream.range(0, classifiers.length).boxed().map(i -> classifiers[i].apply(e)).collect(Collectors.toList());

        return (Map<T, List<S>>) list.stream().collect(Collectors.groupingBy(compositeKey));
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SafeVarargs
    public static <T> List<T> sortMultiple(List<T> list, Function<T, Object>... keyExtractors) {

        if (keyExtractors == null || keyExtractors.length == 0) {
            return new ArrayList<>(list);
        }

        Function keyStractor = keyExtractors[0];
        Comparator<T> comp = Comparator.comparing(keyStractor);
        for (int i = 1; i < keyExtractors.length; i++) {
            keyStractor = keyExtractors[i];
            comp = comp.thenComparing(keyStractor);
        }

        return list.stream().sorted(comp).collect(Collectors.toList());
    }
}
