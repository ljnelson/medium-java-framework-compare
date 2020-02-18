package lizzy.medium.compare.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import java.util.Collections;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/")
public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(RestInterface.class);
    }
}
