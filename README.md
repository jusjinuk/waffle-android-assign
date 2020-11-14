# waffle-android-assign

### 1주차 과제 TicTacToe 관련 주석
---
#### 개요
   * PLAYING... 대신 누구의 턴인지 뜨도록 UI를 설정해놓았습니다.
   * Player O부터 시작하도록 만들었습니다.

### 2주차 과제 SimpleTodo 관련 주석
---
#### 개요
  * 과제 명세 만족하도록 작성하였습니다.
  * 수고해주셔서 감사드립니다.

### 3주차 과제 Moviedb 관련 주석
---
#### 개요
  * 과제 명세 만족하도록 작성하였습니다.
  * 추가 과제 중에는 Infinite scroll을 구현하였습니다.
#### 질문/궁금한 점
  * ```NetworkModule.kt``` 관련 간단한 질문사항
  ```
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val apiKeyInterceptor = object: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("api_key", BuildConfig.TMDB_API_KEY).build()
            return chain.proceed(request.newBuilder().url(url).build())
        }
    }

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()
  ```
  
  skeleton code의 이 부분을 보면 개발 상태일 때만 ```api_key```를 query param으로 더해서 HTTP request를 보내는 것 같은데 혹시 그렇게 주신 이유가 따로 있을까요? 개인이 받은 ```api_key```를 배포할 때 활용하면 안 되어서 그런 것인가요?
  * Rxjava/Coroutine 사용 관련
  
  이번 과제 관련하여 공부하다보니 Rxjava로 구현하는 비동기 처리를 Coroutine으로도 비슷하게 구현할 수 있는 것 같은데 Retrofit의 경우에는 Rxjava를 이용한 패턴이 더 간단해서 사용하신 건가요? Coroutine을 이용하는 방식도 실무나 현장에서 많이 사용되는지 궁금합니다.
  
#### 마무리
  * 세미나 진행하느라 수고해주셔서 정말 감사드립니다.
  * MVVM, Repository pattern에 따라 코드를 작성하고자 노력은 하였는데 새로운 내용을 공부하여 넣다보니 헷갈리는 부분도 있었어서 혹시 더 나은 방향으로 설계할 수 있는 부분이 있다면 ```comment``` 부탁드릴게요! 감사합니다~!!
