package com.mjc.school.repository.implementations;

import com.mjc.school.repository.AuthorDataSource;
import com.mjc.school.repository.models.Author;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class FromFileAuthorDataSource implements AuthorDataSource {
    private static final Logger logger = Logger.getLogger(FromFileAuthorDataSource.class.getName());
    private static final FromFileAuthorDataSource authorDataSource = new FromFileAuthorDataSource();
    private static final List<Author> authors = new ArrayList<>();

    static {
        Properties properties = new Properties();
        try (InputStream input = FromFileNewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String authorFilePath = properties.getProperty("authorFilePath");
            try (var lines = Files.lines(Paths.get(authorFilePath))) {
                lines.forEach(line -> authors.add(new Author(line)));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private FromFileAuthorDataSource(){};

    public static FromFileAuthorDataSource getAuthorDataSource () {
        return authorDataSource;
    }

    public List<Author> getAuthors(){
        return authors;
    }
}
