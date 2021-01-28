package com.crio.qa.services;

import com.crio.qa.exchanges.GetQuestionsResponse;
import com.crio.qa.exchanges.PostQuestionsResponse;
import com.crio.qa.exchanges.PostUserQuery;

public interface QuestionService {

  GetQuestionsResponse retrieveQuestions(String moduleId);
  
  PostQuestionsResponse validateUserResponse(String moduleId, PostUserQuery postUserResponse);
}