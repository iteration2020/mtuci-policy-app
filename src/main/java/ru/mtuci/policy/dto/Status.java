package ru.mtuci.policy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
   private String hostName;
   private String helloWord;
}
