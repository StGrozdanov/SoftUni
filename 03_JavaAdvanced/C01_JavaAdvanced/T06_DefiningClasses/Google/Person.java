package T06_DefiningClasses.Exercise.Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private List<Pokemon> pokemons;
    private List<Parents> parents;
    private List<Children> children;

    public Person(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void addParents(Parents parent) {
        this.parents.add(parent);
    }

    public void addPokemons(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public void addChildren(Children child) {
        this.children.add(child);
    }

    public String getLists(List<Object> elements) {
        StringBuilder builder = new StringBuilder();
        if (!elements.isEmpty()) {
            for (Object element : elements) {
                builder.append(element.toString());
            }
            return builder.toString();
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.name).append(System.lineSeparator());
        builder.append("Company:").append(System.lineSeparator());
        if (this.company != null) {
            builder.append(company.toString());
        }
        builder.append("Car:").append(System.lineSeparator());
        if (this.car != null) {
            builder.append(car.toString());
        }
        builder.append("Pokemon:").append(System.lineSeparator());
        builder.append(getLists(Collections.singletonList(pokemons.toString())).replaceAll("[\\[\\],]", ""));
        builder.append("Parents:").append(System.lineSeparator());
        builder.append(getLists(Collections.singletonList(parents)).replaceAll("[\\[\\],]", ""));
        builder.append("Children:").append(System.lineSeparator());
        builder.append(getLists(Collections.singletonList(children)).replaceAll("[\\[\\],]", ""));

        return builder.toString();
    }
}
