
package main.DB.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for question complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="question">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="quest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="answer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
        "q_id",
        "quest",
        "answer",
        "hint",
        "score"
})
@XmlRootElement(name = "Question")
public class Question {
protected int q_id;
    protected String quest;
    protected String answer;
    protected String hint;
    protected int score;


    /**
     * Gets the value of the quest property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQuest() {
        return quest;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    /**
     * Sets the value of the quest property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQuest(String value) {
        this.quest = value;
    }

    /**
     * Gets the value of the answer property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the value of the answer property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAnswer(String value) {
        this.answer = value;
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

    @Override
    public String toString() {
        return "Question{" +
                "quest='" + quest + '\'' +
                ", answer='" + answer + '\'' +
                ", hint='" + hint + '\'' +
                ", score=" + score +
                '}';
    }
}
