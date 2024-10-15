package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdatedDate;
    private Long authorId;

    public NewsDto(){}

    public NewsDto(String title, String content, LocalDateTime lastUpdatedDate, Long authorId) {
        this.title = title;
        this.content = content;
        this.lastUpdatedDate = lastUpdatedDate;
        this.authorId = authorId;
    }

    public NewsDto(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdatedDate, Long authorId) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        NewsDto newsDTO = (NewsDto) o;
        return Objects.equals(title, newsDTO.title) && Objects.equals(content, newsDTO.content) && Objects.equals(authorId, newsDTO.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, authorId);
    }

    @Override
    public String toString() {
        return "NewsDtoResponse[" +
                "id=" + id +
                ", title=" + title +
                ", content=" + content +
                ", createDate=" + createDate.truncatedTo(ChronoUnit.SECONDS) +
                ", lastUpdatedDate=" + lastUpdatedDate.truncatedTo(ChronoUnit.SECONDS) +
                ", authorId=" + authorId +
                ']';
    }
}
