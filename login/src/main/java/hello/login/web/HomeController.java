package hello.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;
	
//    @GetMapping("/")
    public String home() {
        return "home";
    }
    // required false는 로그인 안한 사용가조 들어와야하기떄문
    @GetMapping("")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
    	
    	if(memberId == null) {
    		return "home";
    	}
    	
    	//로그인
    	Member loginMember = memberRepository.findById(memberId);
    	if(loginMember == null) {
    		return "home";
    	}
    	
    	model.addAttribute("member", loginMember);
    	return "loginHome";
    }
}