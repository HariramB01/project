package com.eCommerce.UserService.Response;

public class FallBackResponse {

    private Long id;
    private String message;

    // No-argument constructor
    public FallBackResponse() {}

    // Constructor for convenience
    public FallBackResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FallBackResponse{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
