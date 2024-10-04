package com.mjc.school.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FromFileNewsDataSource implements NewsDataSource{
    private static final FromFileNewsDataSource newsDataSource = new FromFileNewsDataSource();
    private static final List<News> allNews = new ArrayList<>();
    private static final int INITIAL_CAPACITY = 20;

    static {
        try {
            List<String> titles = Files.readAllLines(Paths.get("module-repository/src/main/resources/news.txt"));
            List<String> contents = Files.readAllLines(Paths.get("module-repository/src/main/resources/content.txt"));
            List<Author> authors = FromFileAuthorDataSource.getAuthorDataSource().getAuthors();
            Random random = new Random();
            int count = 0;
            while (count < INITIAL_CAPACITY) {
                allNews.add(new News(
                        titles.get(random.nextInt(titles.size())),
                        contents.get(random.nextInt(contents.size())),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        authors.get(random.nextInt(authors.size())).getId()
                ));
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FromFileNewsDataSource(){};

    public static FromFileNewsDataSource getAuthorDataSource () {
        return newsDataSource;
    }

    public List<News> getAllNews(){
        return allNews;
    }
}

