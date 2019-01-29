package cn.jaylen.codegenerator.common;

import lombok.Data;

/**
 * @author ljl
 * @create 2018-06-26 16:09
 * @desc 数据返回对象,
 **/
@Data
public class Message<T> {
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    private String message;
    private int httpCode;
    private T data;
    private int pages;

    private Message(T data) {
        this.data = data;
        this.httpCode = 200;
        this.message = SUCCESS;
    }

    private Message(String message, int httpCode, T data) {
        this.message = message;
        this.httpCode = httpCode;
        this.data = data;
    }

    public static <T> Message successMessage(T obj){
        return new Message<T>(obj);
    }

    public static Message errorMessage(int httpCode){
        return new Message(ERROR, httpCode, null);
    }

    public static Message errorMessage(int httpCode, String message){
        return new Message(message, httpCode, null);
    }

    public static Message nullParamsMessage(){
        return  new Message("参数为空！",500, null);
    }
}
