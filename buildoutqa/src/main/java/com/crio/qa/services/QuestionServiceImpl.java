package com.crio.qa.services;

import com.crio.qa.dto.Answer;
import com.crio.qa.dto.Question;
import com.crio.qa.dto.Summary;
import com.crio.qa.dto.UserResponse;
import com.crio.qa.exchanges.GetQuestionsResponse;
import com.crio.qa.exchanges.PostQuestionsResponse;
import com.crio.qa.exchanges.PostUserQuery;
import com.crio.qa.repositoryservices.QuestionRepositoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  QuestionRepositoryService questionRepositoryService;

  @Override
  public GetQuestionsResponse retrieveQuestions() {
    // TODO Auto-generated method stub

    List<Question> questions = questionRepositoryService.retrieveQuestionsFromModule();
    GetQuestionsResponse response = new GetQuestionsResponse(questions);

    return response;
  }

  @Override
  public PostQuestionsResponse validateUserResponse(
      @RequestBody PostUserQuery postUserQuery) {
    // TODO Auto-generated method stub

    List<Answer> answers = questionRepositoryService.retrieveAnswersFromModule();
    List<UserResponse> responses = postUserQuery.getResponses();
    int score = 0; 
    int total = answers.size();

    for (UserResponse response : responses) {
      String responseId = response.getQuestionId();
      List<String> userAnswer = response.getUserResponse();

      for (Answer answer : answers) {
        String answerId = answer.getQuestionId();
        List<String> correctAnswer = answer.getCorrect();

        if (responseId.equals(answerId)) {
          answer.setUserAnswer(userAnswer);

          if (userAnswer.equals(correctAnswer)) {
            answer.setAnswerCorrect(true);
            score++;
          } else {
            answer.setAnswerCorrect(false);
          }

          break;
        }
      }
    }

    Summary summary = new Summary(score, total);
    PostQuestionsResponse postQuestionsResponse = new PostQuestionsResponse(answers, summary);

    return postQuestionsResponse;
  }
    
}