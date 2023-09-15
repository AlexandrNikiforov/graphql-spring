package com.alexnikiforov.graphql.graphqlspringboot.fitness;

import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.Coach;
import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.Difficulty;
import com.alexnikiforov.graphql.graphqlspringboot.fitness.domain.FitnessClass;
import graphql.GraphQLContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class FitnessController {

    @QueryMapping
    public List<FitnessClass> schedule(@Argument Coach coach,
                                       GraphQLContext context) {
        log.info("schedule({})", coach);

        var startsAt = LocalDateTime.now();

        return List.of(FitnessClass.builder()
                .id(UUID.randomUUID())
                .coach(coach)
                .startsAt(startsAt)
                .endsAt(startsAt.plusHours(1L))
                .difficulty(Difficulty.BEGINNER)
                .build()
        );
    }
}
