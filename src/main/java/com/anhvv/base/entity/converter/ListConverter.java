package com.anhvv.base.entity.converter;

import com.anhvv.base.common.RoleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListConverter implements AttributeConverter<List<RoleType>, String> {

    private static final Logger logger = LogManager.getLogger(ListConverter.class);

    @Override
    public String convertToDatabaseColumn(List<RoleType> roleTypes) {
        if (roleTypes == null) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(roleTypes);
            } catch (JsonProcessingException e) {
                logger.info("Error convert java type to database type.");
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<RoleType> convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<String> temps = objectMapper.readValue(s, List.class);
                if (CollectionUtils.isEmpty(temps)) {
                    return new ArrayList<>();
                } else {
                    return temps.stream().map(RoleType::valueOf).collect(Collectors.toList());
                }
            } catch (JsonProcessingException e) {
                logger.info("Error convert database type to java type.");
                logger.error(e.getMessage());
            }
        }
        return null;
    }
}
