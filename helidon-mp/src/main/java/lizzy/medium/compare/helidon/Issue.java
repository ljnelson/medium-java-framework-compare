package lizzy.medium.compare.helidon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue as i")
public class Issue {
    @Id
    private UUID id;
    private String name;
    private String description;

    void partialUpdate(Issue partialIssue) {
        if (partialIssue.getName() != null) {
            this.name = partialIssue.getName();
        }

        if (partialIssue.getDescription() != null) {
            this.description = partialIssue.getDescription();
        }
    }
}
