package org.learn.demon;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {
    public static void main(String[] args) {
        UserDaoImpl dao = new UserDaoImpl();
        Class[] interfaces = {UserDao.class};
        UserDao res = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(dao));
        int add = res.add(1, 2);
        String s = res.update("名字");
        System.out.println(add);
        System.out.println(s);
    }
}
class UserDaoProxy implements InvocationHandler{
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法之前执行：："+method.getName()+"传递的参数"+ Arrays.toString(args));
        Object res = method.invoke(obj, args);
        System.out.println("方法之后执行：："+obj);
        return res;
    }
}