package com.chengbai.auth.inter;


import lombok.Data;
import org.junit.jupiter.api.Test;


public class LoginContextTest {


    @Test
    public void ofTest(){
        RequestParam requestParam = new RequestParam();
        requestParam.setUsername("test01");
        LoginContext<RequestParam, UserInfo, ResultVO> loginContext = LoginContext.of(requestParam);
        loginContext.valid(rq-> System.out.println(rq.getUsername()))
                .valid(request-> {
                    if (!request.getUsername().equals("test")){
                        throw new RuntimeException();
                    }
                })
                .queryUserInfo(r -> new UserInfo());
        final Object token = loginContext.createToken(lc -> {
            System.out.println();
            return new ResultVO();
        });
        System.out.println(token);
    }

}

@Data
class RequestParam{

    private String username;

}


class UserInfo{

}

class ResultVO{

}