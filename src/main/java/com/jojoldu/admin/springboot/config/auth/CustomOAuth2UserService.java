package com.jojoldu.admin.springboot.config.auth;

import com.jojoldu.admin.springboot.config.auth.dto.SessionUser;
import com.jojoldu.admin.springboot.domain.user.User;
import com.jojoldu.admin.springboot.domain.user.UserRepository;
import com.jojoldu.admin.springboot.web.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/*OAuth2에서 제공되는 기능을 본 프로젝트에서 사용하기 위해 다소 변경을 시켜야할 필요성이있다.
따라서 모든 정보와 세션에 대해 우리가 필요한 부분만을 사용하기 위한 커스텀이 이 페이지에 구현되어 있으며,
일종의 출입국심사 조건이라고 생각하면 이해가 쉬운 부분이다.
* */
@RequiredArgsConstructor
@Service

public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(),
                attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
