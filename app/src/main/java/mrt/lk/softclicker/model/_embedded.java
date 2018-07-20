package mrt.lk.softclicker.model;

/**
 * Created by Kamal on 7/19/18.
 */

public class _embedded
{
    private Quections[] quections;

    public Quections[] getQuections ()
    {
        return quections;
    }

    public void setQuections (Quections[] quections)
    {
        this.quections = quections;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quections = "+quections+"]";
    }
}