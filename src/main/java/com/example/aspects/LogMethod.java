package com.example.aspects;

import org.springframework.stereotype.Component;

import java.sql.SQLSyntaxErrorException;

/**
 * Created by lichao on 16/8/5.
 */
@Component
public class LogMethod {
  @Log
  public void sayHello(){
    System.out.println("this is my Log");
  }
}