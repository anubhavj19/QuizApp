package com.crio.qa.controller;

import com.crio.qa.exchanges.GetQuestionsResponse;
import com.crio.qa.exchanges.PostQuestionsResponse;
import com.crio.qa.exchanges.PostUserQuery;
import com.crio.qa.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QaController {

  @Autowired
  private QuestionService questionService;

  @GetMapping("/quiz/1")
  public ResponseEntity<GetQuestionsResponse> getQuestions() {

    GetQuestionsResponse getQuestionsResponse = questionService.retrieveQuestions();

    return ResponseEntity.ok().body(getQuestionsResponse);
  }

  @PostMapping(path = "/quiz/1", consumes = {"application/json"})
  public ResponseEntity<PostQuestionsResponse> validateUserResponse(
        @RequestBody PostUserQuery postUserQuery) {
    PostQuestionsResponse postQuestionResponse = 
        questionService.validateUserResponse(postUserQuery);

    return ResponseEntity.ok().body(postQuestionResponse);
  }
}