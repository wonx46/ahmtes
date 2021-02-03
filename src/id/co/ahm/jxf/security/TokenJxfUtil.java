/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.vo.VoJxfUserCred;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author achmad.ha
 */
@Component(value = "tokenJxfUtil")
public class TokenJxfUtil {

    public Map<String, String> generateToken(VoJxfUserCred voJxfUserCred) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            Key key = JwtKey.generateDefaultKey();
            Map<String, Object> userCred = new HashMap<String, Object>();
            userCred.put("username", voJxfUserCred.getUsername());
            userCred.put("email", voJxfUserCred.getEmail());
            userCred.put("fullname", voJxfUserCred.getFullname());
            
            String compactJws = Jwts.builder()
                    .setClaims(userCred)
                    .setSubject("JXF")
                    .setIssuedAt(new DateTime().toDate())
                    .setId(id)
                    //                    .setExpiration(new DateTime().plusMinutes(20).toDate())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            String jxid = CryptoSecurity.encrypt(compactJws);
            result.put(CommonConstant.JXID, jxid);
            result.put(CommonConstant.TKID, id);
        } catch (Exception ex) {
            Logger.getLogger(TokenJxfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public VoJxfUserCred getUserCred(String token) {
        VoJxfUserCred voJxfUserCred = null;
        Jws<Claims> claims = extractToken(token);
        if (claims != null) {
            voJxfUserCred = new VoJxfUserCred();
            voJxfUserCred.setUsername((String) claims.getBody().get("username"));
            voJxfUserCred.setEmail((String) claims.getBody().get("email"));
            voJxfUserCred.setFullname((String) claims.getBody().get("fullname"));
        }
        return voJxfUserCred;
    }

    public boolean validateToken(String token, String id) {
        Jws<Claims> claims = extractToken(token);
        if (claims != null) {
            if (claims.getBody().getId().equals(id)) {
                long now = new DateTime().getMillis();
                long issuedAt = claims.getBody().getIssuedAt().getTime();
                long delta = now - issuedAt;
                if (delta <= 1200000) {
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public boolean validateTokenForHtml(String token, String id) {
        Jws<Claims> claims = extractToken(token);
        if (claims != null) {
            if (claims.getBody().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private Jws<Claims> extractToken(String token) {
        if (token != null) {
            if (!token.isEmpty()) {
                try {
                    Key key = JwtKey.generateDefaultKey();
                    String tokenJwt = CryptoSecurity.decrypt(token);
                    try {
                        Jwts.parser().setSigningKey(key).parseClaimsJws(tokenJwt);
                        Jws<Claims> claims = Jwts.parser()
                                .requireSubject("JXF")
                                .setSigningKey(key)
                                .parseClaimsJws(tokenJwt);
                        return claims;
                    } catch (SignatureException e) {
                        Logger.getLogger(TokenJxfUtil.class.getName()).log(Level.SEVERE, null, e);
                        //don't trust the JWT!
                    } catch (MissingClaimException e) {
                        Logger.getLogger(TokenJxfUtil.class.getName()).log(Level.SEVERE, null, e);
                        // we get here if the required claim is not present
                    } catch (IncorrectClaimException e) {
                        Logger.getLogger(TokenJxfUtil.class.getName()).log(Level.SEVERE, null, e);
                        // we get here if ther required claim has the wrong value
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TokenJxfUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

}
