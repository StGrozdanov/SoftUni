package T04_InterfacesAndAbstraction.Lab.P04_SayHelloExtend;

public abstract class BasePerson implements Person {
    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }

    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public String getName() {
        return name;
    }
}
