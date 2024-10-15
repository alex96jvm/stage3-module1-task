package com.mjc.school.controller;

import com.mjc.school.service.exception.NewsException;
import java.util.List;

public interface Controller<T> {
    List<T> readAll();

    T readById(Long id) throws NewsException;

    T create(T dto) throws NewsException;

    T update(T dto) throws NewsException;

    Boolean delete(Long id) throws NewsException;
}
