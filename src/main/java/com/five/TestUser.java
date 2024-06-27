package com.five;

/**
 * @author huangfukexing
 * @date 2024/4/8 14:59
 */
public class TestUser {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
