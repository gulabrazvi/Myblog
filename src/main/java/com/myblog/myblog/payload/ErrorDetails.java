package com.myblog.myblog.payload;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;
    private String description;

    public ErrorDetails(Date date, String message, String description) {
        this.date=date;
        this.message=message;
        this.description=description;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
