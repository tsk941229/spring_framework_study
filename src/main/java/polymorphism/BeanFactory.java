package polymorphism;

public class BeanFactory {

    /*
        Factory 패턴
    */

    public Object getBean(String beanName) {

        if(beanName.equals("samsung")) {
            return new SamsungTV();
        } else if(beanName.equals("lg")) {
            return new LgTV();
        }
        return null;
    }

}
