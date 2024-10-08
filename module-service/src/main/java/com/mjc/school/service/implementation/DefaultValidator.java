package com.mjc.school.service.implementation;

import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;

public class DefaultValidator {
    public Long validateNumericValue(String id, String subValue) throws NewsException {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NewsException(ErrorCodes.NOT_NUMERIC, subValue + " Id should be number");
        }
    }

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
