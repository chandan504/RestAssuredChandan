package OAUth.test;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest
{
    @BeforeMethod
    public void method(Method m){
     System.out.println("Starting Test" +m.getName());
        System.out.println("Thred is running "+Thread.currentThread().getId());
    }
}
