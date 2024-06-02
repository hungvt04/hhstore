package com.datn.be.infrastructure.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject {

    private HttpStatus status;

    private Object data;

    private List<String> message;

    public <T> ResponseObject(T obj) {
        processResponseObject(obj, HttpStatus.OK);
    }

    public <T> ResponseObject(T obj, HttpStatus status) {
        processResponseObject(obj, status);
    }

    public ResponseObject(List<String> message, HttpStatus status) {
        processResponseMessage(message, status);
    }

    public void processResponseObject(Object obj, HttpStatus status) {
        if (obj != null) {
            this.data = obj;
            this.status = status;
        }
    }

    public void processResponseMessage(List<String> message, HttpStatus status) {
        if (message != null) {
            this.message = message;
            this.status = status;
        }
    }

}
