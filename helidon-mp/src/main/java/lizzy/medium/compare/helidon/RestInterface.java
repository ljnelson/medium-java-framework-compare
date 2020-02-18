package lizzy.medium.compare.helidon;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/issue")
@RequestScoped
public class RestInterface {
  
    private final Repository repository;

    @Inject
    public RestInterface(Repository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Issue> readAll() {
        final Iterable<Issue> result = repository.findAll();
        final List<Issue> returnValue;
        if (result instanceof List) {
          returnValue = (List<Issue>) result;
        } else if (result != null) {
          returnValue = new ArrayList<>();
          result.forEach(returnValue::add);
        } else {
          returnValue = Collections.emptyList();
        }
        return returnValue;
    }

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Issue> read(@PathParam("id") UUID id) {
        return repository.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Issue create(Issue body) {
        return repository.insert(body);
    }

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Issue update(@PathParam("id") UUID id, Issue body) {
        return repository.update(body);
    }

    @PATCH
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Issue partialUpdate(@PathParam("id") UUID id, Issue body) {
        final Issue issue = repository.findById(id).orElseThrow(RuntimeException::new);
        issue.partialUpdate(body);
        return issue;
    }

    @DELETE
    @Path("/{id}/")
    @Transactional
    public void delete(@PathParam("id") UUID id) {
        repository.deleteById(id);
    }
}
