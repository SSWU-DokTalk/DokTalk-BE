package database.doktalk.domain.worldcup.mapper;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.BookSummaryResponse;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupSummaryResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;
import database.doktalk.domain.worldcup.entity.WorldCupMatch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorldCupMapper {

    public WorldCupSummaryResponse toWorldCupSummaryResponse(WorldCup worldCup) {
        return WorldCupSummaryResponse.builder()
                .worldCupId(worldCup.getId())
                .worldCupName(worldCup.getSubject())
                .coverImage(worldCup.getCoverImage())
                .build();
    }

    public WorldCupResponse toWorldCupResponse(WorldCup worldCup, List<BookSummaryResponse> books){
        return WorldCupResponse.builder()
                .title(worldCup.getSubject())
                .books(books)
                .build();
    }

    public BookSummaryResponse toBookSummaryResponse(Book book){
        return BookSummaryResponse.builder()
                .title(book.getBookName())
                .coverImage(book.getCoverImage())
                .build();
    }
}

