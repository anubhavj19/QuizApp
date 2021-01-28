package com.crio.qa.exchanges;

import com.crio.qa.dto.Question;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionsResponse {
    
  private List<Question> questions;
}