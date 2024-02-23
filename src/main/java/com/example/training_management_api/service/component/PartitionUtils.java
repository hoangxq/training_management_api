package com.example.training_management_api.service.component;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@UtilityClass
public class PartitionUtils {
    public <T> Stream<Pair<T, T>> divideToPair(@NonNull Stream<T> stream) {
        return divideMatchSize(stream, 2)
                .map(c -> Pair.of(c.get(0), c.get(1)));
    }

    public <T> Stream<List<T>> divideMatchSize(@NonNull Stream<T> stream, int chunkSize) {
        return divideMatchSize(stream.collect(Collectors.toList()), chunkSize)
                .stream();
    }

    public <T> List<List<T>> divideMatchSize(@NonNull List<T> list, int chunkSize) {
        if (list.size() % chunkSize != 0) {
            throw new IllegalArgumentException("cannot evenly divide into " + chunkSize + " elements groups");
        }
        return divide(list, chunkSize);
    }

    public <T> List<List<T>> divide(@NonNull List<T> list, int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("chunkSize must larger than 0 ");
        }
        int chunksNumber = estimateChunksNumber(list, chunkSize);
        List<List<T>> trunks = new ArrayList<>();
        IntStream.range(0, chunksNumber)
                .forEach(i -> trunks.add(getChunk(list, chunkSize, i)));
        return trunks;
    }

    private <T> List<T> getChunk(List<T> list, int chunkSize, int chunkIndex) {
        int start = chunkIndex * chunkSize;
        int end = Math.min(start + chunkSize, list.size());
        if (start > end) {
            throw new IndexOutOfBoundsException("Index " + chunkIndex + " is out of the list range <0," + (estimateChunksNumber(list, chunkSize) - 1) + ">");
        }
        return new ArrayList<>(list.subList(start, end));
    }

    private int estimateChunksNumber(List<?> list, int chunkSize) {
        return (int) Math.ceil((double) list.size() / (double) chunkSize);
    }
}
