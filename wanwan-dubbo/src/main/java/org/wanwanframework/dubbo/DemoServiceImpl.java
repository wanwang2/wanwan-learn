package org.wanwanframework.dubbo;

import java.util.ArrayList;
import java.util.List;  
  
public class DemoServiceImpl implements DemoService {  

    public String sayHello(String name) {  
        return "Hello " + name;  
    }  
  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getUsers() {  
        List list = new ArrayList();  
        User u1 = new User();  
        u1.setName("hejingyuan");  
     
        User u2 = new User();  
        u2.setName("xvshu");  
        
        list.add(u1);  
        list.add(u2);  
          
        return list;  
    }  
} 