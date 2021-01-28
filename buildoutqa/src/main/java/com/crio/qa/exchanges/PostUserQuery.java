package com.crio.qa.exchanges;

import com.crio.qa.dto.UserResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserQuery {
    
  private List<UserResponse> responses;
}