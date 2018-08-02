package com.lotus.admin.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/beetl");
        viewResolver.setSuffix(".html");

        UserController controller = new UserController();
        mockMvc = standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/list"));
    }

    //@Test
    public void testPage() throws Exception {
        mockMvc.perform(post("/user/page"))
                .andExpect(status().isOk());
    }

    @Test
    public void testTmp(){
        String shareId = "12-5";
        Integer articleId = 0;
        Integer isShare = 0;
        if(shareId.contains("-")){
            articleId = Integer.parseInt(shareId.substring(0, shareId.indexOf("-")));
            isShare = Integer.parseInt(shareId.substring(1 + shareId.indexOf("-")));
        }else{
            articleId = Integer.parseInt(shareId);
        }
        System.out.println(articleId);
        System.out.println(isShare);
    }
}
