package com.smslatam.mule.internal;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class Demo02Operations {

  private final Logger LOGGER = LoggerFactory.getLogger(Demo02Operations.class);

  /**
   * Example of an operation that uses the configuration and a connection instance to perform some action.
   */
  @MediaType(value = ANY, strict = false)
  public String retrieveInfo(@Config Demo02Configuration configuration, @Connection Demo02Connection connection){
    return "Using Configuration [" + configuration.getConfigId() + "] with Connection id [" + connection.getId() + "]";
  }

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
  @MediaType(value = ANY, strict = false)
  public String sayHi(String person) {
    return buildHelloMessage(person);
  }

  /**
   * Example of a async operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
  @Alias("SayGoodbye")
  @MediaType(value = ANY, strict = false)
  public void sayBye(String person,  CompletionCallback<String, String> callback ) {
    LOGGER.info("begin async operation declaration");
    if (person == null || person.isEmpty()) {
     callback.error(new RuntimeException("argument person is null or empty"));
    }
    else {
      callback.success(Result.<String, String>builder()
              .output(buildGoodByeMessage(person))
              .attributes("GOOD BYE")
              .build());
    }
    LOGGER.info("finish async operation declaration");
  }

  /**
   * Private Methods are not exposed as operations
   */
  private String buildHelloMessage(String person) {
    LOGGER.info("building hello message");
    return "Hello " + person + "!!!";
  }

  /**
   * Private Methods are not exposed as operations
   */
  private String buildGoodByeMessage(String person) {
    LOGGER.info("building good bye message");
    return "Good bye " + person + ".";
  }
}
