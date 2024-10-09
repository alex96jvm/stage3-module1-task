package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDTO;
import java.util.List;
import java.util.Scanner;

public interface Controller {
    NewsDTO createNews(Scanner scanner);

    List<NewsDTO> readAllNews();

    NewsDTO readByIdNews(Scanner scanner);

    NewsDTO updateNews(Scanner scanner);

    Boolean deleteNews(Scanner scanner);
}
