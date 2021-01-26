package com.diplom2.models;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long links_id;

    private String long_link, short_link;

    public Long getLinks_id() {
        return links_id;
    }

    public void setLinks_id(Long links_id) {
        this.links_id = links_id;
    }

    public String getLong_link() {
        return long_link;
    }

    public void setLong_link(String long_link) {
        this.long_link = long_link;
    }

    public String getShort_link() {
        return short_link;
    }

    public void setShort_link(String short_link) {
        this.short_link = short_link;
    }

    public Links(String long_link, String short_link) {
        this.long_link = long_link;
        this.short_link = short_link;
    }

    public Links() {

    }

}
