package com.mjc.school.repository;

import com.mjc.school.repository.models.Author;

import java.util.List;

public interface AuthorDataSource {
    List<Author> getAuthors();
}
