package com.unitedremote.codingchallenge.shopsservice.util;


import java.util.Objects;

/**
 * this class represent the response payload
 */
public class RestResponse {
	private String status;
	private int code;
	private String userMessage;
	private String internalMessage;

	public RestResponse(String status, int code, String userMessage, String internalMessage) {
		this.status = status;
		this.code = code;
		this.userMessage = userMessage;
		this.internalMessage = internalMessage;
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

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getInternalMessage() {
		return internalMessage;
	}

	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
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
                Objects.equals(userMessage, that.userMessage) &&
				Objects.equals(internalMessage, that.internalMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code, userMessage, internalMessage);
    }
}
