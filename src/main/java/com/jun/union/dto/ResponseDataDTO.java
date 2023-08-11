package com.jun.union.dto;

import com.jun.union.define.enums.EResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataDTO<T> extends ResponseDefaultDTO {
	private T responseData;

	public ResponseDataDTO(EResultCode eResultCode) {
		this.setResultMessage(eResultCode.getCode());
		this.setResultCode(eResultCode.getMessage());
	}

	public ResponseDataDTO(EResultCode eResultCode, T data) {
		this.setResultMessage(eResultCode.getCode());
		this.setResultCode(eResultCode.getMessage());
		this.setResponseData(data);
	}

}
