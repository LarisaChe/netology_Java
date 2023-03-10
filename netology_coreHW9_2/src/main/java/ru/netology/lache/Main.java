package ru.netology.lache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URL;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=ZYISnAeY1GRxifgS9eygyFmIawiVHyc5i1meTHE5");
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);

        ResponseNasa resps = mapper.readValue(response.getEntity().getContent(), new TypeReference<ResponseNasa>() {});

        URL url = new URL(resps.getUrl());

        String[] parts = resps.getUrl().split("/");
        String fileName = parts[parts.length - 1];

        /*URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        Files.copy(inputStream, new File(fileName).toPath()); */

        try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, count);
            }
            System.out.println("Файл сохранен на диске");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("Файл существует на диске.");
        } else {
            System.out.println("Файл не найден.");
        }
        if (file.canRead()) {
            System.out.println("Файл может быть прочитан.");
        } else {
            System.out.println("Файл не может быть прочитан.");
        }
    }
}