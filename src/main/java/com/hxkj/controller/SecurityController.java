package com.hxkj.controller;

import com.hxkj.model.HttpResult;
import com.hxkj.security.JwtContainer;
import com.hxkj.security.JwtSign;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dsd
 * @version 2018/6/21 9:43
 */
@RestController
@RequestMapping("/security")
public class SecurityController extends BaseController {

    @GetMapping("/logout")
    public HttpResult logout(HttpServletRequest req, HttpResult httpResult) {
        Claims claims = getClaims(req);
        JwtContainer.loggedInUsers.remove(claims.getSubject());
        return httpResult.setStatusCode(HttpResult.SUCCESS);
    }

    Claims getClaims(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        logger.debug(token);
        Claims claims = null;
        if (token != null) {
            claims = Jwts.parser()
                    .setSigningKey(JwtSign.SIGNATURE)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        }
        return claims;
    }
}
