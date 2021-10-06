package http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class HttpConnection {
    private static HttpConnection instance;
    URL add_url;
    URL search_url;

    private HttpConnection(String add_url, String search_url) throws MalformedURLException {
        this.add_url = new URL(add_url);
        this.search_url = new URL(search_url);
    }

    public static HttpConnection getInstance(String add_url, String search_url) throws MalformedURLException {
        if (instance == null){
            instance = new HttpConnection(add_url, search_url);
        }
        return instance;
    }


    public void sendAddCommand(Book book){
        try {
            HttpURLConnection con = (HttpURLConnection)add_url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(book);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (con.getResponseCode() == 201){
                System.out.println("Книга была успешно добавлена!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка доступа к серверу");
            System.exit(0);
        }
    }

    public void sendSearchCommand(String value, String parameterName) throws MalformedURLException {
        URL url = new URL(search_url.toString() + "?" +parameterName+"="+value);
        HttpURLConnection con = null;
        ArrayList<Book> books = new ArrayList<>();
        try {
            con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
            con.connect();
            int status = con.getResponseCode();
            if (status == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                JSONArray books_json = (JSONArray) JSONValue.parse(sb.toString());
                for (Object jsonObject : books_json){
                    Book book = new Book();
                    book.setName((String) ((JSONObject) jsonObject).get("name"));
                    book.setAnnotation((String) ((JSONObject) jsonObject).get("annotation"));
                    book.setAuthorName((String) ((JSONObject) jsonObject).get("authorName"));
                    book.setIsbn((String) ((JSONObject) jsonObject).get("isbn"));
                    book.setGenreName((String) ((JSONObject) jsonObject).get("genreName"));
                    book.setId((long)  ((JSONObject) jsonObject).get("id"));
                    books.add(book);
                }
                books.forEach(System.out::println);

            }else{
                System.out.println("Ошибка отправки запроса! Данного параметра не существует");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
