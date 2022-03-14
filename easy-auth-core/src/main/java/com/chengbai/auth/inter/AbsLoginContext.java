package com.chengbai.auth.inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @program: easy-auth
 * @description:
 * @author: xuDS
 * @create: 2022-03-10 21:39
 */
public abstract class AbsLoginContext<INPUT_PARAM, USER_INFO, OUT_PUT>
        implements ILoginContext<INPUT_PARAM, USER_INFO>,
        LoginProcess<INPUT_PARAM, USER_INFO, OUT_PUT>{

    private INPUT_PARAM input_param;

    private USER_INFO userInfo;

    private int status = 0;

    private List<Consumer<INPUT_PARAM>> valids = new ArrayList<>();
    private List<BiConsumer<INPUT_PARAM, USER_INFO>> posts = new ArrayList<>();

    private Function<INPUT_PARAM, USER_INFO> queryUserFunc;

    public AbsLoginContext(INPUT_PARAM input_param) {
        this.input_param = input_param;
    }

    @Override
    public LoginProcess<INPUT_PARAM, USER_INFO, OUT_PUT> valid(Consumer<INPUT_PARAM> consumer) {
        valids.add(consumer);
        return this;
    }

    @Override
    public void postValid(BiConsumer<INPUT_PARAM, USER_INFO> consumer) {
        posts.add(consumer);
    }

    @Override
    public void queryUserInfo(Function<INPUT_PARAM, USER_INFO> function) {
        this.queryUserFunc = function;
    }

    @Override
    public Optional<OUT_PUT> createToken(Function<ILoginContext<INPUT_PARAM, USER_INFO>, OUT_PUT> function) {
        final Spliterator<Consumer<INPUT_PARAM>> spliterator = valids.stream().spliterator();
        spliterator.forEachRemaining(consumer -> consumer.accept(this.input_param));
        if (function != null){
            this.userInfo = queryUserFunc.apply(this.input_param);
        }
        final Spliterator<BiConsumer<INPUT_PARAM, USER_INFO>> post = posts.stream().spliterator();
        post.forEachRemaining(consumer -> consumer.accept(this.input_param, this.userInfo));
        if (function == null){
            throw new RuntimeException("CREATE METHOD CANT NULL");
        }
        return Optional.ofNullable(function.apply(this));
    }





    @Override
    public INPUT_PARAM getInputParam() {
        return this.input_param;
    }


    @Override
    public USER_INFO getUserInfo() {
        return this.userInfo;
    }
}
