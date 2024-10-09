package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public interface NewsService {
    NewsDTO createNews(NewsDTO newsDTO) throws NewsException;

    List<NewsDTO> getAllNews();

    NewsDTO getNews(Long id) throws NewsException;

    NewsDTO updateNews(NewsDTO newsDTO) throws NewsException;

    Boolean deleteNews(Long id) throws NewsException;
}
