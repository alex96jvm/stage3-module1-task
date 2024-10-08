package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class AuthorData {
    private final Logger logger;
    private final List<AuthorModel> authors;

    public AuthorData() {
        logger = Logger.getLogger(AuthorData.class.getName());
        authors = new ArrayList<>();
        loadNews();
    }

    private void loadNews(){
        Properties properties = new Properties();
        try (InputStream input = NewsDataSource.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
            String authorFilePath = properties.getProperty("authorFilePath");
            try (var lines = Files.lines(Paths.get(authorFilePath))) {
                lines.forEach(line -> authors.add(new AuthorModel(line)));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    public List<AuthorModel> getAuthors(){
        return authors;
    }
}
