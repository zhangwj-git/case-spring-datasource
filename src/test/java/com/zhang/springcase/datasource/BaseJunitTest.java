package com.zhang.springcase.datasource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback()
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringCaseApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class BaseJunitTest {
	
	@Autowired
	private MockMvc mockMvc;


	/**
	 * 发送无参GET请求
	 * @param apiURL 请求URI
	 * @return MockHttpServletResponse
	 * @throws Exception 异常信息
	 */
	protected MockHttpServletResponse httpGetWithOutParam(String apiURL) throws Exception{
		ResultActions actions = mockMvc.perform(get(apiURL)).andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}

    /**
     * 键值对参数发送GET请求
     * @param paramMap 键值对参数
     * @param apiURL 请求URI
     * @return HttpServletResponse
     * @throws Exception 异常信息
     */
	protected MockHttpServletResponse httpGetWithMap(Map<String, Object> paramMap,String apiURL) throws Exception{
		StringBuilder URI = new StringBuilder();
		URI.append(apiURL).append("?");
		for (Map.Entry<String, Object> pm : paramMap.entrySet()) {
			URI.append(pm.getKey()).append("=").append(pm.getValue()).append("&");
		}
		URI.deleteCharAt(URI.length() - 1);
		ResultActions actions = mockMvc.perform(get(URI.toString())).andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}

    /**
     * JSON串参数发送GET请求
     * @param jsonStr json串参数
     * @param apiURL URI
     * @return HttpServletResponse
     * @throws Exception 异常
     */
	protected MockHttpServletResponse httpGetWithJsonStr(String jsonStr,String apiURL) throws Exception{
		ResultActions actions = mockMvc.perform(
				get(apiURL).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
				.andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}

    /**
     * 发送无参POST请求
     * @param apiURL URI
     * @return HttpServletResponse
     * @throws Exception http请求异常
     */
	protected MockHttpServletResponse httpPostWithOutParam(String apiURL) throws Exception{
		ResultActions actions = mockMvc.perform(
				post(apiURL).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}

    /**
     * 键值对参数发送POST请求
     * @param paramMap 键值对参数
     * @param apiURL URI
     * @return HttpServletResponse
     * @throws Exception http请求异常
     */
	protected MockHttpServletResponse httpPostWithMap(Map<String, Object> paramMap,String apiURL) throws Exception{
		StringBuilder URI = new StringBuilder();
		URI.append(apiURL).append("?");
		for (Map.Entry<String, Object> pm : paramMap.entrySet()) {
			URI.append(pm.getKey()).append("=").append(pm.getValue()).append("&");
		}
		URI.deleteCharAt(URI.length() - 1);
		ResultActions actions = mockMvc.perform(post(URI.toString())).andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}

    /**
     * JSON串参数发送POST请求
     * @param jsonStr json串参数
     * @param apiURL URI
     * @return HttpServletResponse
     * @throws Exception http请求异常
     */
	protected MockHttpServletResponse httpPostWithJsonStr(String jsonStr,String apiURL) throws Exception{
		ResultActions actions = mockMvc.perform(
				post(apiURL).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
				.andExpect(status().isOk()).andDo(print());
		return actions.andReturn().getResponse();
	}
}
