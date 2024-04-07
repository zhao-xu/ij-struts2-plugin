# struts2 插件
jetbrains 不再维护，下载后自行编译

源码  
[https://github.com/JetBrains/intellij-obsolete-plugins/tree/master/struts2]()

#### 修改版本
```
version = "2024.1" // 生成插件的版本
tasks {
    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("241.*") // 修改这个，241 表示 2024.1 版本
    }
    test {
        exclude("**/*") // 添加这个，默认跳过 test
    }
```

#### 编译
```
gradlew build
```
如果有 unit test 错误，则跳过
```
gradlew build -x test
```
生成文件位于 `build/distributions/struts2-2024.1.zip`，手动安装
