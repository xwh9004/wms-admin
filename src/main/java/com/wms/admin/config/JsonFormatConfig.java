package com.wms.admin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author: xwh90
 * @date: 2022/8/5 22:19
 * @description: json 序列化全局配置
 */
//@JsonComponent
public class JsonFormatConfig {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").withZone(ZoneId.of("GMT+08:00"));

    public static class LocalDateFormatSerializer extends JsonSerializer<LocalDateTime>{
        @Override
        public void serialize(LocalDateTime localDateTime,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(dateTimeFormatter.format(localDateTime));
        }
    }

    public static class LocalDateFormatDeserialize extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser,
                                         DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {
            return LocalDateTime.parse(jsonParser.getText(),dateTimeFormatter);
        }
    }

    public static class DateFormatSerializer extends JsonSerializer<Date>{
        @Override
        public void serialize(Date localDateTime,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(format.format(localDateTime));
        }
    }
}
