package cn.ljtnono.re.enumeration;

/**
 * @author ljt
 * @date 2019/11/4
 * @version 1.0
 */
public enum GlobalErrorEnum {
    /** 系统错误 */
    SYSTEM_ERROR(500, "系统错误"),

    /** 请求method错误 */
    METHOD_ERROR(10000, "请求方式错误"),

    /** 参数格式异常 */
    PARAM_FORMAT_ERROR(10001, "参数格式错误"),

    /** 参数缺失异常  */
    PARAM_MISSING_ERROR(10002, "参数缺失"),

    /** 参数无效 */
    PARAM_INVALID_ERROR(10003, "参数无效");

    /** 错误码 */
    private final Integer errorCode;

    /** 错误信息*/
    private final String errorMsg;

    GlobalErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
