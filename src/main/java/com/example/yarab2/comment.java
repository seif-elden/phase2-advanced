package com.example.yarab2;

import java.time.LocalDateTime;
import java.io.Serializable;


public class comment implements Serializable , Comparable<comment> {
    private profile author;
    private String content;
    private LocalDateTime timestamp;

    // Constructor
    public comment(profile author, String content) {
        this.author = author;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public profile getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int compareTo(comment other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        return "comment{" +
                "author=" + author +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}