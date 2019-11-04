package cn.ljtnono.re.enumeration;

/**
 * @author ljt
 * @date 2019/11/4
 * @version 1.0
 */
public enum GlobalErrorEnum {
    /** 系统错误 */
    SYSTEM_ERROR("500", "系统错误");

    /** 错误码 */
    private final String errorCode;

    /** 错误信息*/
    private final String errorMsg;

    GlobalErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
