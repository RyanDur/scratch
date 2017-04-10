package com.example.things;

import com.example.things.thing1.Thing1Summary;
import com.example.things.thing1.Thing1History;
import com.example.things.differentthing.DifferentThingSummary;
import com.example.things.differentthing.DifferentThingHistory;
import com.example.things.thing2.Thing2Summary;
import com.example.things.thing2.Thing2History;
import com.example.things.thing3.Thing3Summary;
import com.example.things.thing3.Thing3History;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ManyThings {
    private List<GenericThing<Thing1Summary, Thing1History>> thing1s;
    private List<GenericThing<DifferentThingSummary, DifferentThingHistory>> differentThings;
    private List<GenericThing<Thing2Summary, Thing2History>> thing2s;
    private List<GenericThing<Thing3Summary, Thing3History>> thing3s;

    private ManyThings(Builder builder) {
        thing1s = builder.thing1s;
        differentThings = builder.differentThings;
        thing2s = builder.thing2s;
        thing3s = builder.thing3s;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<GenericThing<Thing1Summary, Thing1History>> getThing1s() {
        return thing1s;
    }

    public List<GenericThing<DifferentThingSummary, DifferentThingHistory>> getDifferentThings() {
        return differentThings;
    }

    public List<GenericThing<Thing2Summary, Thing2History>> getThing2s() {
        return thing2s;
    }

    public List<GenericThing<Thing3Summary, Thing3History>> getThing3s() {
        return thing3s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManyThings that = (ManyThings) o;

        return Objects.equals(this.thing1s, that.thing1s) &&
                Objects.equals(this.differentThings, that.differentThings) &&
                Objects.equals(this.thing2s, that.thing2s) &&
                Objects.equals(this.thing3s, that.thing3s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thing1s, differentThings, thing2s, thing3s);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("thing1s = " + thing1s)
                .add("differentThings = " + differentThings)
                .add("thing2s = " + thing2s)
                .add("thing3s = " + thing3s)
                .toString();
    }

    public static final class Builder {
        private List<GenericThing<Thing1Summary, Thing1History>> thing1s;
        private List<GenericThing<DifferentThingSummary, DifferentThingHistory>> differentThings;
        private List<GenericThing<Thing2Summary, Thing2History>> thing2s;
        private List<GenericThing<Thing3Summary, Thing3History>> thing3s;

        private Builder() {
        }

        public Builder thing1s(List<GenericThing<Thing1Summary, Thing1History>> val) {
            thing1s = val;
            return this;
        }

        public Builder differentThings(List<GenericThing<DifferentThingSummary, DifferentThingHistory>> val) {
            differentThings = val;
            return this;
        }

        public Builder thing2s(List<GenericThing<Thing2Summary, Thing2History>> val) {
            thing2s = val;
            return this;
        }

        public Builder thing3s(List<GenericThing<Thing3Summary, Thing3History>> val) {
            thing3s = val;
            return this;
        }

        public ManyThings build() {
            return new ManyThings(this);
        }
    }
}
