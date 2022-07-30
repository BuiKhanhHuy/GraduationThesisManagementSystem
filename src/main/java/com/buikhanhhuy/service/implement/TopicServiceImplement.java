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

    @Override
    public boolean addTopic(Topic topic) {
        return this.topicRepository.addTopic(topic);
    }

    @Override
    public Topic getTopicById(int topicId) {
        return this.topicRepository.getTopicById(topicId);
    }

    @Override
    public boolean updateTopic(int topicId, Topic topic) {
        return this.topicRepository.updateTopic(topicId, topic);
    }

    @Override
    public boolean deleteTopic(int topicId) {
        return this.topicRepository.deleteTopic(topicId);
    }
}
