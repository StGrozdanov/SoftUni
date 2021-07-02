package T04_InterfacesAndAbstraction.Lab.P03_SayHello;

public class Bulgarian extends Person{

    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
