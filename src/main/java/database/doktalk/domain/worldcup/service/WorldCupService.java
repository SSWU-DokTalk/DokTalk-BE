package database.doktalk.domain.worldcup.service;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupIdResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;

public interface WorldCupService {
    WorldCupIdResponse createWorldCup(WorldCupRequest request);
    MatchIdResponse createMatch(int roundNumber, Long bookId1, Long bookId2, Long worldCup);
}
