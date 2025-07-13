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
- 25.05.31
  - Controller에서 @RequestPart로 MultipartFile이 수신이 안됨 
  - `@PostMapping("/gifticon")
    public ResponseEntity<?> addGifticon(
    @RequestPart(value = "image", required = false) MultipartFile image,
    @RequestPart(value = "couponInfo") GifticonAddDto dto
    ) {
    log.info(image.getName()) --> NullPtrException
    return ResponseEntity.ok(null);
    }`
  - 해결 방법: 프론트에서 파일을 FileReader.readAsDataURL() 방식으로 읽고 있었음
  - 이 방법은 프론트에서 파일(툭히 이미지)를 미리보기할 때 주로 사용하는 방법인데, 파일을 base64로 인코딩함
  - 근데 Spring Boot에서 Multipart를 수신할 때 base64로 인코딩된 건 인식하지 못함
  - 따라서 프론트에서 FileReader로 변환하지 않은 파일을 그대로 전송함 -> `const file = event.target.files[0]; sendFile(file)` 형식
- 25.06.11
  - RequestBody 수신 시 Long/Integer 타입으로 수신 시 `org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type java.lang.Long from Object value ...` 에러 발생
  - `public ResponseEntity<?> deleteGifticon(@RequestBody Long id)`
  - 해결 방법: String 타입으로 수신 or 객체로 수신 
  - `public ResponseEntity<?> deleteGifticon(@RequestBody Test id) `
  - 요청이 { id: coupon.id } 형태로 전달되면서 객체(JSON Object)로 인식되기 때문에, Long이나 Integer 같은 단일 값으로 변환할 수 없어 오류가 발생
  - 추가로, `@RequestBody String id` 형식으로 받을 경우, json 문자열을 그대로 받기 때문에(ex. `"{"id":18}"`) 사실상 dto나 Map으로 수신해야함 
- 25.07.13
  - 트랜잭션 안에서 delete -> insert 순서로 코드가 실행되도록 설계했지만, 실제 쿼리 실행 순서는 insert -> delete 순으로 실행됨
  - 해결 방법: delete 후 EntityManager.flush() 실행
  - 영속성 컨텍스트에 반영된 내용은 flush를 통해 실제 DB에 반영됨
  - 기본적으로 flush는 트랜잭션을 커밋하기 직전 or JPQL 실행(DB에 직접 쿼리 실행) 전에 실행되는데, insert -> update -> delete 순으로 실행됨
  - 때문에 소스 상에서는 delete -> insert 순이더라도 insert -> delete 순으로 내용이 DB에 반영됨
