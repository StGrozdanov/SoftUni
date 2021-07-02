package T04_InterfacesAndAbstraction.Lab.P04_SayHelloExtend;

public class Bulgarian extends BasePerson {

    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
