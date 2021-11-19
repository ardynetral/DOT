package com.example.simpleapplication.util;

import com.example.simpleapplication.service.Impl.MovieServiceImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtility {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtility.class);
    public static <T> Object parse(Object obj, Class<T> clazz){
        Object returnObj =null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return (T) mapper.readValue((String) obj, clazz);
        }catch (Exception e){
            logger.error("error disini : "+e.getMessage());
            e.printStackTrace();
        }
        return returnObj;
    }

    public static String toJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String response = "";
        try {
            response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (JsonGenerationException e){
            e.printStackTrace();
        }catch (JsonMappingException jm){
            jm.printStackTrace();
        }catch (Exception es){
            es.printStackTrace();
        }
        return response;
    }
}
