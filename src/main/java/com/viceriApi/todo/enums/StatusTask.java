package com.viceriApi.todo.enums;

public enum StatusTask {
    INIT("INIT"),
    IN_PROGRESS("IN PROGRESS"),
    PENDING("PENDING"),
    STOP("STOP"),
    EXCLUDE("EXCLUDE"),
    CANCEL("CANCEL"),
    DONE("DONE");

    private final String status;

    StatusTask(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
