# crawlingViaSelenium

Seminar_001.

프리모아(프리랜서 개발자 구직사이트) 크롤링 해보기

-환경
1. IDE : 스프링부트
2. Lib : 셀레니움

-목표
1. 페이지에 접속해서 로그인을 하고(O)
2. 프로젝트 페이지를 넘어가며(O)
3. 각 프로젝트 정보들을 가져오기(X)

-분석
: 3번이 실패한 이유

-> 정확히 어떤 요소를 가져와서 클릭해야 페이지가 넘어가는지를 파악하지 못했다.
-> 전체 div를 선택해보거나, 제목만 선택해보거나, 내용만 선택해보거나, 전체 소스를 가져오려 해보거나, 프로젝트 번호를 가져와서 url 주소를 클릭해 보는 등 다양한 시도를 해봤으나 전부 성공하지 못했다...ㅠ
-> Jsoup을 이용해서 시도해보기도 하였으나, 로그인 단계에서 실패했다.

-계획


조직개편 이후 크롤링을 필수적으로 해야하게 되면 다시 시도해볼 것




