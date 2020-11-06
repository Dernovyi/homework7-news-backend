package pl.dernovyi.homework7newsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.dernovyi.homework7newsbackend.model.MyUrl;
import pl.dernovyi.homework7newsbackend.model.News;
import pl.dernovyi.homework7newsbackend.model.NewsDto;
import pl.dernovyi.homework7newsbackend.model.Rate;
import pl.dernovyi.homework7newsbackend.service.GetNewsService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/news", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class NewsToDBController {


    GetNewsService newsService;
    MyUrl myUrl;
    List<News> list = new ArrayList<>();
    @Autowired
    public NewsToDBController(GetNewsService newsService, MyUrl myUrl) {
        this.newsService = newsService;
        this.myUrl = myUrl;
    }


    @GetMapping
    public ResponseEntity<List<NewsDto>> getNewNews(){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(myUrl.getUrl())
                .queryParam("apiKey", myUrl.getApyKey());

        ResponseEntity<Rate> forEntity = restTemplate.getForEntity(url.toUriString(), Rate.class);
        list = forEntity.getBody().getNews();

        newsService.addNewsToDB(list);

        return new ResponseEntity<>(newsService.getNews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNews(@PathVariable String id){

        return new ResponseEntity<>( newsService.getNewsById(id), HttpStatus.OK);
    }

}
