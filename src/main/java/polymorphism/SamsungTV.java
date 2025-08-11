package polymorphism;

public class SamsungTV implements TV {

    /*
        결합도가 높은 프로그램을 만들면 어떤 불편함이 있는지
        예제로 학습하기
    */

    public void powerOn() {
        System.out.println("Samsung TV---전원 켠다.");
    }

    public void powerOff() {
        System.out.println("Samsung TV---전원 끈다.");
    }

    public void volumeUp() {
        System.out.println("Samsung TV---소리 올린다.");
    }
    public void volumeDown() {
        System.out.println("Samsung TV---소리 내린다.");
    }

}
