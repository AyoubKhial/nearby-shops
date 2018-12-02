package com.unitedremote.codingchallenge.shopsservice.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

/**
 * This class is used to get the JWT token from the request, validate it, load the user associated with the token,
 * Spring security provides an annotation called <code>@AuthenticationPrincipal</code> to access the currently
 * authenticated user in the controllers.
 * <p>
 * We’ve created a meta-annotation so that we don’t get too much tied up of with Spring Security related annotations
 * everywhere in our project. This reduces the dependency on Spring Security. So if we decide to remove Spring Security
 * from our project, we can easily do it by simply changing the CurrentUser annotation.
 * @author Ayoub Khial
 * @see AuthenticationPrincipal
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}