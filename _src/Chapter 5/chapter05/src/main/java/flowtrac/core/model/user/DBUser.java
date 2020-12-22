package flowtrac.core.model.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Implementation of the User-Interface using a database.
 * @author Sven
 */
@Entity
@Table(name = "\"User\"")
public class DBUser implements User {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4666012219130057369L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email = "";
    private String name;
    private String password;
    private Role role;
    private String username;
    private User createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    private User lastModifiedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.user.User#getEMail()
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.user.User#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.user.User#getPassword()
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.user.User#getRole()
     */
    @Override
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.user.User#getUsername()
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.issue.ItemInformation#getCreatedBy()
     */
    @Override
    public User getCreatedBy() {
        return this.createdBy;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.issue.ItemInformation#getCreationDate()
     */
    @Override
    public Date getCreationDate() {
        if (this.creationDate != null) {
            return new Date(this.creationDate.getTime());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.issue.ItemInformation#getLastModified()
     */
    @Override
    public Date getLastModified() {
        if (this.lastModified != null) {
            return new Date(this.lastModified.getTime());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see flowtrac.core.model.issue.ItemInformation#getLastModifiedBy()
     */
    @Override
    public User getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * flowtrac.core.model.issue.ItemInformation#setCreatedBy(flowtrac.core.
     * model.user.User)
     */
    @Override
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * flowtrac.core.model.issue.ItemInformation#setCreationDate(java.util.Date)
     */
    @Override
    public void setCreationDate(Date creationDate) {
        if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * flowtrac.core.model.issue.ItemInformation#setLastModified(java.util.Date)
     */
    @Override
    public void setLastModified(Date lastModified) {
        if (lastModified != null) {
            this.lastModified = new Date(lastModified.getTime());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * flowtrac.core.model.issue.ItemInformation#setLastModifiedBy(flowtrac.
     * core.model.user.User)
     */
    @Override
    public void setLastModifiedBy(User lastModfiedBy) {
        this.lastModifiedBy = lastModfiedBy;
    }
}
