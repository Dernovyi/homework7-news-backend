package pl.dernovyi.homework7newsbackend.service;

import pl.dernovyi.homework7newsbackend.model.News;
import pl.dernovyi.homework7newsbackend.model.NewsDto;

import java.util.List;

public interface GetNewsService {
   List<NewsDto> getNews();
   void  addNewsToDB(List<News> list);

   NewsDto getNewsById(String id);
}
