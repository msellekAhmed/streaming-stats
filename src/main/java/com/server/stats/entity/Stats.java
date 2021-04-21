package com.server.stats.entity;


import javax.persistence.*;
import java.util.Date;


/**
 * JPA Entity Used to represent the stats to be stored in the database,
 * Mapping postgres tale and Entity object is done automatically via spring data JPA
 */
@Entity
@Table(name = "stats")
public class Stats {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "time")
    private Date time;

    @Column(name = "customer")
    private String customer;

    @Column(name = "content")
    private String content;

    @Column(name = "cdn")
    private int cdn;

    @Column(name = "p2p")
    private int p2p;

    @Column(name = "sessions", nullable = true)
    private int sessions;

    public Stats() {
    }

    public Stats(Date time, String customer, String content, int cdn, int p2p, int sessions) {
        this.time = time;
        this.customer = customer;
        this.content = content;
        this.cdn = cdn;
        this.p2p = p2p;
        this.sessions = sessions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) { this.customer = customer; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCdn() {
        return cdn;
    }

    public void setCdn(int cdn) {
        this.cdn = cdn;
    }

    public int getP2p() {
        return p2p;
    }

    public void setP2p(int p2p) {
        this.p2p = p2p;
    }

    public int getSessions() {
        return sessions;
    }

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", time=" + time +
                ", customer='" + customer + '\'' +
                ", content='" + content + '\'' +
                ", cdn=" + cdn +
                ", p2p=" + p2p +
                ", sessions=" + sessions +
                '}';
    }



}
