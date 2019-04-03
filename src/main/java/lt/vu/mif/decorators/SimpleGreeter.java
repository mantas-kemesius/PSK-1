package lt.vu.mif.decorators;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class SimpleGreeter implements Greeter {
    public String getGreeting() {
        return "Hello";
    }
}
