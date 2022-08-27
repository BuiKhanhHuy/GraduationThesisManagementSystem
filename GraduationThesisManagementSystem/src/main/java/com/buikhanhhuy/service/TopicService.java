package com.buikhanhhuy.service;

import com.buikhanhhuy.pojo.Topic;

import java.util.List;
import java.util.Map;

public interface TopicService {
    public boolean checkUniqueTopicName(String topicName);
    public List<Object[]> getTopicOptions();
    public List<Topic> getTopics(Map<String, String> params);
    public long countTopic(Map<String, String> params);
    public boolean addTopic(Topic topic);
    public Topic getTopicById(int topicId);
    public boolean updateTopic(int topicId, Topic topic);
    public boolean deleteTopic (int topicId);
}
