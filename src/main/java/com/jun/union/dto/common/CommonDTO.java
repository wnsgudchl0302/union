package com.jun.union.dto.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@SuperBuilder
public class CommonDTO {
    private static ObjectMapper mapper;

    protected static ObjectMapper getObjectMapper() {
        if(mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return mapper;
    }

    @Override
    public String toString() {
        try {
            return getObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        return "{}";
    }
}
