# Downloading & Storing PDF File

## Tools & Technology used:
- **Test Framework Suite:** Selenium
- **Testing Framework:** TestNG
- **Programming Language:** Java
- **IDE:** IntelliJ IDEA
- **Build tool:** Gradle


## Prerequisite:
1. Need to install **JDK 17** and configure it into Environment variable
2. Download **Gradle** and configure it into Environment variable also

## Run the automation script:
1. Open the project with **IntelliJ IDEA** by clicking on **build.gradle**
2. Let the **IntelliJ IDEA** to download the dependencies for the first run
3. After dependencies are downloaded and project loaded successfully, click on **Terminal** from the bottom left bar
4. Type in the terminal:

```bash
gradle clean test
```
5. Selenium will open the browser and start the automation.
6. To view report of the automation execution, expand the **build -> reports -> tests** folder and open "index.html" in a browser

## Test execution report:

![img.png](img.png)