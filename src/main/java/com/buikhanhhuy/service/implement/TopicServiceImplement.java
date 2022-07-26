package com.buikhanhhuy.service.implement;

import com.buikhanhhuy.pojo.Topic;
import com.buikhanhhuy.repository.TopicRepository;
import com.buikhanhhuy.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImplement implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> getTopics() {
        return this.topicRepository.getTopics();
    }
}
