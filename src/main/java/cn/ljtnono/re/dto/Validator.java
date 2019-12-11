package cn.ljtnono.re.dto;

/**
 * dto对象自校验
 * @author ljt
 * @date 2019/12/11
 * @version 1.0
 */
public interface Validator {

    /**
     * dto对象用于自校验的接口
     * @return 校验成功返回true， 校验失败抛出特定异常
     */
    boolean validate();
}
