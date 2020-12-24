# Dropbox Tests Project

Used Java + Maven + Selenide + JUnit + Hamcrest + Allure

Also, I used travis as a CI.
Selenide is a Selenium wrapper. This library resolve a lot of problems of UI tests: instability of tests caused by dynamic content, JavaScript, Ajax, timeouts etc.

> ***Please note:*** DropBox uses CAPTCHA and this is often can be the problem for tests (we can see CAPTCHA alerts in the report)

* run tests locally:
`mvn clean test`

* all Selenide properties are available, for example if you want to execute tests on Firefox (Chrome is by default):
`mvn clean test -Dselenide.browser=firefox`
  
* generate and open Allure Report (with screenshots):
`mvn allure:serve`

last build status:
[![Build Status](https://travis-ci.com/alisa0301/dropbox-tests.svg?branch=main)](https://travis-ci.com/alisa0301/dropbox-tests)