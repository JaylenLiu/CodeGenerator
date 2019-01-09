package cn.jaylen.codegenerator.common;

/**
 * @author ljl
 * @create 2018-06-26 16:09
 * @desc 数据返回对象,
 * 200: 成功
 * 500：错误
 * 600：系统未登录
 **/
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int gethttpCode() {
        return httpCode;
    }

    public void sethttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", httpCode=" + httpCode +
                ", data=" + data +
                ", pages=" + pages +
                '}';
    }
}
