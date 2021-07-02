package T04_InterfacesAndAbstraction.Lab.P03_SayHello;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String sayHello();

}
