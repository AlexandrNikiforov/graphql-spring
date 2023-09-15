package com.alexnikiforov.graphql.graphqlspringboot.fitness;

import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.Coach;
import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.Difficulty;
import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.FitnessClass;
import graphql.GraphQLContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class FitnessController {

    @QueryMapping
    public List<FitnessClass> schedule(@Argument Coach coach,
                                       GraphQLContext context) {
        log.info("schedule({})", coach);

        context.put("apiKey", "value");

        var startsAt = LocalDateTime.now();

        return List.of(FitnessClass.builder()
                .id(UUID.randomUUID())
                .coach(coach)
                .startsAt(startsAt)
                .endsAt(startsAt.plusHours(1L))
                .build()
        );
    }

    //    We assume that we need to call an external service to get this Difficulty information
    @BatchMapping
    public Callable<Map<FitnessClass, Difficulty>> difficulty(
            List<FitnessClass> fitnessClasses,
            @ContextValue String apiKey,
            GraphQLContext context
    ) {
        log.info("difficulty({})", fitnessClasses);
        return () -> fitnessClasses.stream()
                        .collect(Collectors.toUnmodifiableMap(
                                Function.identity(),
                                ignore -> Difficulty.BEGINNER
                        ));
    }
}
