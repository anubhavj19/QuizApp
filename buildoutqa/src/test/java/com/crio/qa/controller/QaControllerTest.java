package com.crio.qa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;

import com.crio.qa.QaMain;
import com.crio.qa.exchanges.GetQuestionsResponse;
import com.crio.qa.exchanges.PostQuestionsResponse;
import com.crio.qa.services.QuestionService;
import com.crio.qa.utils.FixtureHelpers;
import com.crio.qa.exchanges.PostUserQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootTest(classes = {QaMain.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class QaControllerTest {
  
  public static final String QUIZ_API_ENDPOINT = "/quiz";
  public static final String MODULE_ID = "/1";
  public static final String QUIZ_API_URI = QUIZ_API_ENDPOINT + MODULE_ID;

  private static final String FIXTURES = "fixtures";
  private ObjectMapper objectMapper;

  private MockMvc mvc;

  @MockBean
  private QuestionService questionService;
  
  @InjectMocks
  private QaController qaController;

  @BeforeEach
  public void setup() {
    objectMapper = new ObjectMapper();
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(qaController).build();
  }
  
  @Test
  public void getResponseTest() throws Exception {
    GetQuestionsResponse sampleGetResponse = loadSampleGetResponseList();

    when(questionService
        .retrieveQuestions(any(String.class)))
        .thenReturn(sampleGetResponse);

    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
        .forClass(String.class);
 
    URI uri = UriComponentsBuilder
        .fromPath(QUIZ_API_URI)
        .build().toUri();

    assertEquals(QUIZ_API_URI, uri.toString());

    MockHttpServletResponse response = 
        mvc.perform(get(uri.toString())).andReturn()
        .getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());

    verify(questionService, times(1))
        .retrieveQuestions(argumentCaptor.capture());

  }

  private GetQuestionsResponse loadSampleGetResponseList() throws IOException {
    String fixture =
        FixtureHelpers.fixture(FIXTURES + "/sample_get_questions_response.json");

    return objectMapper.readValue(fixture,
        new TypeReference<GetQuestionsResponse>() {
        });
  }

}