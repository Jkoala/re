package cn.ljtnono.re.common;

/**
 * 保存了实体类与redis交互的各种参数
 * @author ljt
 * @date 2019/11/10
 * @version 1.0
 */
public class ReEntityRedisCommon {

    /** ReBlog实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_BLOG_KEY = "re_blog:id:author:title:type";

    /** ReBlog实体类分页查询在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_BLOG_PAGE_KEY = "re_blog:page:count";

    /** ReBlogType实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_BLOG_TYPE_KEY = "re_blog_type:id:name";

    /** ReBook实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_BOOK_KEY = "re_book:id:name:author:type";

    /** ReBookType实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_BOOK_TYPE_KEY = "re_book_type:id:name";

    /** ReConfig实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_CONFIG_KEY = "re_config:id:key";

    /** ReImage实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_IMAGE_KEY = "re_image:id:origin_name:type:owner";

    /** ReLink实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_LINK_KEY = "re_link:id:name:type";

    /** ReLinkType实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_LINK_TYPE_KEY = "re_link_type:id:name";

    /** RePermission实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_PERMISSION_KEY = "re_permission:id:res";

    /** ReRole实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_ROLE_KEY = "re_role:id:name";

    /** ReRolePermission实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_ROLE_PERMISSION_KEY = "re_role_permission:id";

    /** ReSkill实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_SKILL_KEY = "re_skill:id:name:owner";

    /** ReTimeLine实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_TIMELINE_KEY = "re_timeline:id";

    /** ReUser实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_USER_KEY = "re_user:id:username:qq:tel";

    /** ReUserRole实体类在redis中存储的键的格式，通过替换相应的值来存储 */
    public static final String RE_USER_ROLE = "re_user_role:id";

}
