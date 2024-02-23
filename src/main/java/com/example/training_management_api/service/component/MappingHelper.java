package com.example.training_management_api.service.component;

import com.example.training_management_api.config.ModelMapperConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappingHelper {

    private ModelMapper modelMapper;
    private final ModelMapperConfig.ModelMapperFactory modelMapperFactory;

    public MappingHelper(ModelMapperConfig.ModelMapperFactory modelMapperFactory) {
        this.modelMapperFactory = modelMapperFactory;
        this.modelMapper = modelMapperFactory.getMapper();
    }

    public <T, H> List<T> mapList(List<H> inputData, Class<T> clazz) {
        return CollectionUtils.isEmpty(inputData) ?
                Collections.emptyList() :
                inputData.stream()
                        .map(i -> modelMapper.map(i, clazz))
                        .collect(Collectors.toList());
    }

    public <T, H> Page<T> mapPage(Page<H> inputData, Class<T> clazz) {
        return inputData
                .map(i -> modelMapper.map(i, clazz));
    }

    public <T, H> T map(H inputData, Class<T> clazz) {
        return modelMapper.map(inputData, clazz);
    }

    public <T, H> void mapIfSourceNotNull(T source, H destination) {
        ModelMapper tmpMapper = modelMapperFactory.getMapper();
        tmpMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        tmpMapper.map(source, destination);
    }

    public <T, H> void mapIfSourceNotNullAndStringNotBlank(T source, H destination) {
        ModelMapper tmpMapper = modelMapperFactory.getMapper();
        tmpMapper.getConfiguration()
                .setPropertyCondition(isStringNotBlank());
        tmpMapper.map(source, destination);
    }

    public <T, H> T mapIgnoreAmbiguity(H inputData, Class<T> clazz) {
        ModelMapper tmpMapper = modelMapperFactory.getMapper();
        tmpMapper.getConfiguration().setAmbiguityIgnored(true);
        return tmpMapper.map(inputData, clazz);
    }

    public <T, H> void copyProperties(T source, H destination) {
        modelMapper.map(source, destination);
    }

    private Condition<Object, Object> isStringNotBlank() {
        return new AbstractCondition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> context) {
                if (context.getSource() instanceof String) {
                    return StringUtils.isNotBlank(String.valueOf(context.getSource()));
                }
                return Objects.nonNull(context.getSource());
            }
        };
    }

}
