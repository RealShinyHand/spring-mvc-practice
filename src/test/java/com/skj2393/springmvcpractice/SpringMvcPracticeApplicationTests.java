package com.skj2393.springmvcpractice;

import com.skj2393.springmvcpractice.controller.constant.ErrorCode;
import com.skj2393.springmvcpractice.dto.APIDataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SpringMvcPracticeApplicationTests {

	@DisplayName("문자열 데이터가 주어지면,표준 성공 응답을 생성")
	@Test
	void givenStringData_whenCreatingResponse_thenReturnSuccessfulResponse(){
		//given
		String data = "test Data";
		//when
		APIDataResponse<String> response = APIDataResponse.of(data);
		//Then
		assertThat(response)
				.isNotNull()
				.hasFieldOrPropertyWithValue("success",true)
				.hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode())
				.hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
				.hasFieldOrPropertyWithValue("data",data);
	}

	@DisplayName("데이터가 없음,데이터 없는 표준 성공 응답")
	@Test
	void givenNothing_whenCreatingResponse_thenReturnEmptySuccessfulResponse(){
		//given

		//when
		APIDataResponse<String> response = APIDataResponse.empty();
		//Then
		assertThat(response)
				.isNotNull()
				.hasFieldOrPropertyWithValue("success",true)
				.hasFieldOrPropertyWithValue("errorCode",ErrorCode.OK.getCode())
				.hasFieldOrPropertyWithValue("message",ErrorCode.OK.getMessage())
				.hasFieldOrPropertyWithValue("data",null);
	}

}
