# 목표
> 다른 OAuth Provider가 들어오게 되어도 추가가 용이하게 만들 것.
 
# 개발 과정에서 어려웠던 부분
> Naver API 공식문서가 너무 불친절 했다. </br>
> Naver API 사용중 Token을 얻는 작업 도중에 Exception이 터졌는데 Response를 HTML 코드로 날려주고 한글은 다 깨져있어 에러 추적이 힘들었다.

# 얻은 것
> 1. @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
> > JsonProperty로 직접 넣어주는 형태를 사용하였지만 이 코드를 통해 더욱 편한 작업이 가능해질 것 같다.
> 2. RestClient
> > 사용법이 간단하여 코드를 보는 것 만으로도 충분히 이해가 가능하며 편하게 작업이 가능했다. 
> 3. OAuth 로그인의 간단한 구현