package com.mjc.school.repository.model;

import java.util.Objects;

public class AuthorModel {
    private static Long idCounter = 1L;
    private final Long id;
    private final String name;

    public AuthorModel(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public Long getId (){
        return id;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorModel author = (AuthorModel) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
