package com.crio.qa.exchanges;

import com.crio.qa.dto.Answer;
import com.crio.qa.dto.Summary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostQuestionsResponse {

  private List<Answer> questions;
  private Summary summary;
}