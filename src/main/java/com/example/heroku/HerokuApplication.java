package com.example.heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class HerokuApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(HerokuApplication.class, args);
  }

//  @RequestMapping("/db")
//  String db(Map<String, Object> model) {
//    try (Connection connection = dataSource.getConnection()) {
//      Statement stmt = connection.createStatement();
//      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//      ArrayList<String> output = new ArrayList<String>();
//      while (rs.next()) {
//        output.add("Read from DB: " + rs.getTimestamp("tick"));
//      }
//
//      model.put("records", output);
//      return "db";
//    } catch (Exception e) {
//      model.put("message", e.getMessage());
//      return "error";
//    }
//  }
//
//  @Bean
//  public DataSource dataSource() throws SQLException {
//    if (dbUrl == null || dbUrl.isEmpty()) {
//      return new HikariDataSource();
//    } else {
//      HikariConfig config = new HikariConfig();
//      config.setJdbcUrl(dbUrl);
//      return new HikariDataSource(config);
//    }
//  }

}
