package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import java.util.List;

public class DefaultNewsRepository implements NewsRepository {
    private final AuthorData authorData;
    private final NewsDataSource newsDataSource;

    public DefaultNewsRepository(AuthorData authorData, NewsDataSource newsDataSource) {
        this.authorData = authorData;
        this.newsDataSource = newsDataSource;
    }

    public NewsModel createNews(NewsModel news) {
        newsDataSource.getAllNews().add(news);
        return news;
    }

    public List<NewsModel> readAllNews() {
        return newsDataSource.getAllNews();
    }

    public List<AuthorModel> readAllAuthors() {
        return authorData.getAuthors();
    }

    public NewsModel readByIdNews(Long id){
        return newsDataSource.getAllNews().stream()
                .filter(n -> n.getId().equals(id)).findAny().orElseThrow();
    }

    public NewsModel updateNews(NewsModel updateNewsModel) {
        NewsModel news = readByIdNews(updateNewsModel.getId());
        news.setTitle(updateNewsModel.getTitle());
        news.setContent(updateNewsModel.getContent());
        news.setAuthorId(updateNewsModel.getAuthorId());
        return news;
    }

    public Boolean deleteNews(Long id) {
        return newsDataSource.getAllNews().remove(readByIdNews(id));
    }
}
