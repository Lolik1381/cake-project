web: java -jar build/libs/gradle-getting-started-1.0.jar
release: ./mvnw -Dliquibase.changeLogFile=src/main/resources/db/changelog/db.changelog-master.yaml -Dliquibase.url=$JDBC_DATABASE_URL -Dliquibase.promptOnNonLocalDatabase=false liquibase:update