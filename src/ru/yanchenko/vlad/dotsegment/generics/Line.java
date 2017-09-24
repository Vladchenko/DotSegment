package ru.yanchenko.vlad.dotsegment.generics;

/**
 * Line made of 2 dots (ru.yanchenko.vlad.dotsegment.generics.Dot)
 * Created by Влад on 24.09.2017.
 */
public class Line {

    private Dot dot1;
    private Dot dot2;

    public Line() {
        dot1 = new Dot();
        dot2 = new Dot();
    }

    public Line(Dot dot1, Dot dot2) {
        this.dot1 = dot1;
        this.dot2 = dot2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!dot1.equals(line.dot1)) return false;
        return dot2.equals(line.dot2);

    }

    @Override
    public int hashCode() {
        int result = dot1.hashCode();
        result = 31 * result + dot2.hashCode();
        return result;
    }

    public Dot getDot2() {
        return dot2;
    }

    public void setDot2(Dot dot2) {
        this.dot2 = dot2;
    }

    public Dot getDot1() {
        return dot1;
    }

    public void setDot1(Dot dot1) {
        this.dot1 = dot1;
    }
}
