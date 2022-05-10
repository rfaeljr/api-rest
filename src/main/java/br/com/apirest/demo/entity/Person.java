package br.com.apirest.demo.entity;

import javax.persistence.*;

//Entity representing people in real world, look that it is with annotation indicating this
//Annotation JPA
@Entity
public class Person {
    //Annotation for indicating that attribute is primary key and the type of the generation value for it
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    //method's get and setters used for access the attributes of the class in encapsulate mode
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
