package com.openshift.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Blog implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String title;
    private String text;
    private final Date createdOn = new Date();

    public Blog(String title, String text) {
        this.title = title;
        this.text = text;
    }
    
    public Blog() {
        
    }

    public String getId() {
        return id;
    }

    
    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getText() {
        return text;
    }
    
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return "Blog [id=" + id + ", title=" + title + ", text=" + text + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Blog other = (Blog) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

}
