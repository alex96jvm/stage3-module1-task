package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto newsToNewsDto(NewsModel news);

    @Mapping(target = "id", ignore = true)
    NewsModel newsDtoToNews(NewsDto newsDto);
}
