package mrt.lk.softclicker.model;

/**
 * Created by Kamal on 7/19/18.
 */

public class Quections
{
    private String rightAnswer;

    private _links _links;

    private String active;

    private String answer1;

    private String question;

    private String answer3;

    private String answer2;

    private String answer4;

    public String getRightAnswer ()
    {
        return rightAnswer;
    }

    public void setRightAnswer (String rightAnswer)
    {
        this.rightAnswer = rightAnswer;
    }

    public _links get_links ()
    {
        return _links;
    }

    public void set_links (_links _links)
    {
        this._links = _links;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String getAnswer1 ()
    {
        return answer1;
    }

    public void setAnswer1 (String answer1)
    {
        this.answer1 = answer1;
    }

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getAnswer3 ()
    {
        return answer3;
    }

    public void setAnswer3 (String answer3)
    {
        this.answer3 = answer3;
    }

    public String getAnswer2 ()
    {
        return answer2;
    }

    public void setAnswer2 (String answer2)
    {
        this.answer2 = answer2;
    }

    public String getAnswer4 ()
    {
        return answer4;
    }

    public void setAnswer4 (String answer4)
    {
        this.answer4 = answer4;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rightAnswer = "+rightAnswer+", _links = "+_links+", active = "+active+", answer1 = "+answer1+", question = "+question+", answer3 = "+answer3+", answer2 = "+answer2+", answer4 = "+answer4+"]";
    }
}
