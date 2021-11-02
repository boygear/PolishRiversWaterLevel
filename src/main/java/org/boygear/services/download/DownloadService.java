package org.boygear.services.download;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.boygear.entities.Measurement;
import org.boygear.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Component
public class DownloadService {

    private String urlString = "https://danepubliczne.imgw.pl/api/data/hydro/";

    private HttpURLConnection setRequest() throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        return con;
    }


    private String readTheResponse(HttpURLConnection con) throws IOException {
        int status = con.getResponseCode();

        Reader streamReader = null;
        StringBuffer content = new StringBuffer();
        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        con.disconnect();

        return content.toString();
    }

    public List<DownloadedMeasurement> getCurrentMeasurementList(){
        String requestString = null;
        try{
            requestString = readTheResponse(setRequest());
        }catch (IOException e){
            throw new BadRequestException("Problem with request", e);
        }

        Gson json = new Gson();
        Type type = new TypeToken<List<DownloadedMeasurement>>() {}.getType();
        return json.fromJson(requestString, type);
    }
}
