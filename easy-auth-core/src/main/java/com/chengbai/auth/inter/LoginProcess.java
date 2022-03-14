package com.chengbai.auth.inter;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @program: easy-auth
 * @description:
 * @author: xuDS
 * @create: 2022-03-10 21:20
 */
public interface LoginProcess <INPUT_PARAM, USER_INFO,OUTPUT>{

    /**
     * 校验
     *
     * @param consumer
     * @return
     */
    LoginProcess<INPUT_PARAM, USER_INFO,OUTPUT> valid(Consumer<INPUT_PARAM> consumer);

    /**
     * 查询
     *      到这步应该valid不再添加 status=1
     * @param function
     */
    void queryUserInfo(Function<INPUT_PARAM, USER_INFO> function);

    /**
     * 后置校验
     *      状态应为查询之后
     * @param consumer
     */
    void postValid(BiConsumer<INPUT_PARAM, USER_INFO> consumer);

    /**
     * 最终查询
     *
     * @param function
     * @return
     */
    Optional<OUTPUT> createToken(Function<ILoginContext<INPUT_PARAM, USER_INFO>, OUTPUT> function);
}
