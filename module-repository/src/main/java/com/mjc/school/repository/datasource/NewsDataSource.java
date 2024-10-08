package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.News;
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

public class NewsDataSource {
    private final Logger logger;
    private final List<News> allNews;

    public NewsDataSource() {
        logger = Logger.getLogger(NewsDataSource.class.getName());
        allNews = new ArrayList<>();
        loadNews();
    }

    private void loadNews(){
        Properties properties = new Properties();
        int INITIAL_CAPACITY = 20;
        try (InputStream input = NewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String newsFilePath = properties.getProperty("newsFilePath");
            String contentFilePath = properties.getProperty("contentFilePath");
            var titles = Files.readAllLines(Paths.get(newsFilePath));
            var contents = Files.readAllLines(Paths.get(contentFilePath));
            var authors = new AuthorDataSource().getAuthors();
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

    public List<News> getAllNews(){
        return allNews;
    }
}

