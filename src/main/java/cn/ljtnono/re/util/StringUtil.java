package cn.ljtnono.re.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  字符串处理工具
 *  @author ljt
 *  @date 2018/12/16
 *  @version 1.0
*/
public class StringUtil {

    /**
     * 防止工具类进行实例化
     */
    private StringUtil() {}

    /**
     * 删除字符串中的html标签，只保留内容部分
     * @param htmlStr 带有html标签的字符串
     * @return 不含html标签的字符串
     */
    public static String delHtmlTagFromStr(String htmlStr){
        //定义script的正则表达式
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式
        String regEx_html="<[^>]+>";
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        //过滤script标签
        htmlStr=m_script.replaceAll("");
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        //过滤style标签
        htmlStr=m_style.replaceAll("");
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        //过滤html标签
        htmlStr=m_html.replaceAll("");
        //返回文本字符串
        return htmlStr.trim();
    }
    public static void main(String[] args) {
        String str = "<div style='text-align:center;'> 整治“四风”  清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";
        System.out.println(delHtmlTagFromStr(str));
    }
}
