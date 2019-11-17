package cn.ljtnono.re.security;

import cn.ljtnono.re.util.EncryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 加密器
 * @author ljt
 * @date 2019/11/18
 * @version 1.0
 */
@Component("rePasswordEncoder")
public class RePasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return EncryptUtil.getMd5LowerCase((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(EncryptUtil.getMd5LowerCase((String) rawPassword));
    }
}
