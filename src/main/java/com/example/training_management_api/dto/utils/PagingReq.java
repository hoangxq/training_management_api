package com.example.training_management_api.dto.utils;

import com.example.training_management_api.service.component.PartitionUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class PagingReq {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUM = 1;
    @ApiParam(value = "Size of response's pageData. Minimum value is 1. Default is 10")
    @ApiModelProperty(value = "pageSize")
    @Min(1)
    protected Integer pageSize = DEFAULT_PAGE_SIZE;
    @ApiParam(value = "Number of the page. Minimum is 1. Default is 1.")
    @ApiModelProperty(value = "pageNum. Start from 0")
    @Min(1)
    protected Integer pageNum = DEFAULT_PAGE_NUM;
    @ApiParam(value = "Sorting fields.Field name is camelcase like: createDate. Sort direction must be uppercase: ASC, DESC. Examples: &sorts=createDate,ASC&sorts=updateDate,ASC")
    @ApiModelProperty(value = "sort. Direction ASC, DESC. Example: {\"createDate\": \"ASC\"}")
    protected String sorts;
    private LinkedHashMap<String, Sort.Direction> sortMap;

    @JsonIgnore
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    public Pageable makePageable() {
        List<Sort.Order> orders = createOrders(getSortMap());
        // Pageable is 0 base, PageRequest is 1 base
        return PageRequest.of(getPageNum() - 1, getPageSize(), Sort.by(orders));
    }

    @JsonIgnore
    public void setSortDefault(LinkedHashMap<String, Sort.Direction> sortDefault) {
        setSortMap(Optional.ofNullable(getSortMap())
                .filter(MapUtils::isNotEmpty)
                .orElse(sortDefault));
    }

    private List<Sort.Order> createOrders(Map<String, Sort.Direction> sorts) {
        return Optional.ofNullable(sorts)
                .filter(MapUtils::isNotEmpty)
                .map(Map::entrySet)
                .map(entries -> entries.stream()
                        .map(e -> new Sort.Order(e.getValue(), e.getKey()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private LinkedHashMap<String, Sort.Direction> getSortMap() {
        return Optional.ofNullable(sorts)
                .filter(StringUtils::isNotBlank)
                .map(this::buildSortMap)
                .orElseGet(this::getDefaultSortMap);
    }

    private LinkedHashMap<String, Sort.Direction> buildSortMap(String sorts) {
        return PartitionUtils.divideToPair(Arrays.stream(sorts.split(",")))
                .collect(Collectors.toMap(
                        Pair::getLeft,
                        p -> Sort.Direction.fromString(p.getRight()),
                        (k1, k2) -> k1,
                        LinkedHashMap::new
                ));
    }

    private LinkedHashMap<String, Sort.Direction> getDefaultSortMap() {
        return Optional.ofNullable(sortMap)
                .orElse(new LinkedHashMap<>());
    }
}
