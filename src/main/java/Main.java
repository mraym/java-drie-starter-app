import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.google.gson.Gson;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");
    get("/really/really/really/lomg/path/integration/test", (req, res) -> "Hello World");

    get("/say-hello-drie", (request, response) -> {
      Map<String, String> messageMap = new HashMap<String,String>();
      messageMap.put("message", "Hello Java Spark Microframework on Drie!");
      
      Gson gson = new Gson();
      String json = gson.toJson(messageMap);

      response.status(200);
      response.type("application/json");
      return json;
    });

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

  }

}
