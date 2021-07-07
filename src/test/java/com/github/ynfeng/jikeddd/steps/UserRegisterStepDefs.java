package com.github.ynfeng.jikeddd.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ynfeng.jikeddd.application.UserRegisterRequest;
import io.cucumber.java.Before;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import io.cucumber.spring.CucumberContextConfiguration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserRegisterStepDefs {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataSource dataSource;

    private ResultActions result;

    @Before
    public void setup() throws SQLException {
        truncateTables();
    }

    private void truncateTables() throws SQLException {
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();

        try {
            stmt.execute("truncate user");
        } finally {
            stmt.close();
            conn.close();
        }
    }

    @当("使用 {string} 注册")
    public void 使用_用户名_注册(String username) throws Exception {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername(username);

        result = mockMvc.perform(
            post("/users")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

    }

    @那么("注册成功")
    public void 注册成功() throws Exception {
        result.andDo(print()).andExpect(status().isOk());
    }

    @那么("注册失败")
    public void 注册失败() throws Exception {
        result.andDo(print()).andExpect(status().is4xxClientError());
    }

    @假如("{string} 已经被注册")
    public void 用户已经被注册(String username) throws Exception {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername(username);

        mockMvc.perform(
            post("/users")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isOk());
    }
}
