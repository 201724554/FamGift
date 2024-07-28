# FamGift

## trouble shooting
- 24.07.28 
  - local 환경에서 카카오 로그인 중 jwt를 담은 쿠키가 전달되지 않음 
  - 해결 방법: axios 이용해 api 호출 시 withCredentials 옵션을 true로 설정
  - Credentials는 쿠키, Authorization 인증 헤더, TLS Certificates를 내포하는 인증 정보인데, 기본적으로 별도의 withCredentials = true 옵션 없이는 인증 관련 데이터를 (Cross Origin에) 담지 못하게 돼 있음