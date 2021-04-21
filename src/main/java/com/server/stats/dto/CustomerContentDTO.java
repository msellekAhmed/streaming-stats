package com.server.stats.dto;

import java.util.Objects;


/**
 * Data Transfer Object User to Group Customer, Content Pair for groupping and aggregation purgpose.
 * Overrides equals and hashcode methods to be used in Collectors.groupping
 */
public class CustomerContentDTO {

    private String customer;

    private String content;

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


    public CustomerContentDTO(String customer, String content) {
        this.customer = customer;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerContentDTO)) return false;
        CustomerContentDTO that = (CustomerContentDTO) o;
        return Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getContent());
    }

    @Override
    public String toString() {
        return "CustomerContentDTO{" +
                "customer='" + customer + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
