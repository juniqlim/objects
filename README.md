# Objects(Small Programs)
재사용 될만한 유용한 작은 객체들(프로그램)을 만들어봤습니다.

[워드 커닝햄의 휼륭한 프로그래머가 되는 방법](https://web.archive.org/web/20230517180720/http://agile.egloos.com/2807583)  
Ward's etude for becoming a great programmer, [WriteSmallButUsefulProgramsEveryDay](http://wiki.c2.com/?WriteSmallButUsefulProgramsEveryDay).  
도 참고했습니다.
> 저는 매일 작지만 유용한 프로그램을 작성하는 것을 추천합니다.   
> 누군가가 한 번이라도 똑같거나 더 나은 프로그램을 작성했다는 것은 신경 쓰지 마세요. 그들은 당신이 아닙니다.   
> 자신이 만든 프로그램의 유용성을 느껴야 복잡성 사이에서 균형을 잡을 수 있습니다.

제가 실제로 사용하면서 조금씩 수정하고 있습니다.  
## 객체 목록
* businessday - 영업일 계산
* encryption - 문자열 암호화(AES, RSA)
* jwt - jwe/jws 생성, 검증  
  
자세한 사용법은 각 모듈의 테스트 코드를 참조해주세요.  
## 설치 방법
[jitpack](https://jitpack.io/#juniqlim/objects)를 이용하여 쉽게 설치 가능합니다.  
  
build.gradle
```groovy
repositories {
	maven { url 'https://jitpack.io' }
}

implementation 'io.github.juniqlim:objects:0.0.5' // 전부 사용하기
implementation 'com.github.juniqlim.objects:jwt:0.0.5' // 특정 모듈 사용하기
```
최신 버전은 [릴리즈 목록](https://github.com/juniqlim/objects/releases)에서 확인 가능합니다.  
  
문의사항 있으시다면, [이슈](https://github.com/juniqlim/objects/issues)에 편하게 남겨주세요!