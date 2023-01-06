package br.com.dillmann.shirotest.login

import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.stereotype.Component

@Component("authorizer")
class LoginRealm : AuthorizingRealm() {

    override fun supports(token: AuthenticationToken?) =
        token is LoginToken

    override fun doGetAuthenticationInfo(token: AuthenticationToken): AuthenticationInfo {
        require(token is LoginToken)
        val payload = token.payload
        return SimpleAuthenticationInfo(payload, payload, "loginRealm")
    }

    override fun doGetAuthorizationInfo(principals: PrincipalCollection) =
        buildAuthorizationInfo(principals)

    fun buildAuthorizationInfo(principals: PrincipalCollection?): AuthorizationInfo {
        val principal = principals?.primaryPrincipal
        val authorizationInfo = SimpleAuthorizationInfo()

        if (principal is LoginDto) {
            authorizationInfo.roles = principal.roles?.toSet() ?: emptySet()
            authorizationInfo.stringPermissions = principal.permissions?.toSet() ?: emptySet()
        }

        return authorizationInfo
    }
}