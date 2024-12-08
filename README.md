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
  - 즉, 중간에 `doFilter()`가 실행된 이후의 과정이 2번 실행됨
  - ex. A filter -> B filter -> C filter -> Controller 인테 B filter 가은데어 `doFilter()`가 코드 중간에서 `return`없이 실행되면 A -> B -> C -> Controller가 실행되고 다시 B 나머지 부분 -> C -> Controller 실행됨