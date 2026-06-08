# Login Test (nxdkhue)

Project Maven test to perform login on:
https://efadzli.com/software_testing/index.php?view=user_login

Test case: `id=username` -> `Adam`, `id=password` -> `Adam123`

Run tests (downloads driver automatically using WebDriverManager):

Windows (headless):

```powershell
mvn -Dheadless=true test
```

Windows (show browser):

```powershell
mvn test
```

Notes:
- Requires Java 11+ and Maven installed.
- The test asserts basic indicators of a successful login (redirect or presence of "logout").
 
Run the `MainRunner` directly via Maven Exec plugin:

Windows (headless):

```powershell
cd C:\Users\pc\login-test
mvn -Dheadless=true exec:java -Dexec.mainClass=nxdkhue.MainRunner
```

Windows (show browser):

```powershell
cd C:\Users\pc\login-test
mvn exec:java -Dexec.mainClass=nxdkhue.MainRunner
```

Notes on drivers:
- You do NOT need to download `chromedriver.exe` manually: `WebDriverManager` will download a matching driver automatically at runtime.
- If you prefer to use a local driver file, pass its path to JVM property `webdriver.chrome.driver`, for example:

```powershell
mvn -Dwebdriver.chrome.driver="C:\path\to\chromedriver.exe" exec:java -Dexec.mainClass=nxdkhue.MainRunner
```
