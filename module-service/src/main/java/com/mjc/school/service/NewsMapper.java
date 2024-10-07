package com.mjc.school.service;

import com.mjc.school.repository.models.News;
import com.mjc.school.service.dto.NewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO newsToNewsDto(News news);
}
