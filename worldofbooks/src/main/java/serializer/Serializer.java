package serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import reporting.Report;

public class Serializer {
    private static Serializer serializer = null;
    private static ObjectMapper objectMapper = null;

    private Serializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public static Serializer getInstance() {
        if (serializer == null) serializer = new Serializer();
        return serializer;
    }

    public String serializeReport(Report report) throws JsonProcessingException {
        return objectMapper.writeValueAsString(report);
    }
}
