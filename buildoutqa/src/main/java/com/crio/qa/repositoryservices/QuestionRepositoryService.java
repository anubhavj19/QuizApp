package com.crio.qa.repositoryservices;

import com.crio.qa.dto.Answer;
import com.crio.qa.dto.Question;

import java.util.List;

public interface QuestionRepositoryService {

  List<Question> retrieveQuestionsFromModule(String moduleId);

  List<Answer> retrieveAnswersFromModule(String moduleId);
}