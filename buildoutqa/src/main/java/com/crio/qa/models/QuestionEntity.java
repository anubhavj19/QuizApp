package com.crio.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionEntity {

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