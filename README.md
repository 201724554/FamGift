# FamGift

## trouble shooting
- 24.07.28 
  - local 환경에서 카카오 로그인 중 jwt를 담은 쿠키가 전달되지 않음 
  - 해결 방법: axios 이용해 api 호출 시 withCredentials 옵션을 true로 설정