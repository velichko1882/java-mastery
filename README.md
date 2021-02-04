# Java Mastery 2021

Для работы приложения необходимо установить следующее програмное обеспечение:

1. JDK 8+
2. PostgreSQL 12+
3. Apache Tomcat 9+
4. Apache Maven 3.6.3+
5. pgAdmin4 4.30+ (optional)

Для запуска приложениия необходимо:

1. В СУБД установить пользователю "**postgres**" (по умолчанию) пароль "**postgres**", создать под этим пользователем базу данных "**PostgreSQL**" и заполнить её с помощью SQL-скриптов: **create_objects.sql** и **tables_content.sql**.
2. Скачать zip-архив приложения по адресу: **http://github.com/velichko1882/java-mastery** и распаковать его.
3. Запустить контейнер сервлетов **Apache Tomcat**.
4. Перейти в директорию приложения, открыть в ней интерфейс командной строки (терминал) и набрать команду: 
  **mvn tomcat7:deploy**.
5. Открыть окно браузера и в адресной строке ввести: **localhost:8080/JavaMastery2021**. Приложениие должно запуститься.
