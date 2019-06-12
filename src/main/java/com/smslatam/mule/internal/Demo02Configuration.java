package com.smslatam.mule.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(Demo02Operations.class)
@ConnectionProviders(Demo02ConnectionProvider.class)
public class Demo02Configuration {

  @Parameter
  private String configId;

  public String getConfigId(){
    return configId;
  }
}
