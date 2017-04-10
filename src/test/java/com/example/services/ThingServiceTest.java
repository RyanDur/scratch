package com.example.services;

import com.example.otherlibrary.Thing;
import com.example.things.GenericThing;
import com.example.things.GenericThing.ThingFactory;
import com.example.things.ManyThings;
import com.example.things.thing1.Thing1Summary;
import com.example.things.thing1.Thing1History;
import com.example.things.differentthing.DifferentThingSummary;
import com.example.things.differentthing.DifferentThingHistory;
import com.example.things.thing2.Thing2Summary;
import com.example.things.thing2.Thing2History;
import com.example.things.thing3.Thing3Summary;
import com.example.things.thing3.Thing3History;
import com.example.otherlibrary.ThingResponse;
import org.junit.Test;

import java.util.List;

import static com.example.things.definitions.Things.AAA;
import static com.example.things.definitions.Things.CCC;
import static com.example.things.definitions.Things.BBB;
import static com.example.things.definitions.Things.DDD;
import static com.example.things.definitions.GiverOfThings.PAUL;
import static com.example.things.definitions.GiverOfThings.BROOKS;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThingServiceTest {

    @Test
    public void shouldParseStuff() {
        DBService dbService = mock(DBService.class);
        ThingResponse response = ThingResponse.newBuilder()
                .things(asList(Thing.newBuilder()
                        .wtfThingType("DDD")
                        .wtfGiverType("BROOKS")
                        .build(), Thing.newBuilder()
                        .wtfThingType("CCC")
                        .wtfGiverType("PAUL")
                        .build(), Thing.newBuilder()
                        .wtfThingType("AAA")
                        .wtfGiverType("BROOKS")
                        .build(), Thing.newBuilder()
                        .wtfThingType("BBB")
                        .wtfGiverType("BROOKS")
                        .build()))
                .build();
        ThingService service = new ThingService(dbService, new ThingFactory());
        List<String> fields = asList("thingName", "thingType");
        Integer id = 3;

        when(dbService.getSpendingAccountsResponse(anyInt())).thenReturn(response);

        ManyThings manyThings = service.getSpendingAccounts(id, fields);

        ManyThings expected = ManyThings.newBuilder()
                .thing1s(singletonList(GenericThing.<Thing1Summary, Thing1History>newBuilder()
                        .thingType(AAA)
                        .thingName(BROOKS)
                        .build()))
                .differentThings(singletonList(GenericThing.<DifferentThingSummary, DifferentThingHistory>newBuilder()
                        .thingType(CCC)
                        .thingName(PAUL)
                        .build()))
                .thing2s(singletonList(GenericThing.<Thing2Summary, Thing2History>newBuilder()
                        .thingType(BBB)
                        .thingName(BROOKS)
                        .build()))
                .thing3s(singletonList(GenericThing.<Thing3Summary, Thing3History>newBuilder()
                        .thingType(DDD)
                        .thingName(BROOKS)
                        .build()))
                .build();

        assertThat(manyThings, is(equalTo(expected)));
    }
}