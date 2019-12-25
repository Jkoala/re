package cn.ljtnono.re.dto;

import java.io.Serializable;

/**
 * 封装新增博客对象输入参数
 * @author ljt
 * @date 2019/12/11
 * @version 1.0
 */
public class ReBlogSaveDTO implements Validator, Serializable {

    private static final long serialVersionUID = -2747756701272030287L;

    /**
     * dto对象用于自校验的接口
     *
     * @return 校验成功返回true， 校验失败抛出特定异常
     */
    @Override
    public boolean validate() {
        return false;
    }


}
