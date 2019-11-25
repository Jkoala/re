package cn.ljtnono.re.enumeration;

/**
 * 全局变量
 * @author ljt
 * @date 2019/11/23
 * @version 1.0
 */
public enum GlobalVariableEnum {

    /** 博客默认图片的url地址 */
    RE_IMAGE_DEFAULT_URL("https://www.ljtnono.cn/re/images/default_img.jpg");

    private Object value;

    public Object getValue() {
        return value;
    }

    GlobalVariableEnum(Object value) {
        this.value = value;
    }
}
