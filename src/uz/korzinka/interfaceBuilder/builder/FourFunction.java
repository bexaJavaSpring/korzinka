package uz.korzinka.interfaceBuilder.builder;

import java.util.List;

@FunctionalInterface
public interface FourFunction<M, T> {

    M fourFunction(T list, T list1, List<M> lists);

}
