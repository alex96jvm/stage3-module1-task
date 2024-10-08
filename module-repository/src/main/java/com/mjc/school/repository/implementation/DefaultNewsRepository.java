package com.mjc.school.repository.implementation;

import com.mjc.school.repository.datasource.AuthorDataSource;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DefaultNewsRepository implements com.mjc.school.repository.NewsRepository {
    private final AuthorDataSource authorDataSource;
    private final NewsDataSource newsDataSource;

    public DefaultNewsRepository(AuthorDataSource authorDataSource, NewsDataSource newsDataSource) {
        this.authorDataSource = authorDataSource;
        this.newsDataSource = newsDataSource;
    }

    public News createNews(String title, String content, Long authorId) {
        News newNews = new News(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsDataSource.getAllNews().add(newNews);
        return newNews;
    }

    public List<News> readAllNews() {
        return newsDataSource.getAllNews();
    }

    public List<Author> readAllAuthors() {
        return authorDataSource.getAuthors();
    }

    public Optional<News> readByIdNews(Long id){
        return newsDataSource.getAllNews().stream().filter(n -> n.getId().equals(id)).findAny();
    }

    public News updateNews(Long id, String title, String content, Long authorId) {
        News news = readByIdNews(id).orElseThrow();
        news.setTitle(title);
        news.setContent(content);
        news.setAuthorId(authorId);
        return news;
    }

    public boolean deleteNews(Long id) {
        return newsDataSource.getAllNews().remove(readByIdNews(id).orElseThrow());
    }
}
