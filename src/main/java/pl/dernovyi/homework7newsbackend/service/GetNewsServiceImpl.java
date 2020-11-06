package pl.dernovyi.homework7newsbackend.service;

import com.sun.tools.internal.xjc.model.CElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.dernovyi.homework7newsbackend.model.News;
import pl.dernovyi.homework7newsbackend.model.NewsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetNewsServiceImpl implements GetNewsService {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public GetNewsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NewsDto> getNews() {
          String sql = "SELECT * FROM news";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        List<NewsDto> news =new ArrayList<>();
        maps.stream().forEach(element -> news.add(new NewsDto(
                String.valueOf(element.get("news_id")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("image")),
                String.valueOf(element.get("category")),
                String.valueOf(element.get("published"))
        )));
        return news;
    }

    @Override
    public void addNewsToDB(List<News> list) {
        String sql = "INSERT INTO news VALUES (?,?,?,?,?,?,?,?)";
        for (News news : list) {
            try {
                jdbcTemplate.update(sql, news.getId(), news.getTitle(), news.getDescription(), news.getUrl(), news.getAuthor(), news.getImage(),  news.getCategory().get(0), news.getPublished()  );
            }
            catch (RuntimeException e){
                System.out.println("already exist");
            }

        }
    }

    @Override
    public NewsDto getNewsById(String id) {
        String sql= "SELECT * FROM news WHERE news_id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, row) -> new NewsDto(
                rs.getString("news_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("author"),
                rs.getString("image"),
                rs.getString("category"),
                rs.getString("published")),
                id);
    }
}
