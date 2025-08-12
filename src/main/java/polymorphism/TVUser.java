package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

    public static void main(String[] args) {

        // 내가 구현한 factory
//        BeanFactory factory = new BeanFactory();
//        TV tv = (TV) factory.getBean(args[0]);

        // Spring IoC
        AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

        System.out.println("언제1?");

        TV tv = (TV) factory.getBean("tv");

        System.out.println("언제2?");

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();

        factory.close();

    }

}
