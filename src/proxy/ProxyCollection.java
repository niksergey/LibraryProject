package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyCollection implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case ("add"):
                return true;
            case ("remove"):
                return false;
            case ("contains"):
                boolean[] mas = {true, false, true, true};
                Integer index = (Integer) args[0];
                if (index >= mas.length) {
                    return false;
                }
                return mas[index];
        }
        return true;
    }
}