package com.weixin.test;

import javax.jws.WebService;

@WebService (endpointInterface = "com.weixin.test.HelloService" )
public class HelloServiceImpl implements HelloService {

      @Override
      public String greetings(String name) {
            return "Hello: " + name;
     }

}
