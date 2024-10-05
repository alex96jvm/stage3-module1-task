package com.mjc.school.service;

import com.mjc.school.repository.News;
import com.mjc.school.repository.NewsDataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class DefaultNewsService implements NewsService{

    NewsDataSource newsDataSource;

    public DefaultNewsService(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public NewsDTO createNews(String title, String content, Long authorId) {
        News news = new News(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsDataSource.getAllNews().add(news);
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsDataSource.getAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO getNews(Long id) {
        News news = findNewsWithId(id);
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(Long id, String title, String content, Long authorId) {
        News news = findNewsWithId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setLastUpdatedDate(LocalDateTime.now());
        news.setAuthorId(authorId);
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public boolean deleteNews(Long id) {
        return newsDataSource.getAllNews().remove(findNewsWithId(id));
    }

    private News findNewsWithId(Long id){
        return newsDataSource.getAllNews().stream().filter(n -> n.getId().equals(id)).findAny().get();
    }
}
