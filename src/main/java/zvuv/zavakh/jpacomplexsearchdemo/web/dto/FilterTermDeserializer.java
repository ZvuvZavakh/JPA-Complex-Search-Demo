package zvuv.zavakh.jpacomplexsearchdemo.web.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class FilterTermDeserializer extends JsonDeserializer<FilterTerm> {

    @Override
    public FilterTerm deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = objectMapper.readTree(jsonParser);
        System.out.println(root);
        if (root.has("operation") &&
                root.get("operation").asText().equals("IN") &&
                root.has("value") &&
                root.get("value").isArray()
        )   {
            return objectMapper.readValue(root.toString(), FilterTermMultipleValues.class);
        } else {
            return objectMapper.readValue(root.toString(), FilterTermSingleValue.class);
        }
    }
}
