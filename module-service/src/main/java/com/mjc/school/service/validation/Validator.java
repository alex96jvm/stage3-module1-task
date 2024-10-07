package com.mjc.school.service.validation;

import com.mjc.school.repository.implementations.FromFileAuthorDataSource;
import com.mjc.school.repository.implementations.FromFileNewsDataSource;
import com.mjc.school.repository.models.News;

public class Validator {
    public News validateNewsId(Long id) throws NewsException {
        return FromFileNewsDataSource.getNewsDataSource().getAllNews().stream()
                .filter(n -> n.getId().equals(id)).findAny()
                .orElseThrow(() -> new NewsException(ErrorCodes.NEWS_NOT_FOUND, "News with id " + id + " does not exist."));
    }

    public void validateAuthorId(Long authorId) throws NewsException {
        FromFileAuthorDataSource.getAuthorDataSource().getAuthors().stream()
                .filter(n -> n.getId().equals(authorId)).findAny()
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
