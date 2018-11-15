package com.weixin.test;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {
     
      @WebMethod
     String greetings (@WebParam(name="name")String name);

}
