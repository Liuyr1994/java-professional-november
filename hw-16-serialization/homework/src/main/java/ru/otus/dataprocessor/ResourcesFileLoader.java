package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ResourcesFileLoader implements Loader {
    private static final Logger logger = LoggerFactory.getLogger(ResourcesFileLoader.class);
    private String fileName;
    private final ObjectMapper mapper;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        mapper = new ObjectMapper();
    }

    @Override
    public List<Measurement> load() {
        try (InputStream inputStream = Objects.requireNonNull(
                ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName),
                "Файл не найден в resources: " + fileName)) {
            return mapper.readValue(inputStream, new TypeReference<List<Measurement>>() {
            });

        } catch (IOException e) {
            new RuntimeException(e);
        }
        // читает файл, парсит и возвращает результат
        return Collections.emptyList();
    }
}
