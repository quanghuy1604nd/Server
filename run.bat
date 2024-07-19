@echo off

:: Thiết lập các biến môi trường cho các cổng
set port=807
:: Chạy ứng dụng JAR đầu tiên trên cổng PORT1
start "" java -jar dist/Server.jar

set port=808
:: Chạy ứng dụng JAR thứ hai trên cổng PORT2
start "" java -jar dist/Server.jar
