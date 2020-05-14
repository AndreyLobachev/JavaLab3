package model;

import java.util.Objects;

public class Person implements Cloneable {

    private String firstName;
    private String secondName;
    public final static Person UNKNOWN_PERSON = new Person("", "");

    public Person(String firstName, String secondName) {
        if(firstName == null || secondName == null){
            throw new NullPointerException();
        }
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return String.format("<%s> <%s>", secondName, firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.firstName = firstName;
        person.secondName = secondName;
        return person;
    }
}