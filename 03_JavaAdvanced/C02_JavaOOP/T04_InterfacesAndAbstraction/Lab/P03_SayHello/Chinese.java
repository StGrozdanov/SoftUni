package T04_InterfacesAndAbstraction.Lab.P03_SayHello;

public class Chinese extends Person{
    public Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
