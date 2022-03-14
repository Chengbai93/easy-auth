package com.chengbai.auth.inter;


/**
 * @program: easy-auth
 * @description:
 * @author: xuDS
 * @create: 2022-03-10 22:31
 */
public class LoginContext<INPUT_PARAM, USER_INFO,OUTPUT> extends AbsLoginContext<INPUT_PARAM, USER_INFO,OUTPUT>{

    private LoginContext(INPUT_PARAM input_param) {
        super(input_param);
    }

    public static <INPUT_PARAM, USER_INFO,OUTPUT> LoginContext<INPUT_PARAM, USER_INFO,OUTPUT> of(INPUT_PARAM input_param){
        return new LoginContext<INPUT_PARAM, USER_INFO,OUTPUT>(input_param);
    }
}
