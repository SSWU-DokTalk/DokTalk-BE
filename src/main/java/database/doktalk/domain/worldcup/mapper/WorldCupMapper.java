package database.doktalk.domain.worldcup.mapper;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;
import database.doktalk.domain.worldcup.entity.WorldCupMatch;
import org.springframework.stereotype.Component;

@Component
public class WorldCupMapper {

    public WorldCup toWorldCup(WorldCupRequest request){
        return WorldCup.builder()
                .subject(request.getSubject())
                .build();
    }

    public WorldCupMatch toWorldCupMatch(int roundNumber, Book book1, Book book2, WorldCup worldCup){
        return WorldCupMatch.builder()
                .roundNumber(roundNumber)
                .worldCup(worldCup)
                .book1(book1)
                .book2(book2)
                .build();
    }

    public MatchIdResponse toMatchIdResponse(WorldCupMatch worldCupMatch){
        return MatchIdResponse.builder()
                .id(worldCupMatch.getId())
                .build();
    }
}

