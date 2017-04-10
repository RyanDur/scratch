package com.example.services;

import com.example.otherlibrary.Thing;
import com.example.otherlibrary.ThingResponse;
import com.example.things.GenericThing.ThingFactory;
import com.example.things.ManyThings;
import com.example.things.definitions.Things;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.example.things.definitions.Things.AAA;
import static com.example.things.definitions.Things.BBB;
import static com.example.things.definitions.Things.CCC;
import static com.example.things.definitions.Things.DDD;
import static java.util.Optional.of;
import static java.util.stream.Collectors.groupingBy;

public class ThingService {

    private final DBService service;
    private final ThingFactory factory;

    public ThingService(DBService service, ThingFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public ManyThings getSpendingAccounts(Integer id, List<String> fields) {
        ThingResponse response = service.getSpendingAccountsResponse(id);

        Map<Things, List<Thing>> accounts = response.getThings().stream()
                .collect(groupingBy(o -> Things.valueOf(o.getWtfThingType())));

        return of(ManyThings.newBuilder())
                .map(get(AAA, accounts, fields))
                .map(get(BBB, accounts, fields))
                .map(get(CCC, accounts, fields))
                .map(get(DDD, accounts, fields))
                .orElse(null)
                .build();
    }

    private Function<ManyThings.Builder, ManyThings.Builder>
    get(Things thing, Map<Things, List<Thing>> things, List<String> fields) {
        return builder -> {
            switch (thing) {
                case AAA:
                    return builder.thing1s(factory.from(thing, things.get(thing), fields));
                case BBB:
                    return builder.thing2s(factory.from(thing, things.get(thing), fields));
                case CCC:
                    return builder.differentThings(factory.from(thing, things.get(thing), fields));
                case DDD:
                    return builder.thing3s(factory.from(thing, things.get(thing), fields));
                default:
                    return builder;
            }
        };
    }
}
