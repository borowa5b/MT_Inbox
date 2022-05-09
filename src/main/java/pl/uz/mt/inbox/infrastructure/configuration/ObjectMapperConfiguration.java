package pl.uz.mt.inbox.infrastructure.configuration;

import pl.uz.mt.inbox.users.infrastructure.serialization.OffsetDateTimeDeserializer;
import pl.uz.mt.inbox.users.infrastructure.serialization.OffsetDateTimeSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.OffsetDateTime;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        final var simpleModule = new SimpleModule();
        simpleModule.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
        simpleModule.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
        return new ObjectMapper().registerModule(new JavaTimeModule())
                                 .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                 .registerModule(simpleModule)
                                 .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
