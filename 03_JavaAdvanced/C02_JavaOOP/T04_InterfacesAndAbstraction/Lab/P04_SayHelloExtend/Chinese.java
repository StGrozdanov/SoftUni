package T04_InterfacesAndAbstraction.Lab.P04_SayHelloExtend;

public class Chinese extends BasePerson {
    public Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
