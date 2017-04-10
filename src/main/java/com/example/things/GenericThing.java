package com.example.things;

import com.example.otherlibrary.Thing;
import com.example.things.definitions.GiverOfThings;
import com.example.things.definitions.Things;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import static java.util.stream.Collectors.toList;

public class GenericThing<T, R> {
    private GiverOfThings giverName;
    private Things thingType;
    private List<T> summaries;
    private List<R> histories;

    private GenericThing(Builder<T, R> builder) {
        giverName = builder.thingName;
        thingType = builder.thingType;
        summaries = builder.summaries;
        histories = builder.histories;
    }

    public static class ThingFactory {
        public <A, B> List<GenericThing<A, B>>
        from(Things type, List<Thing> things, List<String> fields) {
            return things.stream().map(acc ->
                    GenericThing.<A, B>newBuilder()
                            .thingName(GiverOfThings.valueOf(acc.getWtfGiverType()))
                            .thingType(type)
                            .build())
                    .collect(toList());
        }
    }

    public static <A, B> Builder<A, B> newBuilder() {
        return new Builder<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericThing that = (GenericThing) o;

        return Objects.equals(this.thingType, that.thingType) &&
                Objects.equals(this.summaries, that.summaries) &&
                Objects.equals(this.histories, that.histories) &&
                Objects.equals(this.giverName, that.giverName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thingType, summaries, histories, giverName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("thingType = " + thingType)
                .add("summaries = " + summaries)
                .add("histories = " + histories)
                .add("thingName = " + giverName)
                .toString();
    }

    public static final class Builder<A, B> {
        private GiverOfThings thingName;
        private Things thingType;
        private List<A> summaries;
        private List<B> histories;

        private Builder() {
        }

        public Builder<A, B> thingName(GiverOfThings val) {
            thingName = val;
            return this;
        }

        public Builder<A, B> thingType(Things val) {
            thingType = val;
            return this;
        }

        public Builder<A, B> summaries(List<A> val) {
            summaries = val;
            return this;
        }

        public Builder<A, B> histories(List<B> val) {
            histories = val;
            return this;
        }

        public GenericThing<A, B> build() {
            return new GenericThing<>(this);
        }
    }
}
