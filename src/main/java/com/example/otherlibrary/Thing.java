package com.example.otherlibrary;

import java.util.Objects;
import java.util.StringJoiner;

public class Thing {
    private String wtfGiverType;
    private String wtfThingType;

    private Thing(Builder builder) {
        wtfGiverType = builder.wtfGiverType;
        wtfThingType = builder.wtfThingType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getWtfGiverType() {
        return wtfGiverType;
    }

    public String getWtfThingType() {
        return wtfThingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thing that = (Thing) o;

        return Objects.equals(this.wtfThingType, that.wtfThingType) &&
                Objects.equals(this.wtfGiverType, that.wtfGiverType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wtfThingType, wtfGiverType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("wtfThingType = " + wtfThingType)
                .add("wtfGiverType = " + wtfGiverType)
                .toString();
    }

    public static final class Builder {
        private String wtfGiverType;
        private String wtfThingType;

        private Builder() {
        }

        public Builder wtfGiverType(String val) {
            wtfGiverType = val;
            return this;
        }

        public Builder wtfThingType(String val) {
            wtfThingType = val;
            return this;
        }

        public Thing build() {
            return new Thing(this);
        }
    }
}
