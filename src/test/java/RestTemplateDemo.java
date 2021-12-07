import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo {

    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();
        String name = "Tom";
        ResponseEntity<AgifyResponse> response = rt.getForEntity("https://api.agify.io/?name={n}", AgifyResponse.class, name);
        AgifyResponse body = response.getBody();
        System.out.println(body.getName() + "'s age is " + body.getAge());
    }
}
