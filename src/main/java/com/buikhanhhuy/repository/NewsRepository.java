package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.News;

import java.util.List;

public interface NewsRepository {
    public List<News> getNews();
    public News getNewsWithAuthorById(int newsId);
    public Object[] getNewsById(int newsId);
    public boolean addNews(News news);
    public boolean updateNews(int newsId, News news);
    public boolean deleteNews(int newsId);
}
