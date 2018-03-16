package pl.kmo2008.phonebookelastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "person")
public class Person {

    @Id
    public String Id;

    public String name;

    public String phone;

    public Person() {
    }

    public Person(String id, String name, String phone) {
        Id = id;
        this.name = name;
        this.phone = phone;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public String toStringView() {
        return name + " -> " + phone;
    }
}
