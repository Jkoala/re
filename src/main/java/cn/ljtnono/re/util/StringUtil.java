package cn.ljtnono.re.util;

/**
 *  字符串处理工具
 *  @author ljt
 *  @date 2019/10/13
 *  @version 1.1
*/
public class StringUtil {

    /**
     * 防止工具类进行实例化
     */
    private StringUtil() {}


    /**
     * 判断字符串是否为空
     *
     * @param str 判断的字符串参数
     * @return 如果字符串为null或者空串，返回true，否者返回false
     */
    public static boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 判断字符串是否为空格或者空串
     *
     * @param str 判断的字符串参数
     * @return 如果字符串全是空格或者空串或者null，返回true，否者返回false
     */
    public static boolean isBlank(final String str) {
        return isEmpty(str.trim());
    }




}
