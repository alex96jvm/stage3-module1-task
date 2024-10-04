package com.mjc.school.repository;

import java.time.LocalDateTime;
import java.util.Objects;

public class News {
    private static Long idCounter = 1L;
    private final Long id;
    private String title;
    private String content;
    private final LocalDateTime createDate;
    private LocalDateTime lastUpdateTime;
    private Long authorId;

    public News(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateTime, Long authorId) {
        this.id = idCounter++;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateTime = lastUpdateTime;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) && Objects.equals(content, news.content) && Objects.equals(authorId, news.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, authorId);
    }

    @Override
    public String toString() {
        return "NewDtoResponse[" +
                "id=" + id +
                ", title=" + title +
                ", content=" + content +
                ", createDate=" + createDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", authorId=" + authorId +
                ']';
    }
}



