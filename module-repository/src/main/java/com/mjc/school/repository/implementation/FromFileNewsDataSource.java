package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsDataSource;
import com.mjc.school.repository.models.News;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

public class FromFileNewsDataSource implements NewsDataSource {
    private static final Logger logger = Logger.getLogger(FromFileNewsDataSource.class.getName());
    private static final FromFileNewsDataSource newsDataSource = new FromFileNewsDataSource();
    private static final List<News> allNews = new ArrayList<>();
    private static final int INITIAL_CAPACITY = 20;

    static {
        Properties properties = new Properties();
        try (InputStream input = FromFileNewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String newsFilePath = properties.getProperty("newsFilePath");
            String contentFilePath = properties.getProperty("contentFilePath");
            var titles = Files.readAllLines(Paths.get(newsFilePath));
            var contents = Files.readAllLines(Paths.get(contentFilePath));
            var authors = FromFileAuthorDataSource.getAuthorDataSource().getAuthors();
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
            logger.warning(e.getMessage());
        }
    }

    private FromFileNewsDataSource(){};

    public static FromFileNewsDataSource getNewsDataSource() {
        return newsDataSource;
    }

    public List<News> getAllNews(){
        return allNews;
    }
}

