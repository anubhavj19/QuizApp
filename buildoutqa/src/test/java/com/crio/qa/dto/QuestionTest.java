package com.crio.qa.dto;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class QuestionTest {
    
    @Test
    public void serializeAndDeserializeQuestionJson() throws IOException, JSONException {
      final String jsonString =
          "{\n"
              + "  \"questionId\": \"001\",\n"
              + "  \"title\": \"What is the default IP address of localhost?\",\n"
              + "  \"type\": \"objective-single\",\n"
              + "  \"options\": {\n"
              + "    \"1\": \"0.0.0.0\",\n"
              + "    \"2\": \"192.168.0.12\",\n"
              + "    \"3\": \"127.0.0.1\",\n"
              + "    \"4\": \"255.255.255.255\"\n"
              + "  }\n"
              + "}";
  
      Question question = new Question();
      question = new ObjectMapper().readValue(jsonString, Question.class);
  
      String actualJsonString = "";
      actualJsonString = new ObjectMapper().writeValueAsString(question);
      JSONAssert.assertEquals(jsonString, actualJsonString, true);
    }
  
}