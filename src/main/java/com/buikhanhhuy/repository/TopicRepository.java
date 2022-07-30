package com.buikhanhhuy.repository;

import com.buikhanhhuy.pojo.Topic;

import java.util.List;

public interface TopicRepository {
    public List<Topic> getTopics();
    public boolean addTopic(Topic topic);
    public Topic getTopicById(int topicId);
    public boolean updateTopic(int topicId, Topic topic);
    public boolean deleteTopic (int topicId);
}
