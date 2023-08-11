package com.jun.union.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDataDTO<T> extends RequestDefaultDTO {
	private T requestData;

}
