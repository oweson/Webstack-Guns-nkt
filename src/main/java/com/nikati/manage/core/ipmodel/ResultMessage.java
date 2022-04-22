package com.nikati.manage.core.ipmodel;

import lombok.Data;

/**
 * @author oweson
 * @date 2022/4/22 22:30
 */

@Data
public class ResultMessage {

    private Integer status;
    private String message;
    private Result result;
}



