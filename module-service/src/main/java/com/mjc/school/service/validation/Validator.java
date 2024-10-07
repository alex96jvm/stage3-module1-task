package com.mjc.school.service.validation;

import com.mjc.school.repository.models.Author;
import com.mjc.school.repository.models.News;
import java.util.List;

public class Validator {
    private final List<Author> authors;
    private final List<News> allNews;

    public Validator (List<Author> authors, List<News> allNews) {
        this.authors = authors;
        this.allNews = allNews;
    }

    public News findNewsById(Long id) throws NewsException {
        return allNews.stream().filter(n -> n.getId().equals(id)).findAny()
                .orElseThrow(() -> new NewsException(ErrorCodes.NEWS_NOT_FOUND, "News with id " + id + " does not exist."));
    }

    public void validateAuthorId(Long authorId) throws NewsException {
        authors.stream().filter(n -> n.getId().equals(authorId)).findAny()
                .orElseThrow(() -> new NewsException(ErrorCodes.AUTHOR_NOT_FOUND, "Author Id does not exist. Author Id is: " + authorId));
    }

    public void validateTitle(String title) throws NewsException {
        if (!title.matches(".{5,30}")){
            throw new NewsException(ErrorCodes.INVALID_LENGTH, "News title can not be less than 5 and more than 30 symbols. News title is " + title);
        }
    }

    public void validateContent(String content) throws NewsException {
        if (!content.matches(".{5,255}")){
            throw new NewsException(ErrorCodes.INVALID_LENGTH, "News content can not be less than 5 and more than 255 symbols. News content is " + content);
        }
    }

    public Long validateNumericValue(String id, String subValue) throws NewsException {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NewsException(ErrorCodes.NOT_NUMERIC, subValue + " Id should be number");
        }
    }
}
