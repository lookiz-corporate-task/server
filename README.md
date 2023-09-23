# Lookiz

## **Git Flow 전략**

### **1. 주요 브랜치**

* **main** : 안정적이며 실제 운영 환경에 배포될 코드가 저장됩니다. 항상 안정적인 코드만 존재해야 합니다.
* **develop** : 다음 배포를 위한 개발 작업이 진행되는 브랜치입니다.

### **2. 보조 브랜치**

* **feature branches** : 새로운 기능 개발을 위한 브랜치입니다. 개발이 완료되면 develop 브랜치에 병합(merge)됩니다.
  * 규칙: `feature/[기능명]-[작업 내용 또는 이슈 번호]`
  * 예: `feature/login-email-validation`
* **release branches** : 배포를 위해 마지막 테스트와 버그 수정을 진행하는 브랜치입니다. 완료되면 master와 develop 브랜치에 병합됩니다.
  * 규칙: `release/[버전 번호]`
  * 예: `release/1.2.0`
* **hotfix branches** : 운영 환경에서 발생한 긴급한 버그를 수정하기 위한 브랜치입니다. 수정 후 master와 develop 브랜치에 병합됩니다.
  * 규칙: `hotfix/[버그명]-[이슈 번호 또는 날짜]`
  * 예: `hotfix/login-bug-#123`

## **Git Commit 규칙**

### **1. 기본 규칙**

* 제목과 본문을 한 줄 띄워 분리하기
* 제목은 50자 이내로
* 본문은 72자마다 줄 바꾸기
* 제목 끝에 마침표 생략
* 제목은 명령문으로

```
제목: 한 줄 요약

본문: 상세한 변경 사항 설명
```

### **2. 커밋 유형에 따른 접두어**

* **기능 추가** :
  * 접두어: **`feat:`**
  * 예: **`feat: add login functionality`**
* **버그 수정** :
  * 접두어: **`fix:`**
  * 예: **`fix: correct email validation`**
* **리팩토링** :
  * 접두어: **`refactor:`**
  * 예: **`refactor: restructure user model`**
* **스타일 변경 (코드 형식, 세미콜론 누락, 코드 변경 없음)** :
  * 접두어: **`style:`**
  * 예: **`style: format user controller`**
* **문서 추가/수정** :
  * 접두어: **`docs:`**
  * 예: **`docs: update API documentation`**
* **테스트 관련 변경** :
  * 접두어: **`test:`**
  * 예: **`test: add user registration test case`**
* **빌드 프로세스 혹은 외부 의존성 변경** :
  * 접두어: `setting:`
  * 예: **`setting: update webpack configuration`**

## **How To Run**

### **1. applocation.properties 파일 설정하기**

```javascript
app.version=1.0.5

// DB Connection Setting
spring.datasource.url='db server url'
spring.datasource.username='db username'
spring.datasource.password='db password'
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

// Hibernate Setting
spring.jpa.generate-ddl=false
spring.jpa.show-sql=false
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.properties.hibernate.format_sql=true

// HTTPS Setting
server.ssl.key-store='keystore path'
server.key-store-stype='key store stye'
server.ssl.key-store-password='key store password'
server.ssl.key-alias='key alias

server.port='https port'

```

### **2. 실행하기**
```javascript
// Build

./gradlew build

// Start

./gradlew bootRun
```
