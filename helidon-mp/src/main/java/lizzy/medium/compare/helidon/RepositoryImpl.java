package lizzy.medium.compare.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Issue> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(Issue.class, id));
    }

    @Override
    public Issue insert(Issue issue) {
        entityManager.persist(issue);
        return issue;
    }

    @Override
    public void deleteById(UUID id) {
        entityManager.remove(entityManager.getReference(Issue.class, id));
    }

    @Override
    public List<Issue> findAll() {
        return entityManager.createNamedQuery("Issue.findAll", Issue.class).getResultList();
    }

    @Override
    public Issue update(Issue issue) {
        return entityManager.merge(issue);
    }

}
