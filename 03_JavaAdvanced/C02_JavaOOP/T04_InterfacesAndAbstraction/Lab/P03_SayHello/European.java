package T04_InterfacesAndAbstraction.Lab.P03_SayHello;

public class European extends Person{

    public European(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
