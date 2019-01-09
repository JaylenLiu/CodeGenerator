package cn.jaylen.codegenerator.common.exception;

/**
 * @Author: Jaylen
 * @Description: http状态码
 * @Date: 2018/12/29 15:46
 */
public enum  HttpCodeEnum {
    HTTP_200(200, "请求成功"),
    HTTP_404(404, "请求资源不存在"),
    HTTP_500(500, "服务器错误");
    private Integer code;
    private String msg;

    HttpCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
