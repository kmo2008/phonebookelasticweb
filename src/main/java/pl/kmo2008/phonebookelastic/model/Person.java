package pl.kmo2008.phonebookelastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * This class id model class for Person
 */
@Document(indexName = "person")
public class Person {

    /**
     * Id field
     */
    @Id
    public String Id;

    /**
     * Name field
     */
    public String name;
    /**
     * Phone field
     */
    public String phone;

    /**
     * Empty constructor for Person class
     */
    public Person() {
    }

    /**
     * Full constructor for Person class
     * @param id If of person
     * @param name Name of person
     * @param phone Phone number
     */
    public Person(String id, String name, String phone) {
        Id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Constructor for Person class without ID field
     * @param name Name of Person
     * @param phone Phone of Person
     */
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Default toString method
     * @return String of Person
     */
    @Override
    public String toString() {
        return "Person{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    /**
     * Custon toString method for template "name -> number"
     * @return String of person
     */
    public String toStringView() {
        return name + " -> " + phone;
    }
}
