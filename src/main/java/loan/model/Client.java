package loan.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author A.Bogoslov
 */

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "doc_id")
    private Long docId;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Request> requests;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Клиент: " + surname + " " + firstName + "; ДУЛ: " + docId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(docId, client.docId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, surname, docId);
    }
}
