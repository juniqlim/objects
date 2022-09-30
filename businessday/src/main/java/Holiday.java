import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Holiday {
    private final String year;

    public Holiday(LocalDate source) {
        this(source.format(DateTimeFormatter.BASIC_ISO_DATE).substring(0, 4));
    }

    public Holiday(String year) {
        this.year = year;
    }

    public List<LocalDate> holidays() throws IOException {
        final String OPEN_API = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
        final String OPEN_API_KEY = "=W1V6sQgYWYdbmfmjiOnF%2FJDEkCqEDNUDc1%2B9UbA6yFjK23BVGZ9%2FfEp87o8eGKrjSKoplpIPPVCX76Ns8GHnHQ%3D%3D";

        String urlBuilder =
            OPEN_API + "?" + URLEncoder.encode("ServiceKey", "UTF-8") + OPEN_API_KEY /*Service Key*/ + "&"
                + URLEncoder.encode("numOfRows", "UTF-8") + "=100&"
                + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8") /*공공데이터포털에서 받은 인증키*/
                + "&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode(year, "UTF-8") /*연*/;
        URL url = new URL(urlBuilder);

        BufferedReader br;
        StringBuilder sb;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        int responseCode = conn.getResponseCode();
        if (responseCode >= 200 && responseCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        List<LocalDate> holidays = new ArrayList<>();
        Pattern pattern = Pattern.compile("<locdate>(.*?)</locdate>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(sb.toString());
        while (matcher.find()) {
            holidays.add(LocalDate.parse(matcher.group(1), DateTimeFormatter.BASIC_ISO_DATE));
        }

        return holidays;
    }
}