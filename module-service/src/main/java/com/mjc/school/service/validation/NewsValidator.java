package com.mjc.school.service.validation;

import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;

public class NewsValidator {
    public void validateNewsData(String title, String content) throws NewsException {
        validateLength(title, 30, "title");
        validateLength(content, 255, "content");
    }

    private void validateLength(String value, int maxLength, String fieldName) throws NewsException {
        if (!value.matches(String.format(".{5,%d}", maxLength))) {
            throw new NewsException(ErrorCodes.INVALID_LENGTH,
                    String.format("News %s can not be less than 5 and more than %d symbols. News %s is %s",
                            fieldName, maxLength, fieldName, value));
        }
    }
}
