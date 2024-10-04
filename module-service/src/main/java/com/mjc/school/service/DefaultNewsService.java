package com.mjc.school.service;

import com.mjc.school.repository.FromFileNewsDataSource;
import com.mjc.school.repository.News;
import com.mjc.school.repository.NewsDataSource;
import java.util.List;


public class DefaultNewsService implements NewsService{

    NewsDataSource newsDataSource;

    DefaultNewsService(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public NewsDTO createNews(String title, String content, Long authorId) {
        return null;
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return List.of();
    }

    @Override
    public NewsDTO getNews(Long id) {
        News news = newsDataSource.getAllNews().stream().filter(n -> n.getId().equals(id)).findAny().get();
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(Long id, String title, String content, Long authorId) {
        return null;
    }

    @Override
    public boolean deleteNews(Long id) {
        return false;
    }
}
