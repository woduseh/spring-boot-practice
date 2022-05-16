package com.springboot.web.practice.domain.math.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EvalUtil {
  private final ScriptEngineManager manager = new ScriptEngineManager();
  private final ScriptEngine engine = manager.getEngineByName("JavaScript");

  public Object eval(String input) throws ScriptException {
    try {
      if (input.matches(".*[a-zA-Z;~`#$_{}\\[\\]:\\\\;\"',\\.\\?]+.*")) {
        throw new ScriptException("Invalid expression : " + input );
      }
      return engine.eval(input);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}