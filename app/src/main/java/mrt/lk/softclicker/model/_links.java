package mrt.lk.softclicker.model;

/**
 * Created by Kamal on 7/19/18.
 */

public class _links
{
    private Quection quection;

    private Self self;

    public Quection getQuection ()
    {
        return quection;
    }

    public void setQuection (Quection quection)
    {
        this.quection = quection;
    }

    public Self getSelf ()
    {
        return self;
    }

    public void setSelf (Self self)
    {
        this.self = self;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quection = "+quection+", self = "+self+"]";
    }
}
