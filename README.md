# FamGift

## trouble shooting
- 24.07.28 
  - local 환경에서 카카오 로그인 중 jwt를 담은 쿠키가 전달되지 않음 
  - 해결 방법: axios 이용해 api 호출 시 withCredentials 옵션을 true로 설정
  - Credentials는 쿠키, Authorization 인증 헤더, TLS Certificates를 내포하는 인증 정보인데, 기본적으로 별도의 withCredentials = true 옵션 없이는 인증 관련 데이터를 (Cross Origin에) 담지 못하게 돼 있음

- 24.10.20_1
  - PrincipalDetails 객체에 @Value로 값이 주입되지 않음
  - 해결 방법: @Value로 값이 주입되는 시점은 컴포넌트 스캔 시점 -> 빈으로 등록돼 있어야 됨 -> 관련 기능 따로 서비스로 빼서 구현'
- 24.10.20_2 -> 참고: [https://developer-ping9.tistory.com/237](https://developer-ping9.tistory.com/237)
  - BaseFilter에서 `if(!cookieMap.containsKey("jwt")) { filterChain.doFilter(request, response);}` 호출 시 조건문은 타지만 그 아래에 있는 코드도 실행됨
  - 해결 방법: `doFilter()` 후 `return;` 등의 코드로 실행을 종료해야 함
  - filterChain.doFilter 호출 시, 다음 순서의 filter의 `doFilterInternal()`을 실행시키는데, 이 때 이전 filter의 동작이 끝나지 않으면 일련의 filter를 모두 다 거치고 Controller까지 호출되고 나서 다시 `doFilter()`가 실행된 filter로 돌아와서 일련의 과정이 다시 실행됨
  - 따라서 중간에 다음 filterChain으로 넘기고 싶으면 `doFilter()` 후 `return;` 등의 코드로 실행을 종료해야 함
- 24.12.09
  - jwt 토큰을 getClaim 메소드를 이용해 claim 값을 문자열로 받아올 때 "" 가 붙어서 나옴 -> `claim values : {sub="famgift", authority="ROLE_USER", name="장덕진", id="3328927548", exp=313360412400}`
  - `Claim` 타입 객체를 `toString()`을 통해 문자열로 만들면 따옴표가 붙음 -> `Claim.asString()` 메소드를 사용해야 문자열로 온전하게 변화됨
- 24.12.16
  - Spring Security의 `mvcMatchers("/api/**").hasAnyRole()`에서 `hasAnyRole()`에는 ROLE_ prefix를 제외한 권한 명이 들어가야함
  - ROLE_ prefix가 포함되면 빌드 시 IllegalArgumentException 발생하며 빌드 실패
  - 하지만 Authentication 생성 시 인자로 사용되는 권한에는 ROLE_ prefix가 포함된 권한 명을 넣어야 함
    - Spring Security에서 그렇게 구현됨
- 25.05.11
  - React에서 버튼 클릭 이번트 함수 설정을 아래와 같이 구현했을 때 렌더링 시점에서 함수가 바로 실행되는 문제 발생
  - `<button onClick={onButtonClick(category)} className="modal-button">{buttonText}</button>`
  - 해결 방법: onClick 핸들러가 onButtonClick 함수를 직접 호출하기 때문에 렌더링 시점에서 함수 실행, `()=>onButtonClick` 형식으로 함수 자체를 전달하는 것오르 해결 가능