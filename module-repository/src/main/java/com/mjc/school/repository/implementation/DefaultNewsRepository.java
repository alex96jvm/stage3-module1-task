package com.mjc.school.repository.implementation;

import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DefaultNewsRepository implements com.mjc.school.repository.NewsRepository {
    private final AuthorData authorData;
    private final NewsDataSource newsDataSource;

    public DefaultNewsRepository(AuthorData authorData, NewsDataSource newsDataSource) {
        this.authorData = authorData;
        this.newsDataSource = newsDataSource;
    }

    public NewsModel createNews(String title, String content, Long authorId) {
        NewsModel newNews = new NewsModel(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsDataSource.getAllNews().add(newNews);
        return newNews;
    }

    public List<NewsModel> readAllNews() {
        return newsDataSource.getAllNews();
    }

    public List<AuthorModel> readAllAuthors() {
        return authorData.getAuthors();
    }

    public Optional<NewsModel> readByIdNews(Long id){
        return newsDataSource.getAllNews().stream().filter(n -> n.getId().equals(id)).findAny();
    }

    public NewsModel updateNews(Long id, String title, String content, Long authorId) {
        NewsModel news = readByIdNews(id).orElseThrow();
        news.setTitle(title);
        news.setContent(content);
        news.setAuthorId(authorId);
        return news;
    }

    public Boolean deleteNews(Long id) {
        return newsDataSource.getAllNews().remove(readByIdNews(id).orElseThrow());
    }
}
