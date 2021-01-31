package com.crio.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "questions")
public class QuestionEntity {

  @Id
  private String id;

  private String questionId;

  private String title;

  private String description;

  private String type;

  private Map<String, String> options;

  private List<String> correctAnswer;

  private List<String> userAnswer;

  private String explanation;

  private boolean answerCorrect;
}