package MidExamPreparation.P16_FamilyTree;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private String birthday;
    private List<Person> children;
    private List<Person> parents;

    public Person(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public Person() {
        this.name = null;
        this.birthday = null;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void addChildren(Person children) {
        this.children.add(children);
    }

    public void addParents(Person parents) {
        this.parents.add(parents);
    }

    public List<Person> getChildren() {
        return children;
    }

    public List<Person> getParents() {
        return parents;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.birthday);
    }
}
