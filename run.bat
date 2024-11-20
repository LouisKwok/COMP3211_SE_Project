@echo off
cls

:: 編譯所有 .java 檔案
javac -d out -sourcepath src src/controller/*.java src/model/*.java src/view/*.java

:: 檢查是否編譯成功
if %errorlevel% neq 0 (
    echo [Error] Compilation failed!
    pause
    exit /b
)

:: 執行主程式
java -cp out controller.Main

pause