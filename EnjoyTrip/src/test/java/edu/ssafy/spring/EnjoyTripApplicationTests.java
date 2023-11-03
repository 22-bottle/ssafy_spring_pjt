package edu.ssafy.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import edu.ssafy.spring.board.model.mapper.BoardMapper;
import edu.ssafy.spring.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(
		properties = {
				"tmp=tmp",
		},
		classes = {UserController.class}
)
@Slf4j
class EnjoyTripApplicationTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Value("${tmp}")
	private String TEMP;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test // listArticle test
	public void test1() throws Exception {
		mockMvc.perform(get("/list"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andDo(print());
	}

}
