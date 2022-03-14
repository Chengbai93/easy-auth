package com.chengbai.auth.inter;

/**
 * @program: easy-auth
 * @description:
 * @author: xuDS
 * @create: 2022-03-14 23:17
 */
public interface ILoginContext<INPUT_PARAM, USER_INFO>{

    /**
     *  获取输入值
     * @return
     */
    INPUT_PARAM getInputParam();


    /**
     * 获取中间值
     * @return
     */
    USER_INFO getUserInfo();



}
