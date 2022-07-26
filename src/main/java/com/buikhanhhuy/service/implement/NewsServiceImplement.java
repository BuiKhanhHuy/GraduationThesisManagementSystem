package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.News;
import com.buikhanhhuy.repository.NewsRepository;
import com.buikhanhhuy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImplement implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getNews() {
        return this.newsRepository.getNews();
    }
}
