package org.wanwanframework.dubbo;

import java.util.List;

public interface DemoService {

    @SuppressWarnings("rawtypes")
	public List getUsers();
    
    public String sayHello(String name);
}
