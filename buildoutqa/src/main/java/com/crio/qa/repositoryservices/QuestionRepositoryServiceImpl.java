package com.crio.qa.repositoryservices;

import com.crio.qa.dto.Answer;
import com.crio.qa.dto.Question;
import com.crio.qa.models.ModuleEntity;
import com.crio.qa.models.QuestionEntity;
import com.crio.qa.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionRepositoryServiceImpl implements QuestionRepositoryService {

  @Autowired
  private QuestionRepository questionRepository;

  @Override
  public List<Question> retrieveQuestionsFromModule(String moduleId) {
    // TODO Auto-generated method stub
    List<ModuleEntity> modules = questionRepository.getModuleDetails(moduleId);
    List<QuestionEntity> questionEntities = new ArrayList<>();

    for (ModuleEntity module : modules) {
      questionEntities.addAll(module.getQuestions());
    }

    
    ModelMapper modelMapper = new ModelMapper();

    List<Question> questions = questionEntities
        .stream()
        .map(questionEntity -> modelMapper.map(questionEntity, Question.class))
        .collect(Collectors.toList());

    return questions;
  }

  @Override
  public List<Answer> retrieveAnswersFromModule(String moduleId) {
    // TODO Auto-generated method stub

    List<ModuleEntity> modules = questionRepository.getModuleDetails(moduleId);
    List<QuestionEntity> questionEntities = new ArrayList<>();

    for (ModuleEntity module : modules) {
      questionEntities.addAll(module.getQuestions());
    }

    
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addMappings(new PropertyMap<QuestionEntity, Answer>() {
      protected void configure() {
        map().setCorrect(source.getCorrectAnswer());
        }
      });

    List<Answer> answers = questionEntities
        .stream()
        .map(questionEntity -> modelMapper.map(questionEntity, Answer.class))
        .collect(Collectors.toList());

    return answers;
  }
}