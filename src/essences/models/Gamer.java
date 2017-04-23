
package essences.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gamer complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="gamer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="niackname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gamer", propOrder = {
        "rdr_id",
        "niackname",
        "score",
        "password",
        "email",
        "isAdmin",
        "isBlocked"
})
public class Gamer {
    protected int rdr_id;
    protected String niackname;
    protected int score;
    protected String password;
    protected String email;
    protected boolean isAdmin;
    protected boolean isBlocked;

    /**
     * Gets the value of the niackname property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNiackname() {
        return niackname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the value of the niackname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNiackname(String value) {
        this.niackname = value;
    }

    public int getRdr_id() {
        return rdr_id;
    }

    public void setRdr_id(int rdr_id) {
        this.rdr_id = rdr_id;
    }

    /**
     * Gets the value of the score property.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the value of the score property.
     */
    public void setScore(int value) {
        this.score = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPassword(String value) {
        this.password = value;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "niackname='" + niackname + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", isBlocked=" + isBlocked +
                '}';
    }


}
