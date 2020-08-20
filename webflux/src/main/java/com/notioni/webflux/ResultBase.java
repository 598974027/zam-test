package com.notioni.webflux;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultBase {

    private String code;

    private String message;

    static public ResultBase OK() {
        return new ResultBase("200", "ok");
    }

    static public ResultBase ERROR() {
        return new ResultBase("500", "error");
    }

}
