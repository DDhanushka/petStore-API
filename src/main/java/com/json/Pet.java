package com.json;

public class Pet {

    private String name;
    private  Integer age;
    private String petType;

    public Pet() {

    }

    public Pet(String name, Integer age, String petType) {
        this.name = name;
        this.age = age;
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
