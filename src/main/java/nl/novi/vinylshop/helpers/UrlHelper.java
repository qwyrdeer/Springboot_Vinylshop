package nl.novi.vinylshop.helpers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class UrlHelper {

    private final HttpServletRequest request;

    public UrlHelper(HttpServletRequest request){
        this.request = request;
    }


    public  String getCurrentUrlString() {
        return request.getRequestURL().toString();
    }

    public String getCurrentUrlString( Long id) {
        return request.getRequestURL().toString() + "/" + id.toString();
    }

    public  URI getCurrentUrl() {
        return convertToURI(getCurrentUrlString());
    }

    public URI getCurrentUrlWithId( Long id) {
        return convertToURI(getCurrentUrlString( id));
    }

    private  URI convertToURI(String uri) {
        return URI.create(uri);
    }
}
