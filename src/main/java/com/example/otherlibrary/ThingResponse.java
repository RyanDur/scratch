package com.example.otherlibrary;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ThingResponse {
    public List<Thing> things;

    private ThingResponse(Builder builder) {
        things = builder.things;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Thing> getThings() {
        return things;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThingResponse that = (ThingResponse) o;

        return Objects.equals(this.things, that.things);
    }

    @Override
    public int hashCode() {
        return Objects.hash(things);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("things = " + things)
                .toString();
    }

    public static final class Builder {
        private List<Thing> things;

        private Builder() {
        }

        public Builder things(List<Thing> val) {
            things = val;
            return this;
        }

        public ThingResponse build() {
            return new ThingResponse(this);
        }
    }
}
