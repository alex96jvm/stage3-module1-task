package com.mjc.school.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AuthorDataSource {
    private static final AuthorDataSource authorDataSource = new AuthorDataSource();
    private static final List<Author> authors = new ArrayList<>();

    static {
        try (Stream<String> lines = Files.lines(Paths.get("module-repository/src/main/resources/author.txt"))) {
            lines.forEach(line -> authors.add(new Author(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AuthorDataSource(){};

    public static AuthorDataSource getAuthorDataSource () {
        return authorDataSource;
    }

    public List<Author> getAuthors(){
        return authors;
    }
}
