package model;

import java.time.LocalDate;
import java.time.Period;

public class RentedSpace extends AbstractSpace{

    private LocalDate finishDate;

    public RentedSpace() {
        super();
        finishDate = LocalDate.now().plusDays(1);
    }

    public RentedSpace(Person person, LocalDate startDate, LocalDate finishDate) {
        super(person,startDate);
        if(finishDate == null)throw new NullPointerException();
        if(finishDate.isBefore(startDate))throw new IllegalArgumentException();
        this.finishDate = finishDate;
    }

    public RentedSpace(Person person, Vehicle vehicle, LocalDate startDate, LocalDate finishDate) {
        super(person, vehicle,startDate);
        if(finishDate == null)throw new NullPointerException();
        if(finishDate.isBefore(startDate))throw new IllegalArgumentException();
        this.finishDate = finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        if(finishDate == null)throw new NullPointerException();
        if(finishDate.isBefore(startDate))throw new IllegalArgumentException();
        this.finishDate = finishDate;
    }
    public LocalDate getFinishDate(){
        return finishDate;
    }
    public Period getFinishPeriod(){
        return Period.between(finishDate,LocalDate.now());
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
