package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private String fileName;
    private final ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(FileSerializer.class);

    public FileSerializer(String fileName) {
        this.fileName = fileName;
        mapper = new ObjectMapper();
    }

    @Override
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        try {
            File file = new File(fileName);
            mapper.writeValue(file, data);
        } catch (IOException e) {
            logger.error("Ошибка при записи JSON в файл: {}", fileName, e);
            throw new FileProcessException(e); // или new RuntimeException(e), если кастомного исключения нет
        }
    }
}
