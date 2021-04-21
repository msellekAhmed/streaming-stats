package com.server.stats.dto;



/**
 * Data Transfer Object Used to represent the json payloads received from different divices,
 * Used also to construct Stats Object to be inserted in the database
 */
public class StatsDTO {


    private String token;

    private String customer;

    private String content;

    private int timespan;

    private int cdn;

    private int p2p;

    private int sessionDuration;

    public StatsDTO(String token, String customer, String content, int timespan, int cdn, int p2p, int sessionDuration) {
        this.token = token;
        this.timespan = timespan;
        this.customer = customer;
        this.content = content;
        this.cdn = cdn;
        this.p2p = p2p;
        this.sessionDuration = sessionDuration;
    }


    public StatsDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTimespan() {
        return timespan;
    }

    public void setTimespan(int timespan) {
        this.timespan = timespan;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

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

    public int getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(int sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    @Override
    public String toString() {
        return "StatsDTO{" +
                "token='" + token + '\'' +
                ", customer='" + customer + '\'' +
                ", content='" + content + '\'' +
                ", timespan=" + timespan +
                ", cdn=" + cdn +
                ", p2p=" + p2p +
                ", sessionDuration=" + sessionDuration +
                '}';
    }

}
