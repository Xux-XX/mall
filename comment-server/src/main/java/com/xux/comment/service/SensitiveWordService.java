package com.xux.comment.service;

public interface SensitiveWordService {

    String replaceWith(String text, String target);

    String replace(String text);

    void addWord(String word);

    void deleteWord(String word);

    boolean exists(String word);

    void load();
}
