package com.unitedremote.codingchallenge.shopsservice.util;


import java.util.Objects;

/**
 * this class represent the response payload
 */
public class RestResponse {
	private String status;
	private int code;
	private String message;

	public RestResponse() {}

	public RestResponse(String status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	private void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	private void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatusCode(Pair<Integer, String> pair) {
		this.setCode(pair.getKey());
		this.setStatus(pair.getValue());
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResponse that = (RestResponse) o;
        return code == that.code &&
                Objects.equals(status, that.status) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code, message);
    }
}
