package cn.jaylen.codegenerator.common.exception;

/**
 * @Author: Jaylen
 * @Description: 自定义异常
 * @Date: 2018/12/29 15:30
 */
public class CustomException extends RuntimeException {
    private int httpCode;

    public CustomException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
