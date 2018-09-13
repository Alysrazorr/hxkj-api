package com.hxkj.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")
public class JsonUtils {

    private static final Logger logger = LogManager.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    private static JsonNodeFactory jnf = new JsonNodeFactory(false);

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    public static String toPJson(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    private static JsonNode read(InputStream is) {
        try {
            return objectMapper.readTree(is);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private static JsonNode read(String str) {
        try {
            return objectMapper.readTree(new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8"))));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static ArrayNode createArrayNode() {
        return jnf.arrayNode();
    }

    public static ArrayNode readArray(String str) {
        return (ArrayNode) read(str);
    }

    public static ArrayNode readArray(InputStream is) {
        return (ArrayNode) read(is);
    }

    public static ObjectNode createObjectNode() {
        return jnf.objectNode();
    }

    public static ObjectNode readObject(String str) {
        return (ObjectNode) read(str);
    }

    public static ObjectNode readObject(InputStream is) {
        return (ObjectNode) read(is);
    }

}
