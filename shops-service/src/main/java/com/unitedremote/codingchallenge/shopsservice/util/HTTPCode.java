package com.unitedremote.codingchallenge.shopsservice.util;

/**
 * this util class contain pairs of exception's code and corresponding messages.
 */
public final class HTTPCode {
	public static final Pair<Integer, String> OK = new Pair<>(200, "OK");
	public static final Pair<Integer, String> CREATED = new Pair<>(201, "Created");
	public static final Pair<Integer, String> NOT_MODIFIED = new Pair<>(304, "Not modified");
	public static final Pair<Integer, String> BAD_REQUEST = new Pair<>(400, "Bad request");
	public static final Pair<Integer, String> UNAUTHORIZED = new Pair<>(401, "Unauthorized");
	public static final Pair<Integer, String> FORBIDDEN = new Pair<>(403, "Forbidden");
	public static final Pair<Integer, String> NOT_FOUND = new Pair<>(404, "Not found");
	public static final Pair<Integer, String> INTERNAL_SERVER_ERROR = new Pair<>(500, "Internal Server Error");

	// restrict instantiation
	private HTTPCode() {}
}